package vo;

public class PageVO {
	private int start; // 1 ~ 5까지 시작 페이지 끝 페이지
	private int end;
	private int startPage; // 하단 시작페이지
	private int endPage; // 하단 끝페이지

	private int totalPage;
	private int count;
	private static int perList = 10; // 페이지 출력 목록 list 개수
	private static int perPage = 5; // 페이지 하단

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static int getPerList() {
		return perList;
	}

	public static void setPerList(int perList) {
		PageVO.perList = perList;
	}

	public static int getPerPage() {
		return perPage;
	}

	public static void setPerPage(int perPage) {
		PageVO.perPage = perPage;
	}

}
