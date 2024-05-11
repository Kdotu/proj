package vo;

public class RentVO {
	private String rentNum;
	private String rentStart;
	private String rentEnd;
	private String rentExtend;
	private String memNum;
	private int bookNumber;
	private String bookReservation;
	
	public String getRentNum() {
		return rentNum;
	}
	public void setRentNum(String rentNum) {
		this.rentNum = rentNum;
	}
	public String getRentStart() {
		return rentStart;
	}
	public void setRentStart(String rentStart) {
		this.rentStart = rentStart;
	}
	public String getRentEnd() {
		return rentEnd;
	}
	public void setRentEnd(String rentEnd) {
		this.rentEnd = rentEnd;
	}
	public String getRentExtend() {
		return rentExtend;
	}
	public void setRentExtend(String rentExtend) {
		this.rentExtend = rentExtend;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getBookReservation() {
		return bookReservation;
	}
	public void setBookReservation(String bookReservation) {
		this.bookReservation = bookReservation;
	}
	
	@Override
	public String toString() {
		return "RentVO [rentNum=" + rentNum + ", rentStart=" + rentStart + ", rentEnd=" + rentEnd + ", rentExtend="
				+ rentExtend + ", memNum=" + memNum + ", bookNumber=" + bookNumber + ", bookReservation="
				+ bookReservation + "]";
	}
	
}