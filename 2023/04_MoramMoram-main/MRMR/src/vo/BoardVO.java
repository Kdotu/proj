package vo;

public class BoardVO {
	private int board_no;
	private String board_title;
	private String board_content;
	private int mem_no;
	private String mem_name;
	private int board_category_no;
	private String board_date;
	private int board_hit;

	public BoardVO() {}

	public BoardVO(int board_no, String board_title, String board_content, int mem_no, String mem_name,
			int board_category_no, String board_date, int board_hit) {
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_content = board_content;
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.board_category_no = board_category_no;
		this.board_date = board_date;
		this.board_hit = board_hit;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public int getBoard_category_no() {
		return board_category_no;
	}

	public void setBoard_category_no(int board_category_no) {
		this.board_category_no = board_category_no;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public int getBoard_hit() {
		return board_hit;
	}

	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}

}
