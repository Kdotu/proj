package vo;

public class ExpertVO {
	private int mem_no;
	private String expert_career;
	private String expert_info;
	private String expert_contact;
	private String expert_license;
	private String expert_education;

	public ExpertVO() {
	}

	public ExpertVO(int mem_no, String expert_career, String expert_info, String expert_contact, String expert_license,
			String expert_education) {
		super();
		this.mem_no = mem_no;
		this.expert_career = expert_career;
		this.expert_info = expert_info;
		this.expert_contact = expert_contact;
		this.expert_license = expert_license;
		this.expert_education = expert_education;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getExpert_career() {
		return expert_career;
	}

	public void setExpert_career(String expert_career) {
		this.expert_career = expert_career;
	}

	public String getExpert_info() {
		return expert_info;
	}

	public void setExpert_info(String expert_info) {
		this.expert_info = expert_info;
	}

	public String getExpert_contact() {
		return expert_contact;
	}

	public void setExpert_contact(String expert_contact) {
		this.expert_contact = expert_contact;
	}

	public String getExpert_license() {
		return expert_license;
	}

	public void setExpert_license(String expert_license) {
		this.expert_license = expert_license;
	}

	public String getExpert_education() {
		return expert_education;
	}

	public void setExpert_education(String expert_education) {
		this.expert_education = expert_education;
	}

}
