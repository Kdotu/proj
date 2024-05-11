package vo;

public class CartVO {
	private int cart_no;
	private int mem_no;
	private String cart_date;
	private int service_no;
	private int cart_state_no;

	public CartVO() {}

	public CartVO(int cart_no, int mem_no, String cart_date, int service_no, int cart_state_no) {
		super();
		this.cart_no = cart_no;
		this.mem_no = mem_no;
		this.cart_date = cart_date;
		this.service_no = service_no;
		this.cart_state_no = cart_state_no;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getCart_date() {
		return cart_date;
	}

	public void setCart_date(String cart_date) {
		this.cart_date = cart_date;
	}

	public int getService_no() {
		return service_no;
	}

	public void setService_no(int service_no) {
		this.service_no = service_no;
	}

	public int getCart_state_no() {
		return cart_state_no;
	}

	public void setCart_state_no(int cart_state_no) {
		this.cart_state_no = cart_state_no;
	}

}
