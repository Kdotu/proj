package vo;

public class ScheduleVO {
	private int schedule_no;
	private String schedule_name;
	private String schedule_start;
	private String schedule_end;
	private int mem_no;
	
	public ScheduleVO() {}

	public ScheduleVO(int schedule_no, String schedule_name, String schedule_start, String schedule_end, int mem_no) {
		super();
		this.schedule_no = schedule_no;
		this.schedule_name = schedule_name;
		this.schedule_start = schedule_start;
		this.schedule_end = schedule_end;
		this.mem_no = mem_no;
	}

	public int getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}

	public String getSchedule_name() {
		return schedule_name;
	}

	public void setSchedule_name(String schedule_name) {
		this.schedule_name = schedule_name;
	}

	public String getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(String schedule_start) {
		this.schedule_start = schedule_start;
	}

	public String getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(String schedule_end) {
		this.schedule_end = schedule_end;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
}
