package dao;

import java.util.List;

import util.JDBCUtil;

public class RentDAO {
	private static RentDAO instance = null;
	private RentDAO() {}
	public static RentDAO getInstance() {
		if(instance == null) instance = new RentDAO(); 
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public int doReserve(String memNum, int bookNum) {
		String sql = " INSERT INTO RENT (RENT_NUM, MEM_NUM, "
				+ "  BOOK_NUMBER, BOOK_RESERVATION) "
				+ " VALUES (SEQ_RENT.NEXTVAL, "+ memNum +" , "+ bookNum + ",SYSDATE) ";
		return jdbc.updateReserveRent(sql);
	}
	
	public int doRent(String memNum, int bookNum) {
		String sql = "INSERT INTO RENT (RENT_NUM, RENT_START, "
				+ " MEM_NUM, BOOK_NUMBER) "
				+ " VALUES (SEQ_RENT.NEXTVAL, SYSDATE, "
				+  memNum + " , " + bookNum + " ) ";
		return jdbc.updateReserveRent(sql);
	}
	
	public int doRent(int bookNum) {
		String sql = " UPDATE RENT "
				+ " SET RENT_START = SYSDATE "
				+ " WHERE BOOK_NUMBER =" + bookNum;
				
		return jdbc.updateReserveRent(sql);
	}
	
	public int setReturn(int bookNum) {
		String sql = " UPDATE RENT "
				+ " SET RENT_END = SYSDATE "
				+ " WHERE BOOK_NUMBER = " + bookNum ;
		return jdbc.updateReserveRent(sql);
	}
	
	public int setextend(int bookNumber) {
		String sql = " UPDATE RENT "
				+ " SET RENT_EXTEND = 'O' "
				+ " WHERE BOOK_NUMBER = " + bookNumber;
		return jdbc.updateReserveRent(sql);
	}
	
	public List<List<Object>> showRentList(String param) {
		String sql = " SELECT A.*,B.* "
				+ " FROM RENT A, BOOK B, MEMBER C"
				+ " WHERE A.BOOK_NUMBER=B.BOOK_NUMBER "
				+ " AND A.MEM_NUM=C.MEM_NUM"
				+ " AND C.MEM_ID = '" + param + "' "
				+ " AND A.RENT_START IS NOT NULL "
				+ " AND A.RENT_END IS NULL "
				+ " ORDER BY RENT_START ";
		return jdbc.getRentReserveList(sql);
	}
	
	public List<List<Object>> showRentList() {
		String sql = "SELECT A.*,B.*,C.*"
				+ " FROM RENT A, BOOK B, MEMBER C "
				+ " WHERE A.MEM_NUM=C.MEM_NUM "
				+ " AND A.BOOK_NUMBER=B.BOOK_NUMBER "
				+ " AND A.RENT_START IS NOT NULL "
				+ " AND A.RENT_END IS NULL "
				+ " ORDER BY RENT_START ";
		return jdbc.getRentReserveLietAdmin(sql);
	}
	
	public List<List<Object>> showReserveList(String param) {
		String sql = "SELECT A.*,B.*"
				+ " FROM RENT A, BOOK B,  MEMBER C"
				+ " WHERE C.MEM_ID = '" + param + "' "
				+ " AND A.MEM_NUM=C.MEM_NUM "
				+ " AND A.BOOK_NUMBER=B.BOOK_NUMBER "
				+ " AND A.BOOK_RESERVATION IS NOT NULL "
				+ " AND A.RENT_START IS NULL "
				+ " ORDER BY BOOK_RESERVATION ";
		return jdbc.getRentReserveList(sql);		
	}
	
	public List<List<Object>> showReserveList() {
		String sql = " SELECT A.*,B.*,C.* "
				+ " FROM RENT A, MEMBER B, BOOK C "
				+ " WHERE A.MEM_NUM=B.MEM_NUM "
				+ " AND A.BOOK_NUMBER=C.BOOK_NUMBER "
				+ " AND A.BOOK_RESERVATION IS NOT NULL "
				+ " AND A.RENT_START IS NULL "
				+ " ORDER BY RENT_NUM ";
		return jdbc.getRentReserveLietAdmin(sql);		
	}
	
	 public int cancelReserve(int bookNum) {
	      String sql = " DELETE FROM RENT "
	            + " WHERE BOOK_NUMBER = " + bookNum
	            + " AND RENT_START IS NULL "
	            + " AND BOOK_RESERVATION IS NOT NULL ";
	      return jdbc.updateReserveRent(sql);
	   }
	 
	public int showReservedBookList(String param, int rentBookNum) {
		 String sql = "SELECT MEM_NUM "
		 		+ " FROM RENT "
		 		+ " WHERE MEM_NUM = " + param
		 		+ " AND BOOK_NUMBER = " + rentBookNum
		 		+ " AND RENT_START IS NULL "
		 		+ " AND BOOK_RESERVATION IS NOT NULL ";
		return jdbc.rentMemNumCheck(sql);
	}
}