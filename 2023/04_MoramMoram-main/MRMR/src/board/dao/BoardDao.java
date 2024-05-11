package board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.BoardVO;


public interface BoardDao {
	/**
	 * BoardVO객체에 담겨진 자료를 DB에 insert하는 메서드 
	 * @param BoardVO DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return 작업 성공 : 1, 작업 실패 :0
	 */
	public int insertBoard(BoardVO boardVo) throws SQLException;
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글을 삭제하는 메서드
	 * @param BoardVO 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 :0
	 */
	public int deleteBoard(int boardVo) throws SQLException;
	
	/**
	 * 하나의 BoardVO객체에 저장된 자료를 DB에 update하는 메서드
	 * @param BoardVO update할 게시글 정보가 저장된 BoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 :0
	 */
	public int updateBoard(BoardVO boardVo) throws SQLException;
	
	/**
	 * DB의 BoardVO 테이블의 전체 목록을 가져오는 메서드
	 * @param board_category_no 목록을 불러올 카테고리 번호
	 * @return BoardVO객체가 저장된 List객체
	 */
	public List<BoardVO> getAllBoardList(Map<String, Object>  map) throws SQLException;
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글 정보를 검색하는 메서드
	 * @param boardNo 검색할 게시글 정보
	 * @return 검색된 게시글 정보가 저장된 BoardVO객체(해당 게시글이 없으면 null 반환)
	 */
	public BoardVO getBoard(int boardNo) throws SQLException;
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * @param BOARD_NO 조회수를 증가살 게시글 번호
	 * @return 작업 성공 :1 작업 실패 : 0
	 */
	public int setCountIncrement(int boardNo) throws SQLException;
	
	//전체 글갯수 
	public int getTotalCount(Map<String, Object>  map) throws SQLException;
	
}
