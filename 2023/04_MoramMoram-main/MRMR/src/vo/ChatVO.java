package vo;

public class ChatVO {
	private int chat_no;
	private String chat_content;
	private String chat_senddate;
	private String chat_opendate;
	private int mem_no_sender;
	private int mem_no_target;
	private String mem_target_name;
	private String mem_sender_name;
	
	public ChatVO() {
	}
	

	public ChatVO(int chat_no, String chat_content, String chat_senddate, String chat_opendate, int mem_no_sender,
			int mem_no_target, String mem_target_name, String mem_sender_name) {
		this.chat_no = chat_no;
		this.chat_content = chat_content;
		this.chat_senddate = chat_senddate;
		this.chat_opendate = chat_opendate;
		this.mem_no_sender = mem_no_sender;
		this.mem_no_target = mem_no_target;
		this.mem_target_name = mem_target_name;
		this.mem_sender_name = mem_sender_name;
	}


	public int getChat_no() {
		return chat_no;
	}

	public String getMem_target_name() {
		return mem_target_name;
	}

	public void setMem_target_name(String mem_target_name) {
		this.mem_target_name = mem_target_name;
	}

	public String getMem_sender_name() {
		return mem_sender_name;
	}

	public void setMem_sender_name(String mem_sender_name) {
		this.mem_sender_name = mem_sender_name;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public String getChat_content() {
		return chat_content;
	}

	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}

	public String getChat_senddate() {
		return chat_senddate;
	}

	public void setChat_senddate(String chat_senddate) {
		this.chat_senddate = chat_senddate;
	}

	public String getChat_opendate() {
		return chat_opendate;
	}

	public void setChat_opendate(String chat_opendate) {
		this.chat_opendate = chat_opendate;
	}

	public int getMem_no_sender() {
		return mem_no_sender;
	}

	public void setMem_no_sender(int mem_no_sender) {
		this.mem_no_sender = mem_no_sender;
	}

	public int getMem_no_target() {
		return mem_no_target;
	}

	public void setMem_no_target(int mem_no_target) {
		this.mem_no_target = mem_no_target;
	}

}