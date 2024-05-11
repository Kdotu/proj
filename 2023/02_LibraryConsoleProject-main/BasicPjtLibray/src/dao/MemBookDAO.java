package dao;

import java.util.List;

import util.JDBCUtil;
import vo.MemberVO;
import vo.MemBookVO;

public class MemBookDAO {
	private static MemBookDAO instance = null;
	private MemBookDAO() {}
	public static MemBookDAO getInstance() {
		if(instance == null) instance = new MemBookDAO();
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();

	
	public int wishBookInsert(String bookname, MemberVO loginInfo) {
		String sql = " INSERT INTO MEMBOOK(MEMBOOK_NUM,MEM_NUM, MEMBOOK_WISH,MEMBOOK_DATE) "
				  +  " VALUES (SEQ_MEMBOOK_NUM.NEXTVAL," + loginInfo.getMemNumber() + ",'" + bookname + "', SYSDATE) ";
		return jdbc.update(sql);
	}
	
	public List<MemBookVO> wishBookList(MemberVO loginInfo) {
		String sql = " SELECT * FROM MEMBOOK "
				+ "  WHERE MEM_NUM = " + loginInfo.getMemNumber()
				+ "    AND MEMBOOK_WISH IS NOT NULL ";
		return jdbc.getWishBook(sql);
	}
	
	public int wishBookDelete(MemberVO loginInfo, int bookMem_num) {
		String sql = " DELETE FROM MEMBOOK "
				   + "  WHERE MEM_NUM = " + loginInfo.getMemNumber() 
				   + "    AND MEMBOOK_NUM = " + bookMem_num;
		return jdbc.update(sql);
	}
	
	public List<MemBookVO>  interestBookList(MemberVO loginInfo) {
		String sql = " SELECT B.*, A.BOOK_NAME "
			     	 + " FROM BOOK A, MEMBOOK B "
				     + "WHERE MEM_NUM = " + loginInfo.getMemNumber()
				     + "  AND A.BOOK_NUMBER = B.BOOK_NUMBER ";
		return jdbc.getInterestBook(sql);
	}
	public int interestBookDelete(MemberVO loginInfo, int bookMem_num) {
		String sql = " DELETE FROM MEMBOOK "
				   + " 	WHERE MEM_NUM = " + loginInfo.getMemNumber()
				   + "    AND MEMBOOK_NUM = " + bookMem_num;
		return jdbc.update(sql);
	}
	
	public int insertInterestBook(int memBookNum, int memNum) {
		String sql = "INSERT INTO MEMBOOK(MEM_NUM, MEMBOOK_DATE, BOOK_NUMBER, MEMBOOK_NUM) "
				+ "VALUES (" + memNum + ", SYSDATE, " + memBookNum + ", SEQ_MEMBOOK_NUM.NEXTVAL) ";
		return jdbc.update(sql);
	}
	public List<MemBookVO>  bookWish() {
		String sql = "	SELECT B.MEM_NAME,A.* FROM MEMBOOK A, MEMBER B"
				   + "	 WHERE A.MEM_NUM = B.MEM_NUM "
				   + "     AND MEMBOOK_WISH IS NOT NULL ";
		return jdbc.getWishBook(sql);
	}
	public int bookWishDelete(int num) {
		String sql = " DELETE FROM MEMBOOK"
				   + " WHERE MEMBOOK_NUM = " + num;
		return jdbc.update(sql);
		
	}
}