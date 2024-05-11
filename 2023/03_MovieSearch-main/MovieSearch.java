import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieSearch {
	private Scanner scan;
	private ObjectMapper objectMapper;
	private File file = new File("d:/d_other/jsonTest/movie.json");
	private ArrayList<Movie> movieList;
	private SearchMovies sr;
	// 생성자
	MovieSearch(){
		scan = new Scanner(System.in); // 검색 키워드 입력용
		
	}
	
	public static void main(String[] args) {
		new MovieSearch().searchStart();
	}

	private void searchStart() {
		System.out.println();
		System.out.println("+++++++++++++++++++++++++++++++++");
		System.out.println("     영 화 검 색 프 로 그 램");
		System.out.println("+++++++++++++++++++++++++++++++++");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 검색
				infoSearch(); break;
			case 2: // 파일저장
				saveJSON(); break;
			case 3: // 저장한 파일 확인
				openJSON(); break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}

	// 영화 정보 검색
	private void infoSearch() {
		System.out.println();
		System.out.println("검색할 영화 정보를 입력하세요");
		System.out.print("입력 >> ");
		String keyword = "";
		keyword = scan.next();
		System.out.println();
		String info = getInfo(keyword);
		
		printInfo(info);
//		System.out.println("info : " + info);
	}
	// api에서 정보를 얻어오는 메서드
	private String getInfo(String keyword) { 
	
	    String clientId = ""; //애플리케이션 클라이언트 아이디
	    String clientSecret = ""; //애플리케이션 클라이언트 시크릿
	
	    String text = null;
	    //String key = "맘마미아";
	    try {
	        text = URLEncoder.encode(keyword , "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException("검색어 인코딩 실패",e);
	    }
	
	    String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;    // JSON 결과
	
	    Map<String, String> requestHeaders = new HashMap<>();
	    requestHeaders.put("X-Naver-Client-Id", clientId);
	    requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	    String responseBody = get(apiURL,requestHeaders);
	    
	    return responseBody;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
	
    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }
    
    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
	        }
	    }
	
	// 검색한 영화 정보를 출력하는 메서드
	private void printInfo(String info) {
		try {
			objectMapper = new ObjectMapper();
			sr = objectMapper.readValue(info, SearchMovies.class); //movie 객체로 변환
			System.out.println("검색된 영화의 수 : " + sr.getTotal());
			System.out.println();
			
			movieList = new ArrayList<Movie>();
			for(int i = 0; i < sr.getItems().size(); i++) {
				Movie movie = objectMapper.readValue(objectMapper.writeValueAsString(sr.getItems().get(i)), Movie.class);
				movieList.add(movie);
			}
			
			showList();
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// JSON 파일로 저장
	private void saveJSON() {
		File fdir = new File("d:/d_other/jsonTest");
		objectMapper = new ObjectMapper();
		if(!fdir.exists()) {
			fdir.mkdirs();
		}
		 try {
	            // 객체를 JSON 타입의 파일로 변환
	        	objectMapper.writeValue(file, sr);
	        	
	        	System.out.println("json 파일 저장 완료");
	 
	        } catch (JsonGenerationException e) {
	            e.printStackTrace();
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		
		
	}
	
	// 저장한 JSON파일 열기
	private void openJSON() {
		objectMapper = new ObjectMapper();
		try {
			sr = objectMapper.readValue(file, SearchMovies.class);
			
			movieList = new ArrayList<Movie>();
			for(int i = 0; i < sr.getItems().size(); i++) {
				Movie movie = objectMapper.readValue(objectMapper.writeValueAsString(sr.getItems().get(i)), Movie.class);
				movieList.add(movie);
			}
			showList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void showList() {
		for(Movie movie : movieList) {
			String title = movie.getTitle();
			title = title.replace("<b>","");
			title = title.replace("</b>","");
			System.out.println("title : " + title);
			System.out.println("subtitle : " + movie.getSubtitle());
			System.out.println("pubDate : " + movie.getPubDate());
			System.out.println("director : " + movie.getDirector());
			System.out.println("actor : " + movie.getActor());
			System.out.println("userRating : " + movie.getUserRating());
			System.out.println();
		}
		
	}
	// 메뉴 선택
	private int displayMenu() {
		System.out.println("메 뉴 선 택");
		System.out.println("-----------------------------");
		System.out.println(" 1. 영화정보 검색");
		System.out.println(" 2. 해당정보 JSON 파일저장");
		System.out.println(" 3. 저장한 파일 확인");
		System.out.println(" 0. 프로그램 종료");
		System.out.println("-----------------------------");
		System.out.println("번호 입력 >> ");
		
		return scan.nextInt();
	}
}
