package vo;

public class ReportVO {
	private int report_no;
	private String report_date;
	private String report_content;
	private int report_category_no;
	private int mem_no_bad;
	private int mem_no_do;
	
	public ReportVO() {}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public int getReport_category_no() {
		return report_category_no;
	}

	public void setReport_category_no(int report_category_no) {
		this.report_category_no = report_category_no;
	}

	public int getMem_no_bad() {
		return mem_no_bad;
	}

	public void setMem_no_bad(int mem_no_bad) {
		this.mem_no_bad = mem_no_bad;
	}

	public int getMem_no_do() {
		return mem_no_do;
	}

	public void setMem_no_do(int mem_no_do) {
		this.mem_no_do = mem_no_do;
	};
	
	
	
	
	
}
