package vo;

public class BoardCommentVO {
	private int board_comment_no;
	private String board_comment_content;
	private int board_comment_level;
	private String board_comment_date;
	private int mem_no;
	private int board_no;
	private int board_comment_parents;

	public int getBoard_comment_no() {
		return board_comment_no;
	}

	public void setBoard_comment_no(int board_comment_no) {
		this.board_comment_no = board_comment_no;
	}

	public String getBoard_comment_content() {
		return board_comment_content;
	}

	public void setBoard_comment_content(String board_comment_content) {
		this.board_comment_content = board_comment_content;
	}

	public int getBoard_comment_level() {
		return board_comment_level;
	}

	public void setBoard_comment_level(int board_comment_level) {
		this.board_comment_level = board_comment_level;
	}

	public String getBoard_comment_date() {
		return board_comment_date;
	}

	public void setBoard_comment_date(String board_comment_date) {
		this.board_comment_date = board_comment_date;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getBoard_comment_parents() {
		return board_comment_parents;
	}

	public void setBoard_comment_parents(int board_comment_parents) {
		this.board_comment_parents = board_comment_parents;
	}

}
