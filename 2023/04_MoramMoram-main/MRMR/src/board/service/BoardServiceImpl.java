package board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import vo.BoardVO;
import vo.PageVO;

public class BoardServiceImpl implements BoardService {
	private static BoardService instance = null;
	private BoardDao dao = null;

	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}

	public static BoardService getInstance() {
		if (instance == null)
			instance = new BoardServiceImpl();
		return instance;
	}

	@Override
	public int insertBoard(BoardVO boardVo) throws SQLException {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardVo) throws SQLException {
		return dao.deleteBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) throws SQLException {
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<BoardVO> getAllBoardList(Map<String, Object>  map) throws SQLException {
		return dao.getAllBoardList(map);
	}

	@Override
	public BoardVO getBoard(int boardNo) throws SQLException {
		return dao.getBoard(boardNo);
	}

	@Override
	public int setCountIncrement(int boardNo) throws SQLException {
		return dao.setCountIncrement(boardNo);
	}

	@Override
	public PageVO pageInfo(int page, String stype, String sword, int boardCategoryNo) {
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("boardCategoryNo", boardCategoryNo);
		int count = this.getTotalCount(map);
		
		int start = (page-1) * PageVO.getPerList() + 1;
		int end = start + PageVO.getPerList() - 1;
		if(end > count)  end = count;
			
		int totalPage =  (int)Math.ceil((double)count / PageVO.getPerList());
		
		int startPage = ((page-1) /PageVO.getPerPage() * PageVO.getPerPage() ) + 1;
		int endPage = startPage + PageVO.getPerPage() - 1;
		if(endPage > totalPage ) endPage = totalPage;
		
		PageVO  vo = new PageVO();
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);
		
		return vo;
	}

	@Override
	public int getTotalCount(Map<String, Object> map) {
		int   count = 0;
		try {
			count = dao.getTotalCount(map);
		} catch (SQLException e) {
			System.out.println("getTotalCount 오류");
			e.printStackTrace();
		}
		return count;
	}

}
