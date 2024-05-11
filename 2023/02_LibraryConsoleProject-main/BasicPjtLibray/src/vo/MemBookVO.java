package vo;

public class MemBookVO {
	private String memNum;
	private String memBookWish;
	private String memBookDate;
	private String bookNumber;
	private String memBookNum;
	private String memBookName;

	public String getMemBookName() {
		return memBookName;
	}
	public void setMemBookName(String memBookName) {
		this.memBookName = memBookName;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getMemBookWish() {
		return memBookWish;
	}
	public void setMemBookWish(String memBookWish) {
		this.memBookWish = memBookWish;
	}
	public String getMemBookDate() {
		return memBookDate;
	}
	public void setMemBookDate(String memBookDate) {
		this.memBookDate = memBookDate;
	}
	public String getbookNumber() {
		return bookNumber;
	}
	public void setbookNumber(String membookNumber) {
		this.bookNumber = membookNumber;
	}
	public String getMemBookNum() {
		return memBookNum;
	}
	public void setMemBookNum(String memBookNum) {
		this.memBookNum = memBookNum;
	}
	
	@Override
	public String toString() {
		return "MemBookVO [memNum=" + memNum + ", memBookWish=" + memBookWish + ", memBookDate=" + memBookDate
				+ ", memBookBookNumber=" + bookNumber + ", memBookNum=" + memBookNum + "]";
	}
	
}