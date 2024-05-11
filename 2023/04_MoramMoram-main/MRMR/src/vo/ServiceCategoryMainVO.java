package vo;

public class ServiceCategoryMainVO {
	private int service_category_main_no;
	private String service_category_main_name;
	
	
	public ServiceCategoryMainVO() {}
	
	public ServiceCategoryMainVO(int service_category_main_no, String service_category_main_name) {
		super();
		this.service_category_main_no = service_category_main_no;
		this.service_category_main_name = service_category_main_name;
	}
	public int getService_category_main_no() {
		return service_category_main_no;
	}
	public void setService_category_main_no(int service_category_main_no) {
		this.service_category_main_no = service_category_main_no;
	}
	public String getService_category_main_name() {
		return service_category_main_name;
	}
	public void setService_category_main_name(String service_category_main_name) {
		this.service_category_main_name = service_category_main_name;
	}
	
	
}
