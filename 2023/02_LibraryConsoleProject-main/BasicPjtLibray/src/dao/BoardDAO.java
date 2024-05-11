package dao;

import java.util.List;

//import service.list;
import util.JDBCUtil;
import vo.BoardVO;

public class BoardDAO {
	private static BoardDAO instance = null;
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(instance == null) instance = new BoardDAO();
		return instance;
}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<BoardVO> getBoardList(List<Integer> param){
		
//		String sql = "SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,A.BOARD_NUM "
//				+ " FROM POST A, MEMBER B "
//				+ " WHERE A.MEM_NUM = B.MEM_NUM "
//				+ " AND A.BOARD_NUM ="+board_num 
//				+ " ORDER BY A.POST_DATE DESC, A.POST_NUM DESC ";
		
		  String sql = "SELECT D.* "
		            + " FROM (SELECT ROWNUM AS RN,C.* "
		            + "   FROM (SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,A.BOARD_NUM "
		            + " FROM POST A, MEMBER B "
		            + " WHERE A.MEM_NUM = B.MEM_NUM "
		            + " AND A.BOARD_NUM = ? " 
		            + " ORDER BY A.POST_DATE DESC, A.POST_NUM DESC)C )D "
		            + " WHERE D.RN > ? "
		            + " AND D.RN <= ? ";
		return jdbc.getBoardList(sql, param);
		
		
	}
	
public List<BoardVO> getBoardList(int boardNum){
		
		String sql = "SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,A.BOARD_NUM "
				+ " FROM POST A, MEMBER B "
				+ " WHERE A.MEM_NUM = B.MEM_NUM "
				+ " AND A.BOARD_NUM ="+boardNum 
				+ " ORDER BY A.POST_DATE DESC, A.POST_NUM DESC ";
		
		return jdbc.getBoardList(sql);
		
		
	}
	
	public int insert(List<Object> param) {
		String sql = "INSERT INTO POST(POST_NUM,POST_TITLE,POST_CONTENT,POST_DATE,MEM_NUM,BOARD_NUM) "
				   + " VALUES (SEQ_BOARDNUM.NEXTVAL, ? , ? , SYSDATE, ?, ?)" ;
		return jdbc.update(sql,param);
	}
	public List<BoardVO> getboardInfo(int showNum) { //한줄
		String sql = " SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,B.MEM_NUM,A.BOARD_NUM "
					+ " FROM POST A, MEMBER B"
					+ " WHERE A.MEM_NUM = B.MEM_NUM "
					+ " AND A.POST_NUM = "+showNum;
		return jdbc.getboardInfo(sql);
		
		
	}
	public int deletePost(int post_num) {
		String sql = "DELETE FROM POST "
				+ " WHERE POST_NUM = " +post_num;
		return jdbc.update(sql);
		
	}
		

	
	public int modifyPost(List<Object> param) {
		String sql = " UPDATE POST "
					+ " SET POST_TITLE = ? "
					+ "  ,POST_CONTENT = ? "
					+ " WHERE POST_NUM = ? ";
			return jdbc.update(sql,param);	
					
	} 
	
	

	public List<BoardVO> search(int input,String con, int board_num) {
		String sql = "";
		if(input == 1 ) { sql = "SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,A.BOARD_NUM "
				+ " FROM POST A, MEMBER B "
				+ " WHERE A.MEM_NUM = B.MEM_NUM "
				+ " AND A.POST_TITLE LIKE '%" + con + "%' " 
				+ "AND A.BOARD_NUM ="+board_num;
		}
		
		if(input == 2) { sql = "SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,A.BOARD_NUM "
				+ " FROM POST A, MEMBER B "
				+ " WHERE A.MEM_NUM = B.MEM_NUM "
				+ " AND B.MEM_NAME LIKE '%" + con + "%' " 
				+ "AND A.BOARD_NUM ="+board_num;
		}

		return jdbc.getBoardList(sql);
	}
	public List<BoardVO> mypost(int memNum) {
		String sql = "SELECT A.POST_NUM,A.POST_TITLE,A.POST_CONTENT,TO_CHAR(A.POST_DATE,'YYYY-MM-DD') AS POST_DATE,B.MEM_NAME,A.BOARD_NUM "
				+ " FROM POST A, MEMBER B "
				+ " WHERE A.MEM_NUM = B.MEM_NUM "
				+ " AND A.MEM_NUM = " + memNum 
				+ " ORDER BY A.POST_NUM DESC ";
		return jdbc.getBoardList(sql);
	}

	
	
	
	
}
	