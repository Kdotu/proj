package vo;

public class CartStateVO {
	private int cart_state_no;
	private String cart_state_name;
	
	public CartStateVO() {}

	public CartStateVO(int cart_state_no, String cart_state_name) {
		super();
		this.cart_state_no = cart_state_no;
		this.cart_state_name = cart_state_name;
	}

	public int getCart_state_no() {
		return cart_state_no;
	}

	public void setCart_state_no(int cart_state_no) {
		this.cart_state_no = cart_state_no;
	}

	public String getCart_state_name() {
		return cart_state_name;
	}

	public void setCart_state_name(String cart_state_name) {
		this.cart_state_name = cart_state_name;
	}
	
	

}
