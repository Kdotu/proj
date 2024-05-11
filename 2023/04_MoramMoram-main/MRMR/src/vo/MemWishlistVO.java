package vo;

public class MemWishlistVO {
	private int wish_no;
	private int mem_no;
	private int service_no;
	
	public MemWishlistVO() { }
	
	public MemWishlistVO(int wish_no, int mem_no, int service_no) {
		super();
		this.wish_no = wish_no;
		this.mem_no = mem_no;
		this.service_no = service_no;
	}
	public int getWish_no() {
		return wish_no;
	}
	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getService_no() {
		return service_no;
	}
	public void setService_no(int service_no) {
		this.service_no = service_no;
	}
	
	
	
}
