package files.dao;

import java.sql.SQLException;
import java.util.Map;

import vo.FilesVO;

public interface FilesDao {
	public void insertFiles(Map<String, Object> map) throws SQLException;
	
	public String selectFileCategoty(int file_category_no) throws SQLException;
	
	public FilesVO selectFiles(Map<String, Object> map) throws SQLException;
	
	public int updateFiles(Map<String, Object> map) throws SQLException;
	
	public int searchFile(Map<String, Object> map) throws SQLException;
}
