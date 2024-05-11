package dao;

import java.util.List;

import util.JDBCUtil;
import vo.ComentVO;

public class ComentDAO {

	private static ComentDAO instance = null;
	private ComentDAO() {}
	public static ComentDAO getInstance() {
		if(instance == null) instance = new ComentDAO();
		return instance;
}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<ComentVO> comentlist(int showNum) {
		String sql = "SELECT A.COMENT_NUM,A.COMENT_CONTENT,TO_CHAR(A.COMENT_DATE,'YYYY-MM-DD') AS COMENT_DATE,B.MEM_NAME,A.POST_NUM,A.MEM_NUM\r\n" + 
				"				 FROM COMENT A, MEMBER B, POST C \r\n" + 
				"				WHERE A.MEM_NUM = B.MEM_NUM\r\n" + 
				"				AND A.POST_NUM = C.POST_NUM \r\n" + 
				"				 AND C.POST_NUM = " + showNum +
				"				ORDER BY A.COMENT_NUM ";
				
		return jdbc.comentList(sql);
	}
	
	
	public int insertComent(List<Object> param) {
		String sql = " INSERT INTO COMENT(COMENT_NUM, COMENT_CONTENT,  COMENT_DATE, MEM_NUM, POST_NUM )\r\n" + 
				"     VALUES (SEQ_COMENT_NUM.NEXTVAL, ? , TO_CHAR(SYSDATE,'YYYY-MM-DD'), ? , ? ) ";
		return jdbc.update(sql,param);	
	}
	
	
	
	public int deleteComent(int delcomentNum) {
		String sql = "DELETE FROM COMENT\r\n" + 
				"  WHERE COMENT_NUM= "+  delcomentNum;	
		return jdbc.update(sql);
	}
	public int updateComent(String comment, int delcomentNum) {
		String sql = "UPDATE COMENT \r\n" + 
				"  SET COMENT_CONTENT = '" + comment + "'" 
				+"  WHERE COMENT_NUM = " +delcomentNum;
		return jdbc.update(sql);
	}

		
	}
	
	
	
	
	
	