package vo;

public class ServiceVO {
	private int service_no;
	private String service_name;
	private String service_info;
	private int service_price;
	private int service_approved_no;
	private String service_approved_date;
	private String mem_name;
	private int mem_no;
	private int addr_no;
	private int service_category_sub_no;
	private String service_category_main_name;
	private String service_category_sub_name;
	
	public ServiceVO() {
	}
	
	

	public ServiceVO(int service_no, String service_name, String service_info, int service_price,
			int service_approved_no, String service_approved_date, String mem_name, int mem_no, int addr_no,
			int service_category_sub_no, String service_category_main_name, String service_category_sub_name) {
		this.service_no = service_no;
		this.service_name = service_name;
		this.service_info = service_info;
		this.service_price = service_price;
		this.service_approved_no = service_approved_no;
		this.service_approved_date = service_approved_date;
		this.mem_name = mem_name;
		this.mem_no = mem_no;
		this.addr_no = addr_no;
		this.service_category_sub_no = service_category_sub_no;
		this.service_category_main_name = service_category_main_name;
		this.service_category_sub_name = service_category_sub_name;
	}



	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getService_category_main_name() {
		return service_category_main_name;
	}

	public void setService_category_main_name(String service_category_main_name) {
		this.service_category_main_name = service_category_main_name;
	}

	public String getService_category_sub_name() {
		return service_category_sub_name;
	}

	public void setService_category_sub_name(String service_category_sub_name) {
		this.service_category_sub_name = service_category_sub_name;
	}

	public int getService_no() {
		return service_no;
	}

	public void setService_no(int service_no) {
		this.service_no = service_no;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_info() {
		return service_info;
	}

	public void setService_info(String service_info) {
		this.service_info = service_info;
	}

	public int getService_price() {
		return service_price;
	}

	public void setService_price(int service_price) {
		this.service_price = service_price;
	}

	public int getService_approved_no() {
		return service_approved_no;
	}

	public void setService_approved_no(int service_approved_no) {
		this.service_approved_no = service_approved_no;
	}

	public String getService_approved_date() {
		return service_approved_date;
	}

	public void setService_approved_date(String service_approved_date) {
		this.service_approved_date = service_approved_date;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public int getAddr_no() {
		return addr_no;
	}

	public void setAddr_no(int addr_no) {
		this.addr_no = addr_no;
	}

	public int getService_category_sub_no() {
		return service_category_sub_no;
	}

	public void setService_category_sub_no(int service_category_sub_no) {
		this.service_category_sub_no = service_category_sub_no;
	}

}
