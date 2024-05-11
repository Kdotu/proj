package vo;

public class MemberVO {
	
	
	private String memNumber;
	private String memId;
	private String memPass;
	private String memName;
	private String memGender;
	private String memHp;
	private String memBir;
	private String memAdd;
	private String memEmail;
	
	public String getMemNumber() {
		return memNumber;
	}
	public void setMemNumber(String memNumber) {
		this.memNumber = memNumber;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemAdd() {
		return memAdd;
	}
	public void setMemAdd(String memAdd) {
		this.memAdd = memAdd;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	@Override
	public String toString() {
		return "MemberVO [memNumber=" + memNumber + ", memId=" + memId + ", memPass=" + memPass + ", memName=" + memName
				+ ", memGender=" + memGender + ", memHp=" + memHp + ", memBir=" + memBir + ", memAdd=" + memAdd
				+ ", memEmail=" + memEmail + "]";
	}
}