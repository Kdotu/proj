package vo;

public class AddressVO {
	private int addr_no;
	private String addr_longi;
	private String addr_lati;
	private String addr_zip;
	private String addr_road;
	private String addr_dong;
	
	
	public AddressVO() {}
	
	public AddressVO(int addr_no, String addr_longi, String addr_lati, String addr_zip, String addr_road,
			String addr_dong) {
		super();
		this.addr_no = addr_no;
		this.addr_longi = addr_longi;
		this.addr_lati = addr_lati;
		this.addr_zip = addr_zip;
		this.addr_road = addr_road;
		this.addr_dong = addr_dong;
	}
	public int getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(int addr_no) {
		this.addr_no = addr_no;
	}
	public String getAddr_longi() {
		return addr_longi;
	}
	public void setAddr_longi(String addr_longi) {
		this.addr_longi = addr_longi;
	}
	public String getAddr_lati() {
		return addr_lati;
	}
	public void setAddr_lati(String addr_lati) {
		this.addr_lati = addr_lati;
	}
	public String getAddr_zip() {
		return addr_zip;
	}
	public void setAddr_zip(String addr_zip) {
		this.addr_zip = addr_zip;
	}
	public String getAddr_road() {
		return addr_road;
	}
	public void setAddr_road(String addr_road) {
		this.addr_road = addr_road;
	}
	public String getAddr_dong() {
		return addr_dong;
	}
	public void setAddr_dong(String addr_dong) {
		this.addr_dong = addr_dong;
	}
	
	
	
}
