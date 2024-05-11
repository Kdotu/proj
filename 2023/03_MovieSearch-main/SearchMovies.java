import java.util.List;

public class SearchMovies {
	String lastBuildDate;	//검색 결과를 생성한 시간
	int total;		//총 검색 결과 개수
	int start;		//검색 시작 위치
	int display	;	//한 번에 표시할 검색 결과 개수
	List<Movie> items;
	
	public SearchMovies() {}

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public List<Movie> getItems() {
		return items;
	}

	public void setItems(List<Movie> items) {
		this.items = items;
	}
	
	
}
