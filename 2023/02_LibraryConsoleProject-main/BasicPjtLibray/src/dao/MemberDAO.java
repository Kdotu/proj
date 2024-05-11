package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.BookVO;
import vo.MemberVO;

public class MemberDAO {
	private static MemberDAO instance = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance == null) instance = new MemberDAO();
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public int join(MemberVO vo){
		// 필수정보
		String sql = " INSERT INTO MEMBER(MEM_NUM, MEM_ID, MEM_PASS, MEM_NAME, MEM_HP) "
				   + " VALUES (SEQ_MEM_NUM.NEXTVAL,?,?,?,? ) ";
		return jdbc.join(sql,vo);
	}
	
	public int joinPlus(MemberVO vo){
		// 필수+선택정보
		// 성별, 생년월일, 주소, 이메일주소
		String sql = "INSERT INTO MEMBER (MEM_NUM,MEM_ID,MEM_PASS,MEM_NAME,MEM_HP, MEM_GENDER, MEM_BIR, MEM_ADD, MEM_EMAIL)"
				+ "VALUES (SEQ_MEM_NUM.NEXTVAL,?,?,?,?,?,TO_DATE(?),?,? )";
		return jdbc.joinPlus(sql,vo);
	}
	
	public Map<String, Object> login(List<String> param) { 
		String sql = " SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PASS = ? ";
		return jdbc.getInstance().selectOne(sql,param);
	}
	
	public List<Object> memberInfo(MemberVO vo) { // 회원정보 검색
		String Id = vo.getMemId();
		String sql = " SELECT MEM_NUM,MEM_ID,MEM_PASS,MEM_NAME,MEM_HP, MEM_GENDER,MEM_BIR, MEM_ADD, MEM_EMAIL "
				   + " FROM MEMBER "
				   + " WHERE MEM_ID = '" + Id + "'";
		return jdbc.getMemberInfo(sql,vo);
	}
	
	public int memberUpdate(List<String> param, MemberVO vo) {
		String sql = " UPDATE MEMBER SET "+ param.get(0) + " = '" + param.get(1)+ "' "
				   + " WHERE MEM_NUM = " + vo.getMemNumber();
		return jdbc.updateInfo(sql);
	}
	
	public List<Object> memberInfoPlus(MemberVO vo) { // 관리자가 검색?
		String sql = " SELECT B.BOOK_NAME, C.MEM_NAME " 
				+ "   FROM RENT A, BOOK B, MEMBER C "
				+ "  WHERE A.BOOK_NUMBER = B.BOOK_NUMBER "
				+ "    AND A.MEM_NUM = C.MEM_NUM "
				+ "    AND A.MEM_NUM = ?";
		return jdbc.getMemberInfo(sql,vo);
	}
	
	public int bookInsert(BookVO bookVO) {
		String sql = "  INSERT INTO BOOK(BOOK_NUMBER, BOOK_NAME, BOOK_WRITER, BOOK_ISBN, BOOK_TYPE, BOOK_REGIST, BOOK_RENTABLE) "
				   + "	VALUES (SEQ_BOOK_NUM.NEXTVAL,?,?,TO_NUMBER(?),TO_NUMBER(?),SYSDATE,'Y') ";
		return jdbc.bookInsert(sql,bookVO);
		
	}
	
	public int bookRemove(BookVO bookVO) {
		String sql = " DELETE FROM BOOK WHERE BOOK_NUMBER = " + bookVO.getBookNumber();
		return jdbc.update(sql);
	}
	
	public int idCheck(String id) {
		int status = 1;
		String sql = " SELECT MEM_ID FROM MEMBER WHERE MEM_ID = '" + id + "'";
		Map<String, Object> member = jdbc.selectOne(sql);
		
		if(member != null && member.get("MEM_ID").equals(id)) {
			status = 0;
		}
		return status;
	}
	
	public Map<String, Object> findId(List<String> param) {
		String sql = " SELECT MEM_ID FROM MEMBER "
				   + " WHERE MEM_NAME = '" + param.get(0) + "'"
				   + "   AND MEM_HP = " + param.get(1);
		return jdbc.selectOne(sql);
	}
	
	public Map<String, Object> findPw(List<String> param) {
		String sql = " SELECT MEM_PASS FROM MEMBER "
				   + " WHERE MEM_ID = '" + param.get(0) + "'"
				   + "   AND MEM_HP = "+ param.get(1);
		return jdbc.selectOne(sql);
	}
	
	public int extendBook(MemberVO loginInfo) {
		String sql = " UPDATE RENT(RENT_EXTEND)"
				   + "    SET ('Y')"
				   + " WHERE MEM_NUM = '" + loginInfo.getMemNumber() + "' "
  				   + "   AND BOOK_NUMBER = ?";
		return jdbc.updateInfo(sql);
	}
	
	public int returnBook(MemberVO loginInfo) {
		String sql = " UPDATE RENT(RENT_END)"
				   + "    SET (SYSDATE) "
				   + " WHERE MEM_NUM = '" + loginInfo.getMemNumber() + "' "
		           + "   AND BOOK_NUMBER = ?";
		return jdbc.updateInfo(sql);
	}
	
	public int cancelBook(MemberVO loginInfo) {
		String sql = " UPDATE RENT(BOOK_RESERVATION)"
				   + "    SET (NULL) "
				   + " WHERE MEM_NUM = '" + loginInfo.getMemNumber() + "' "
				   + "   AND BOOK_NUMBER = ?";
		return jdbc.updateInfo(sql);
	}

}





