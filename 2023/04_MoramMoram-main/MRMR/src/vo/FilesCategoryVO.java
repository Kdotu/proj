package vo;

public class FilesCategoryVO {
	private int file_category_no;
	private String file_category_content;
	private String file_category_path;

	public FilesCategoryVO() { }

	public FilesCategoryVO(int file_category_no, String file_category_content, String file_category_path) {
		this.file_category_no = file_category_no;
		this.file_category_content = file_category_content;
		this.file_category_path = file_category_path;
	}

	public int getFile_category_no() {
		return file_category_no;
	}

	public void setFile_category_no(int file_category_no) {
		this.file_category_no = file_category_no;
	}

	public String getFile_category_content() {
		return file_category_content;
	}

	public void setFile_category_content(String file_category_content) {
		this.file_category_content = file_category_content;
	}

	public String getFile_category_path() {
		return file_category_path;
	}

	public void setFile_category_path(String file_category_path) {
		this.file_category_path = file_category_path;
	}

}
