package vo;

public class ServiceCategorySubVO {
	private int service_category_sub_no;
	private int service_category_main_no;
	private String service_category_sub_name;

	public ServiceCategorySubVO() {	}

	public ServiceCategorySubVO(int service_category_sub_no, int service_category_main_no,
			String service_category_sub_name) {
		super();
		this.service_category_sub_no = service_category_sub_no;
		this.service_category_main_no = service_category_main_no;
		this.service_category_sub_name = service_category_sub_name;
	}

	public int getService_category_sub_no() {
		return service_category_sub_no;
	}

	public void setService_category_sub_no(int service_category_sub_no) {
		this.service_category_sub_no = service_category_sub_no;
	}

	public int getService_category_main_no() {
		return service_category_main_no;
	}

	public void setService_category_main_no(int service_category_main_no) {
		this.service_category_main_no = service_category_main_no;
	}

	public String getService_category_sub_name() {
		return service_category_sub_name;
	}

	public void setService_category_sub_name(String service_category_sub_name) {
		this.service_category_sub_name = service_category_sub_name;
	}

}
