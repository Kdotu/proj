package vo;

import util.SpaceUtil;

public class BookVO {
	// BookDAO에서 받아온 도서정보를 저장할 객체
	private int bookNumber; // 도서번호
	private String bookName; // 도서명
	private String bookWriter; // 저자
	private String bookISBN; // ISBN
	private String bookType; // 도서분류번호
	private String bookRegist; // 도서입고일
	private String bookRentable; // 대여여부
	
	
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	
	public String getBookRegist() {
		return bookRegist;
	}
	public void setBookRegist(String bokRegist) {
		this.bookRegist = bokRegist;
	}
	
	public String getBookRentable() {
		return bookRentable;
	}
	public void setBookRentable(String bookRentable) {
		this.bookRentable = bookRentable;
	}
	@Override
	public String toString() {
		return SpaceUtil.format(bookNumber, 5, 1) + 
				SpaceUtil.format(bookName, 30, 1) + 
				SpaceUtil.format(bookWriter, 30, 1) + 
				SpaceUtil.format(bookISBN, 15, 1) + 
				SpaceUtil.format(bookType, 3, 1) + 
				SpaceUtil.format(bookRegist, 15, 1) + 
				SpaceUtil.format(bookRentable, 3, 0);
	}
	
	
}