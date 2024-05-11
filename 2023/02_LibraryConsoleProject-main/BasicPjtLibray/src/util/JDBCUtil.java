package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.BoardVO;
import vo.BookVO;
import vo.ComentVO;
import vo.MemBookVO;
import vo.MemberVO;
import vo.RentVO;

public class JDBCUtil {
	/*
	 * JDBC를 좀 더 쉽고 편하기 사용하기 위한 클래스
	 * 
	 * Map<String, Object> selectOne(String sql) 
	 * 							: row 1개를 리턴함
	 * Map<String, Object> selectOne(String sql, List<Object> param) 
	 * 							: row 1개를 리턴하면서 sql에 '?'가 있음
	 * List<Map<String, Object>> selectList(String slq)
	 * 							: row 여러개를 리턴함
	 * List<Map<String, Object>> selectList(String slq, List<Object> param)
	 * 							: row 여러개를 리턴하면서 sql에 '?'가 있음
	 * int update(String sql)	: DB를 업데이트함
	 * int update(String sql, List<Object> param)
	 * 							: DB를 업데이트하면서 sql에 '?'가 있음
	 * */
	
	// 싱글톤 패턴 : 인스턴스의 생성을 제한하여 하나의 인스턴스만 사용하는 디자인 패턴
	private static JDBCUtil instance = null;
	// 인스턴스를 보관할 변수
	private JDBCUtil() {}
	// JDBCUtil 객체를 만들수 없게(인스턴스화 할 수 없게) private로 제한함
	public static JDBCUtil getInstance() {
		if(instance == null) instance = new JDBCUtil();
		return instance;
	}
	
	private final String URL = "jdbc:oracle:thin:@112.220.114.130:1521:xe";
	private final String USER = "TEAM6_202209S";
	private final String PASSWORD = "java";
	
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Map<String, Object> selectOne(String sql){
		// sql => "SELECT * FROM MEMBER WHERE MEM_ID='a001' AND MEM_PASS=123"
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		
		return row;
	}
	
	public Map<String, Object> selectOne(String sql, List<String> param) {
		// sql => "SELECT * FROM MEMBER WHERE MEM_ID=?
		// param => ["a001"]
		//
		// sql => "SELECT * FROM MEMBER WHERE MEM_JOB=? AND MEM_LIKE=?"
		// param => ["주부", "낚시"]
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			
			// param에서 하나씩 꺼내 ps의 '?'를 채워준다.
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
			}
			
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		
		return row;
	}
	
	public List<Map<String, Object>> selectList(String sql) {
		// slq => "SELECT * FROM MEMBER"
		// slq => "SELECT * FROM JAVA_BOARD"
		// slq => "SELECT * FROM JAVA_BOARD WHERE NUM > 10"
		List<Map<String, Object>> rowList = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			rowList = new ArrayList<>();
			// select된 행들이 저장될 빈 목록을 만든다.
			while(rs.next()) {
				Map<String, Object> row = new HashMap<>();
				// 한 행을 담을 Map을 만들어준다.
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
					// 행 정보를 담는다 => key에는 컬럼명을 value에는 값을 담는다.
				}
				rowList.add(row);
				// 목록 맨 뒤에 직전에 담아놓은 행 정보를 추가한다.
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	
	public List<Map<String, Object>> selectList(String sql, List<Object> param) {
		// sql => "SELECT * FROM MEMBER WHERE MEM_ADD1 LIKE '%'||?||'%'"
		// sql => "SELECT * FROM JAVA_BOARD WHERE WRITER=?"
		List<Map<String, Object>> rowList = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			rowList = new ArrayList<>();
			// select된 행들이 저장될 빈 목록을 만든다.
			while(rs.next()) {
				Map<String, Object> row = new HashMap<>();
				// 한 행을 담을 Map을 만들어준다.
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
					// 행 정보를 담는다 => key에는 컬럼명을 value에는 값을 담는다.
				}
				rowList.add(row);
				// 목록 맨 뒤에 직전에 담아놓은 행 정보를 추가한다.
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	
	public int update(String sql) {
		// sql => "DELETE FROM JAVA_BOARD"
		// sql => "INSERT INTO JAVA_MEMBER (MEM_ID, MEM_PASS, MEM_NAME)" VALUES ('a001', '1234', '홍길동')"
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}
	
	public int update(String sql, List<Object> param) {
		// sql => "DELETE FROM JAVA_BOARD WHERE WRITER=?"
		// sql => "INSERT INTO JAVA_MEMBER (MEM_ID, MEM_PASS, MEM_NAME) VALUES (?, ?, ?)"
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i + 1, param.get(i));
				// List의 index는 0 부터, PreparedStatement의 index(물음표의 인덱스)는 1부터
			}
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}
	
	public int join(String sql,MemberVO vo) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);

			ps.setObject(1, vo.getMemId());
			ps.setObject(2, vo.getMemPass());
			ps.setObject(3, vo.getMemName());
			ps.setObject(4, vo.getMemHp());
			
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}
	
	public int joinPlus(String sql,MemberVO vo) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			
			ps.setObject(1, vo.getMemId());
			ps.setObject(2, vo.getMemPass());
			ps.setObject(3, vo.getMemName());
			ps.setObject(4, vo.getMemHp());
			ps.setObject(5, vo.getMemGender());
			ps.setObject(6, vo.getMemBir());
			ps.setObject(7, vo.getMemAdd());
			ps.setObject(8, vo.getMemEmail());
			
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}
	
	public Map<String, Object> login(String sql, List<String> param) {
		Map<String, Object> row = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setMemId(rs.getString("MEM_ID"));
				memberVO.setMemPass(rs.getString("MEM_PASS"));
				row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					
					String key = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return row;
	}
	
	public List<Object> getMemberInfo(String sql, MemberVO vo) {
		List<Object> row = new ArrayList<>();
		MemberVO memberVO = new MemberVO();
		memberVO=vo;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				memberVO.setMemNumber(rs.getString("MEM_NUM"));
				memberVO.setMemPass(rs.getString("MEM_PASS"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemHp(rs.getString("MEM_HP"));
				memberVO.setMemBir(rs.getString("MEM_BIR"));
				memberVO.setMemAdd(rs.getString("MEM_ADD"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				
				row.add(memberVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return row;
	}
	
	public int updateInfo(String sql) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			// List의 index는 0 부터, PreparedStatement의 index(물음표의 인덱스)는 1부터
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}

	public int updateMember(String sql, MemberVO vo) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			// List의 index는 0 부터, PreparedStatement의 index(물음표의 인덱스)는 1부터
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}
	
	public List<BookVO> getBook(String sql, List<Integer> param) {
		List<BookVO> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setInt(i + 1, param.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				BookVO bookVo = new BookVO();
				bookVo.setBookNumber(rs.getInt("BOOK_NUMBER"));
				bookVo.setBookName(rs.getString("BOOK_NAME"));
				bookVo.setBookWriter(rs.getString("BOOK_WRITER"));
				bookVo.setBookISBN(rs.getString("BOOK_ISBN"));
				bookVo.setBookType(rs.getString("BOOK_TYPE"));
				bookVo.setBookRegist(rs.getString("BOOK_REGIST"));
				bookVo.setBookRentable(rs.getString("BOOK_RENTABLE"));
				rowList.add(bookVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	
	public List<BookVO> getSerchBook(String sql, List<Object> param) {
		List<BookVO> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, (String) param.get(0));
			ps.setInt(2, (int) param.get(1));
			ps.setInt(3, (int) param.get(2));
			rs = ps.executeQuery();
			while(rs.next()) {
				BookVO bookVo = new BookVO();
				bookVo.setBookNumber(rs.getInt("BOOK_NUMBER"));
				bookVo.setBookName(rs.getString("BOOK_NAME"));
				bookVo.setBookWriter(rs.getString("BOOK_WRITER"));
				bookVo.setBookISBN(rs.getString("BOOK_ISBN"));
				bookVo.setBookType(rs.getString("BOOK_TYPE"));
				bookVo.setBookRegist(rs.getString("BOOK_REGIST"));
				bookVo.setBookRentable(rs.getString("BOOK_RENTABLE"));
				rowList.add(bookVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	
	public int bookInsert(String sql, BookVO bookVO) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			
			ps.setObject(1, bookVO.getBookName());
			ps.setObject(2, bookVO.getBookWriter());
			ps.setObject(3, bookVO.getBookISBN());
			ps.setObject(4, bookVO.getBookType());
			
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return result;
	}
	
	public List<MemBookVO> getWishBook(String sql) {
		List<MemBookVO> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemBookVO membookVO = new MemBookVO();
				membookVO.setMemBookWish(rs.getString("MEMBOOK_WISH"));
				membookVO.setMemBookDate(rs.getString("MEMBOOK_DATE"));
				membookVO.setMemNum(rs.getString("MEM_NUM"));
				membookVO.setMemBookNum(rs.getString("MEMBOOK_NUM"));
				
				rowList.add(membookVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	public List<MemBookVO> getInterestBook(String sql) {
		List<MemBookVO> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemBookVO membookVO = new MemBookVO();
				membookVO.setMemBookWish(rs.getString("MEMBOOK_WISH"));
				membookVO.setMemBookDate(rs.getString("MEMBOOK_DATE"));
				membookVO.setbookNumber(rs.getString("BOOK_NUMBER"));
				membookVO.setMemNum(rs.getString("MEM_NUM"));
				membookVO.setMemBookNum(rs.getString("MEMBOOK_NUM"));
				membookVO.setMemBookName(rs.getString("BOOK_NAME"));
				
				rowList.add(membookVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	
	public List<Object> getRentList(String sql){
		List<Object> row = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				// 책 정보 불러오기
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return row;
		
	}

	public List<List<Object>> getRentReserveList(String sql){
		List<List<Object>> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();		
			while(rs.next()) {
				List<Object> row = new ArrayList<>();
				RentVO rentVO = new RentVO();
				BookVO bookVo = new BookVO();
				bookVo.setBookNumber(rs.getInt("BOOK_NUMBER"));
				bookVo.setBookName(rs.getString("BOOK_NAME"));
				bookVo.setBookWriter(rs.getString("BOOK_WRITER"));
				bookVo.setBookISBN(rs.getString("BOOK_ISBN"));
				bookVo.setBookType(rs.getString("BOOK_TYPE"));
				bookVo.setBookRegist(rs.getString("BOOK_REGIST"));
				bookVo.setBookRentable(rs.getString("BOOK_RENTABLE"));
				
				rentVO.setRentNum(rs.getString("RENT_NUM"));
				rentVO.setRentStart(rs.getString("RENT_START"));
				rentVO.setRentEnd(rs.getString("RENT_END"));
				rentVO.setRentExtend(rs.getString("RENT_EXTEND"));
				rentVO.setMemNum(rs.getString("MEM_NUM"));
				rentVO.setBookNumber(rs.getInt("BOOK_NUMBER"));
				rentVO.setBookReservation(rs.getString("BOOK_RESERVATION"));
				
				row.add(bookVo);
				row.add(rentVO);
				rowList.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
		
	}
	public List<List<Object>> getRentReserveLietAdmin(String sql) {
		List<List<Object>> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();		
			while(rs.next()) {
				List<Object> row = new ArrayList<>();
				MemberVO memberVO = new MemberVO();
				RentVO rentVO = new RentVO();
				BookVO bookVo = new BookVO();
				bookVo.setBookNumber(rs.getInt("BOOK_NUMBER"));
				bookVo.setBookName(rs.getString("BOOK_NAME"));
				bookVo.setBookWriter(rs.getString("BOOK_WRITER"));
				bookVo.setBookISBN(rs.getString("BOOK_ISBN"));
				bookVo.setBookType(rs.getString("BOOK_TYPE"));
				bookVo.setBookRegist(rs.getString("BOOK_REGIST"));
				bookVo.setBookRentable(rs.getString("BOOK_RENTABLE"));
				
				rentVO.setRentNum(rs.getString("RENT_NUM"));
				rentVO.setRentStart(rs.getString("RENT_START"));
				rentVO.setRentEnd(rs.getString("RENT_END"));
				rentVO.setRentExtend(rs.getString("RENT_EXTEND"));
				rentVO.setMemNum(rs.getString("MEM_NUM"));
				rentVO.setBookNumber(rs.getInt("BOOK_NUMBER"));
				rentVO.setBookReservation(rs.getString("BOOK_RESERVATION"));
				
				memberVO.setMemNumber(rs.getString("MEM_NUM"));
				memberVO.setMemId(rs.getString("MEM_ID"));
				memberVO.setMemPass(rs.getString("MEM_PASS"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemHp(rs.getString("MEM_HP"));
				memberVO.setMemBir(rs.getString("MEM_BIR"));
				memberVO.setMemAdd(rs.getString("MEM_ADD"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				row.add(bookVo);
				row.add(rentVO);
				row.add(memberVO);
				rowList.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return rowList;
	}
	
	public int updateReserveRent(String sql) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			result =ps.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return result;
	}
	
	public int updateBook(String sql) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		
		return result;
	}
	
	public List<BoardVO> getBoardList(String sql) {
		List<BoardVO> rowlist = new ArrayList<BoardVO>();
		
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setPostNum(rs.getInt("POST_NUM"));
				boardVO.setPostTitle(rs.getString("POST_TITLE"));
				boardVO.setPostContent(rs.getString("POST_CONTENT"));
				boardVO.setPostDate(rs.getString("POST_DATE"));
				boardVO.setMemName(rs.getString("MEM_NAME"));
				boardVO.setBoardNum(rs.getInt("BOARD_NUM"));
				rowlist.add(boardVO);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		
		return rowlist;
	}
	
	
	public List<BoardVO> getBoardList(String sql, List<Integer> param) {
		List<BoardVO> rowlist = new ArrayList<BoardVO>();
		
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setInt(i + 1, param.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setPostNum(rs.getInt("POST_NUM"));
				boardVO.setPostTitle(rs.getString("POST_TITLE"));
				boardVO.setPostContent(rs.getString("POST_CONTENT"));
				boardVO.setPostDate(rs.getString("POST_DATE"));
				boardVO.setMemName(rs.getString("MEM_NAME"));
				boardVO.setBoardNum(rs.getInt("BOARD_NUM"));
				rowlist.add(boardVO);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		
		return rowlist;
		
	}
	
	public List<BoardVO> getboardInfo(String sql) {
		List<BoardVO> rowlist = new ArrayList<BoardVO>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setPostNum(rs.getInt("POST_NUM"));
				boardVO.setPostTitle(rs.getString("POST_TITLE"));
				boardVO.setPostContent(rs.getString("POST_CONTENT"));
				boardVO.setPostDate(rs.getString("POST_DATE"));
				boardVO.setMemName(rs.getString("MEM_NAME"));
				boardVO.setBoardNum(rs.getInt("BOARD_NUM"));
				boardVO.setMemNum(rs.getInt("MEM_NUM"));
				rowlist.add(boardVO);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return rowlist;
	}
	
	public List<BookVO> getRentBook(String sql) {
		List<BookVO> rowList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setBookNumber(rs.getInt("BOOK_NUMBER"));
				bookVO.setBookName(rs.getString("BOOK_NAME"));
				bookVO.setBookWriter(rs.getString("BOOK_WRITER"));
				bookVO.setBookISBN(rs.getString("BOOK_ISBN"));
				bookVO.setBookType(rs.getString("BOOK_ISBN"));
				bookVO.setBookRegist(rs.getString("BOOK_REGIST"));
				bookVO.setBookRentable(rs.getString("BOOK_RENTABLE"));
				rowList.add(bookVO);	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return rowList;
	}
	
	public List<ComentVO> comentList(String sql){
		List<ComentVO> rowlist = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ComentVO comentVO = new ComentVO();
				comentVO.setComentNum(rs.getInt("COMENT_NUM"));
				comentVO.setComentContent(rs.getString("COMENT_CONTENT"));
				comentVO.setComentDate(rs.getString("COMENT_DATE"));
				comentVO.setMemName(rs.getString("MEM_NAME"));
				comentVO.setPostNum(rs.getInt("POST_NUM"));
				comentVO.setMemNum(rs.getInt("MEM_NUM"));
				rowlist.add(comentVO);	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try {conn.close();} catch (Exception e) {} 
			if(ps != null) try {ps.close();} catch (Exception e) {} 
			if(rs != null) try {rs.close();} catch (Exception e) {} 
		}
		return rowlist;
	}
	
	public int rentMemNumCheck(String sql) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("MEM_NUM");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) try { conn.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(ps != null) try { ps.close(); } catch (Exception e) {}
		}
		return result;
	}
	
	
	
}