package files.service;

import java.sql.SQLException;
import java.util.Map;

import files.dao.FilesDao;
import files.dao.FilesDaoImpl;
import vo.FilesVO;

public class FilesServiceImpl implements FilesService{
	private static FilesService instance = null;
	private FilesDao dao = null;
	private FilesServiceImpl() {
		dao = FilesDaoImpl.getInstance();
	}
	public static FilesService getInstance() {
		if(instance == null) instance = new FilesServiceImpl();
		return instance;
	}
	@Override
	public void insertFiles(Map<String, Object> map) throws SQLException {
		dao.insertFiles(map);
	}
	@Override
	public String selectFileCategoty(int file_category_no) throws SQLException {
		return dao.selectFileCategoty(file_category_no);
	}
	@Override
	public FilesVO selectFiles(Map<String, Object> map) throws SQLException {
		return dao.selectFiles(map);
	}
	
	@Override
	public int updateFiles(Map<String, Object> map) throws SQLException {
		return dao.updateFiles(map);
	}
	
	@Override
	public int searchFile(Map<String, Object> map) throws SQLException {
		return dao.searchFile(map);
	}

}
