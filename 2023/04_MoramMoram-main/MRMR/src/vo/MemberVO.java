package vo;

public class MemberVO {
	private int mem_no;
	private String mem_name;
	private String mem_id;
	private String mem_pass;
	private String mem_regno;
	private String mem_email;
	private String mem_hp;
	private String mem_addr_base;
	private String mem_addr_detail;
	private int mem_grade;

	public MemberVO() {}

	public MemberVO(int mem_no, String mem_name, String mem_id, String mem_pass, String mem_regno, String mem_email,
			String mem_hp, String mem_addr_base, String mem_addr_detail, int mem_grade) {
		super();
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_regno = mem_regno;
		this.mem_email = mem_email;
		this.mem_hp = mem_hp;
		this.mem_addr_base = mem_addr_base;
		this.mem_addr_detail = mem_addr_detail;
		this.mem_grade = mem_grade;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_regno() {
		return mem_regno;
	}

	public void setMem_regno(String mem_regno) {
		this.mem_regno = mem_regno;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_hp() {
		return mem_hp;
	}

	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}

	public String getMem_addr_base() {
		return mem_addr_base;
	}

	public void setMem_addr_base(String mem_addr_base) {
		this.mem_addr_base = mem_addr_base;
	}

	public String getMem_addr_detail() {
		return mem_addr_detail;
	}

	public void setMem_addr_detail(String mem_addr_detail) {
		this.mem_addr_detail = mem_addr_detail;
	}

	public int getMem_grade() {
		return mem_grade;
	}

	public void setMem_grade(int mem_grade) {
		this.mem_grade = mem_grade;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_no=" + mem_no + ", mem_name=" + mem_name + ", mem_id=" + mem_id + ", mem_pass=" + mem_pass
				+ ", mem_regno=" + mem_regno + ", mem_email=" + mem_email + ", mem_hp=" + mem_hp + ", mem_addr_base="
				+ mem_addr_base + ", mem_addr_detail=" + mem_addr_detail + ", mem_grade=" + mem_grade + "]";
	}

}
