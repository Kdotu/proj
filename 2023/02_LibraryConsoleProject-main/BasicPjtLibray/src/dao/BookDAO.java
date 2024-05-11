package dao;

import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDAO {
	private static BookDAO instance = null;
	private BookDAO() {}
	public static BookDAO getInstance() {
		if(instance == null) instance = new BookDAO();
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<BookVO> getList(List<Integer> rowNum) {
		String sql = "SELECT * "
				+ " FROM (SELECT ROWNUM AS RN, BOOK_NUMBER, BOOK_NAME, BOOK_WRITER, BOOK_ISBN, BOOK_TYPE, BOOK_REGIST, BOOK_RENTABLE "
				+ " FROM BOOK ORDER BY BOOK_NUMBER) A "
				+ " WHERE A.RN > ? "
				+ " AND A.RN <= ? ";
		List<Integer> param = rowNum;
		return jdbc.getBook(sql, param);
	}
	
	public List<BookVO> getSerchBook(int input, List<Object> list) {
		String sql = "";
		if(input == 1) sql = "SELECT * "
				+ " FROM (SELECT ROWNUM AS RN, BOOK_NUMBER, BOOK_NAME, BOOK_WRITER, BOOK_ISBN, BOOK_TYPE, BOOK_REGIST, BOOK_RENTABLE "
				+ " FROM BOOK "
				+ " WHERE BOOK_NAME LIKE '%'||?||'%' "
				+ " ORDER BY BOOK_NUMBER) A "
				+ " WHERE A.RN > ? "
				+ " AND A.RN <= ? ";
		if(input == 2) sql = "SELECT * "
				+ " FROM (SELECT ROWNUM AS RN, BOOK_NUMBER, BOOK_NAME, BOOK_WRITER, BOOK_ISBN, BOOK_TYPE, BOOK_REGIST, BOOK_RENTABLE "
				+ " FROM BOOK "
				+ " WHERE BOOK_WRITER LIKE '%'||?||'%' "
				+ " ORDER BY BOOK_NUMBER) A "
				+ " WHERE A.RN > ? "
				+ " AND A.RN <= ? ";
		List<Object> param = list;
		return jdbc.getSerchBook(sql, param);
	}
	
	public int insertBook(List<BookVO> bookVoList) {
		int result = 0;
		for(int i = 0; i < bookVoList.size(); i++) {
			String sql = "INSERT INTO BOOK(BOOK_NUMBER,BOOK_NAME,BOOK_WRITER,BOOK_ISBN,BOOK_TYPE,BOOK_REGIST)"
						+ " VALUES(SEQ_BOOK_NUM.NEXTVAL, '"
						+ bookVoList.get(i).getBookName() + "', '"
						+ bookVoList.get(i).getBookWriter() + "', '"
						+ bookVoList.get(i).getBookISBN() + "', "
						+ Integer.parseInt(bookVoList.get(i).getBookType()) + ", "
						+ "SYSDATE) ";
			result = jdbc.updateBook(sql);
		}
		return result;
	}
	public int deleteBook(BookVO deleteBook) {
		int result = 0;
		
		String sql = "DELETE FROM BOOK WHERE BOOK_NUMBER= " + deleteBook.getBookNumber();
		result = jdbc.updateBook(sql);
		
		return result;
	}
	public List<BookVO> getRentBook() {
		String sql = "SELECT * FROM BOOK "
				+ "WHERE BOOK_RENTABLE = 'N'";
		return jdbc.getRentBook(sql);
	}
}