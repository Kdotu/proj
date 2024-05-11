package game;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
	Scanner sc = new Scanner(System.in);
	int input;
	String inputStr;
	Main m;
	Character user;
	Item[] itemPool;
	Shop sh;
	Main(){
		}
	public static void main(String[] args) {
		new Main().start(); // 게임 시작
	}
	private void start() {
		System.out.println();
		System.out.println("────────────── 이름 입력 ───────────────");
		userName();
	}
	public void startLog() {	  		// 게임 시작 로그
		System.out.println("\r\n"
				+ "──────────────────────────────────────────────────────────────────\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢸⣿⠛⢣⣤⠀⢸⣿⠛⢣⣤⡄⠀⣿⠛⠛⠛⠃⠀⣿⡟⠛⠛⠃⠀⣿⡟⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢸⣿⣶⡎⠉⠀⢸⣿⣶⡎⠉⠁⠀⣿⣶⣶⠀⠀⠀⣿⣷⣶⣶⡆⠀⣿⣷⣶⣶⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⢸⣿⠀⠰⣿⠆⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠘⠛⠀⠀⠀⠀⠘⠛⠀⠘⠛⠃⠀⠛⠛⠛⠛⠃⠀⠛⠛⠛⠛⠃⠀⠛⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⠀⠀⠀⠀⢠⣤⣤⣤⣤⠀⢠⣤⣤⣤⣤⡄⠀⠀⢠⣤⠀⠀⠀⣤⣤⣤⠀⠀⠀⣤⣤⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⢸⣿⠉⠉⠉⠀⠈⠉⣿⡏⠉⠁⠀⣶⠈⠉⣶⡆⠀⣿⡏⠉⣶⡆⠀⠉⠉⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⠀⠀⠸⠿⠿⢿⣿⠀⠀⠀⣿⡇⠀⠀⠀⣿⠿⠿⣿⡇⠀⣿⡿⠿⢀⡀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⢠⣤⣤⣼⣿⠀⠀⠀⣿⡇⠀⠀⠀⣿⠀⠀⣿⡇⠀⣿⡇⠀⣿⡇⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⠈⠉⠉⠉⠉⠀⠀⠀⠉⠁⠀⠀⠀⠉⠀⠀⠉⠁⠀⠉⠁⠀⠉⠁⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "──────────────────────────────────────────────────────────────────");
		System.out.printf("\t\t   100일 동안 '%s'씨를 키워보는 게임!\n",user.name);
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.print("\n\t\t\t\t\t\t\t다음으로 넘어가기 -> (아무 키나 입력) \n");
		while(true) {
		inputStr = sc.nextLine();
		if(inputStr != null) { // 유저가 누르면 다음으로 넘어가게
			clearConsole();
			showInfo(); // 게임 시작 상태창,메인메뉴창 보여줌
			}
		}
	}
	public void userName() {	  		// 플레이어 이름설정
		name:
		while(true) {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		user = new Character(name);
		
		System.out.println(user.name + "(으)로 하시겠습니까? (Y/N)");
		inputStr = sc.nextLine();
		
		if(inputStr.equals("Y") || inputStr.equals("y")) {
			clearConsole();			
			startLog();
			break name;
		}else {
			System.out.println("다시 입력해주세요.");
			continue name;
			}
		}
	}
	private void gameEnding() {			// 엔딩
		System.out.println("\t100일이 모두 경과되었습니다!");
		System.out.println("───────────── ENDING ─────────────");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}		
		
		ending:
		while(true) { 
		
		if(user.sumAppraisal > 300) {
			if(user.confidence > 80) {
				System.out.println("───────────────────────────────────────");
				System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣶⣶⣶⣄⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣄⣾⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣻⣿⣿⣿⣿⣿⣿⣿⣶⣶⡄⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⣠⠟⠁⠀⠀⠉⠉⠛⠛⠛⢻⡏⠁⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⢰⠏⠀⠀⢰⣷⠀⠀⣤⡄⠀⠀⢹⡄⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⣴⠶⣦⡉⠁⠀⠀⠀⣷⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⢸⡇⠀⠀⢀⡀⢷⣤⣼⠇⢠⡀⠀⠀⣿⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⣰⠞⠃⠀⠀⣨⡿⢶⣤⣤⡶⠏⠀⠀⢀⡟⠀⠀⠀⠀\r\n"
						+ "⠀⠀⢀⡾⠁⠀⠀⠀⣰⠏⠀⠀⣿⠀⠀⠀⠀⠀⠈⠻⡄⠀⠀⠀\r\n"
						+ "⠀⠀⡾⠁⠀⠀⠀⣰⠟⠀⠀⢀⡟⠀⠀⠀⠀⣀⠀⠀⢻⠀⠀⠀\r\n"
						+ "⠀⢸⡇⠀⠀⠲⠛⠁⠀⠀⢀⡾⠁⠀⠀⠀⠀⢸⡆⠀⢸⡇⠀⠀\r\n"
						+ "⠀⠘⣇⠀⠀⠀⠀⠀⠀⣠⡞⠁⠀⠀⠀⠀⠀⢸⡇⠀⢸⡇⠀⠀\r\n"
						+ "⠀⠀⠙⠷⣤⣤⣤⣤⠾⠋⠀⠀⠀⠀⠀⠀⠀⢸⠃⠀⢸⡇⠀⠀ ");
				System.out.println("───────────────────────────────────────");
				System.out.println(user.name + "씨는 프로 사기꾼이 되었습니다.");
				try {
					TimeUnit.SECONDS.sleep(1); 
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}		
				System.out.println("돈은 많이 벌 수 있겠습니다.");
			break ending;}
			if(user.sense > 80 ) {
				System.out.println("───────────────────────────────────────");
				System.out.println("\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠒⢚⠀⠀⠀⠰⢳⡒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣶⣶⠀⠀⠀⠈⠉⠉⠀⠀⠀⠀⠹⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠯⠿⠯⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠠⢰⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢎⠁⠈⡣⡀⠀⠀⠀⠀⠩⢿⠯⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢸⡇⠀⠀⠀⠀⡌⣎⠅⠬⠅⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⠝⠸⠃⠀⠀⠀⡠⠃⢁⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⢰⣾⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⢀⡜⠩⡇⣀⠤⠤⢄⠀⠀⡑⡄⠀⠀⠀⠈⠹⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠁⠀⠀⠀⡜⠓⢺⢰⠁⠀⠀⠀⢱⢾⠈⣾⡀⠀⠀⠀⠀⢤⣤⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠋⠙⠂⠀⠀⢰⠁⠀⠘⠘⣄⠀⠀⢀⡞⠈⠢⠋⣧⠀⠀⠀⠀⣿⣽⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠉⠉⠉⠉⠹⠿⠿⠿⠿⠟⠉⠉⠉⠉⠙⣄⠀⠀⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⣹⣹⣄⠀⠀⢠⠀⠀⠀⠀⣀⣀⢀⢀⠀⠀⠀⣀⣀⠀⠀⠀⢸⠀⠀⠀⣖⢺⡓⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠚⠚⠂⠀⠀⢸⠀⠀⠀⠀⠦⠜⠸⠴⠠⠀⠀⠀⠇⠀⠀⠀⢸⠀⠀⠀⠇⠝⠷⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "");
				System.out.println("───────────────────────────────────────");
				System.out.println(user.name + "씨는 유명한 역술인이 되었습니다.");
				try {
					TimeUnit.SECONDS.sleep(1); 
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}		
				System.out.println(user.name + "씨를 보기 위해 사람들이 줄을 섭니다.");
			break ending;}
		}	
		if(user.sumAppraisal < 300 && user.sumAppraisal > 100) {
			if(user.confidence > 80){
				System.out.println("───────────────────────────────────────");
				System.out.println("\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣾⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⡆\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣈⠻⣿⣷⣷⡟⢁⣴⣊⣉⣑⡢⣄⣴⣶⣶⣦⡀⠀⠀⠀⠀⠀⠀⠀⢸⡇\r\n"
						+ "⠀⠀⠀⠀⠀⣠⡤⠤⢤⣠⠞⣉⣩⣭⡙⣆⠀⠛⠀⡎⢸⣿⣿⣿⡇⢹⣿⡇⠐⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠁\r\n"
						+ "⠀⠀⠀⠀⡜⢁⣀⣀⡀⢈⠀⣏⡉⣸⡇⢸⠀⠀⠀⠱⣸⠿⠿⠿⢇⡾⣿⣿⣼⣿⡟⠀⠀⠀⠀⠀⠀⠀⢨⡇\r\n"
						+ "⠀⠀⠀⠀⣇⠸⠿⠿⠇⠘⣦⣉⣉⣉⡡⢋⡠⠔⠂⢄⡸⠶⠒⠛⠉⠀⠸⠋⠉⣁⣠⣤⡀⠀⠀⠀⠀⠀⢀⡆\r\n"
						+ "⠀⠀⠀⠀⠈⠳⠄⠀⠤⢼⠃⠈⠉⡙⢡⠋⠀⠀⣷⠀⠘⠖⠛⠉⠛⠦⡀⢀⣾⣿⣿⣿⣿⣧⠀⠀⠀⠀⠰⡇\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠚⠉⠉⠛⣇⠀⠠⡯⠀⠠⠄⢠⣶⡦⠀⢱⢈⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠘⠇\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠃⣹⣿⣿⣿⠈⡷⠂⡤⠶⠻⣄⠀⠉⠁⣠⠎⠈⠉⠛⠻⠛⠛⠁⠀⠀⠀⠀⠈⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢧⡙⠛⠻⢛⡴⠃⠀⠉⠀⠀⠈⠹⠶⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠐⠒⠻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣶⣶⣿⣷⣷⣶⣷⣾⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣶⣄⠀⢸⡟⠛⠛⠛⠛⠛⠛⠛⢻⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⡿⠋⢸⡇⠀⠀⠀⠀⠀⠀⠀⢸⣬⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⠿⠟⠁⣾⢺⡇⠀⠀⠀⠀⠀⠀⠀⢸⡥⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⢸⣯⠀⠀⠀⠀⠀⠀⠀⢸⡒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⢸⣿⠀⠀⠀⠀⠀⠀⠀⢸⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠇⢸⣿⣶⣶⣶⣶⣶⣶⣶⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢖⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡆⠀⠀⠀⠀⢀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠠⠄⣀⢰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "");
				System.out.println("───────────────────────────────────────");
				System.out.println(user.name + "씨는 인스타 효소 판매원이 되었습니다.");
				try {
					TimeUnit.SECONDS.sleep(1); 
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}		
				System.out.printf("이제 %s씨는 DM으로만 연락할 수 있습니다.",user.name);
			break ending;}
		}
		if(user.sumAppraisal < 100) {
			if(user.talkingSkill > 30 && user.sense > 50) {
				System.out.println("───────────────────────────────────────────");
				System.out.println("\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡠⠤⠴⡎⠑⢒⣶⣤⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⢀⣀⡠⠤⠔⢒⠊⠉⠁⠀⡀⠀⠀⢇⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣶⠒⠤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⡏⠁⠀⢀⠀⡼⠁⠙⡄⠃⡸⢳⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⢀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⢱⠀⠀⠘⡝⢇⠘⠆⠈⠊⡇⡏⠳⡄⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠈⡆⠀⢠⠞⢦⠳⡀⠀⢠⢃⠇⠀⢱⠀⢸⣿⣿⣿⡟⢿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⢱⠀⢸⠀⠀⠳⡘⢆⢸⢸⠀⠀⢸⠀⡀⡟⠻⠏⠀⢸⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠈⡆⠀⠑⢄⠀⠘⢎⠣⡜⢀⣄⠘⢄⠀⢸⠀⣾⣿⠄⢀⣈⣭⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⢱⠀⢶⠀⢣⠀⢀⠷⡙⢄⠁⠀⠈⠳⡀⣇⣬⡁⠀⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⡆⠀⠀⡎⠀⢸⢰⠙⢌⢣⣀⠀⠀⢇⢸⣿⣿⣄⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⠀⢠⠃⠠⣮⣼⡀⠨⠻⣷⣄⢀⠇⠀⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡆⠘⢄⣀⣿⡇⠀⠀⠀⡹⢿⣧⠀⡀⢸⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⠀⠀⠸⣿⢧⠀⠀⠀⠇⠈⢠⠀⠋⠀⣿⣿⣿⣿⡿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡆⢀⣄⠀⠀⠙⠒⠊⠀⣀⣀⣤⣤⣶⣾⣿⠟⠁⢰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⣀⡠⠤⠔⠒⠒⠉⠉⠀⠀⠀⠀⠉⠉⠙⠒⠒⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "");
				System.out.println("───────────────────────────────────────────");
				System.out.println(user.name + "씨는 포장마차 타로카드 보살이 되었습니다.");
				try {
					TimeUnit.SECONDS.sleep(1); 
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}		
				System.out.println("서양 카드를 가지고 보살 이름을 달아도 아무도 신경쓰지 않습니다.");
			break ending;}
			if(user.talkingSkill < 30 && user.sense < 50) {
				System.out.println("───────────────────────────────────────");
				System.out.println("\r\n"
						+ "⠀⠀⠀⢸⡟⠛⠛⣷⠶⠶⠶⠶⠶⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⢸⡇⠀⠀⡇⠀⠀⠀⣠⠤⠤⠬⠿⠦⠤⠤⠤⣤⠤⣄⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⢸⡇⠀⠀⣇⡀⠀⠀⠛⢦⣄⣤⠶⢦⡄⠀⠀⠈⠳⣿⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠸⠷⠶⠶⠏⢹⡷⠶⢤⣄⣨⡇⠀⠀⣿⠀⠰⠀⠀⣿⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⢸⡷⣄⠀⠀⠈⠙⠷⠟⠁⠀⠀⢠⡶⣿⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠛⠛⠛⠛⠃⠘⠀⠛⠛⠛⠛⠛⠋⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⢸⣏⠙⢦⡀⠀⣤⣤⣤⡴⠋⠁⠀⠈⣿⠀⠀⢸⡇⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠙⢷⣀⠙⠾⣷⣤⣤⣤⣤⡄⠀⠀⣿⠀⠀⢸⡇⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⠦⣤⣤⣤⣤⣤⣤⣤⣤⣿⣀⣀⣸⡇⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁⠀⠀⠀⠀⠀\r\n"
						+ "");
				System.out.println("───────────────────────────────────────");
				System.out.println(user.name + "은 오늘도 옥장판을 구입했습니다.");
				try {
					TimeUnit.SECONDS.sleep(1); 
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
				System.out.println("길거리를 나가면 무언가 자꾸 사옵니다.");
				
			break ending;}
			if(user.stress > 80) {
				System.out.println("───────────────────────────────────────");
				System.out.println("\r\n"
						+ "⠀⠀⠀⠀⠀⡖⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⠒⡆⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⡇⠀⢰⢰⠚⡆⣾⣲⠘⠛⠛⠛⠛⠓⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⡇⠘⠚⠈⠒⠃⠓⠚⠘⠛⠛⣛⣛⡛⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⡇⠘⠛⠛⠛⠛⠛⠛⠛⣡⠞⠉⠉⠙⢶⡁⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⡇⠘⠛⠛⠛⠛⠛⠛⠃⣿⠀⠀⠀⠀⢘⡇⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠙⠛⠛⠛⠛⠛⣿⣿⣧⡹⣦⣀⣀⣠⣼⡃⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠭⠭⠭⠥⠌⠉⠉⠉⠈⠻⠆⠀⠀⠀⠀⠀⠀⠀\r\n"
						+ "");
				System.out.println("───────────────────────────────────────");
				System.out.println(user.name + "은 취준생이 되었습니다.");
				try {
					TimeUnit.SECONDS.sleep(1); 
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
				System.out.println("그렇게 됐습니다.");
			break ending;}
			}
		}
		System.out.println("───────────── THE END ─────────────");
		System.out.println("\n\t새 게임을 시작하시겠습니까? (Y/N)");
		inputStr = sc.nextLine();
		if(inputStr.equals("Y") || inputStr.equals("y")) {new Main().start();}
		}
	public void showInfo() {	  		// 기본상태창
		showInfo:
		while(true) {
		if(user.playDay == user.MAX_DAY) {
			endGame();
		}
		if(user.perforUse == 0 || user.perforUse < 0) {
			System.out.println(" ☞ 하루 행동력을 모두 소모했습니다.\n");
			user.moveDay();
			showInfo();
		}
		if(user.confidence == 0 || user.confidence < 0 && user.playDay != 100) {
			System.out.println(" ☞ 자신감이 없어 행동할 수 없습니다. 휴식을 취합니다.");
			user.moveDay();
			user.confidence = 5;}
		if(user.stress == user.MAX_STRESS) {
			System.out.println(" ☞ 과도한 스트레스로 인해 하루 행동불가=>휴식합니다.");
			user.moveDay();
			user.stress = 5;}
		System.out.println();
		System.out.print(	"\t╭──────────────────────────────────────╮\r\n");
		System.out.print(	"\t|   이  름   |  소 지 금   |                ＃D-DAY      |\n");
		System.out.print(	"\t|──────────────────────────────────────|\n");
		System.out.printf(  "\t|   %s     %5d\t    D-%2d",user.name,user.gold,user.dday);
		System.out.println(" \t|");
		System.out.print(	"\t|──────────────────────────────────────|\n");
		System.out.print(   "\t|  상 태 : ");
		user.userCondition();
		System.out.print(  "\n\t╰──────────────────────────────────────╯\n");
		mainMenu(); // 메인메뉴창
		}
	}
	public void endGame() {
		gameEnding(); // 게임엔딩
		inputStr = sc.nextLine();
		if(inputStr!= null) {
			System.out.println(">> 처음으로");
			start();
		}
	}
	public void mainMenu() {	  		// 메인메뉴창
		mainMenu:
		while(true) {
			System.out.println();
			System.out.print("\t\t\t\t\t행동력 : ");
			user.perforUseShow();
			System.out.println();
			System.out.println("\t╭════════════════════════════════════════╮");
			System.out.println("\t│ 메 뉴 │ 1.행 동 | 2.상태창/소지품  | 3.휴 식         | ");
			System.out.println("\t╰════════════════════════════════════════╯");
			System.out.println("");
			inputStr = sc.nextLine();
			switch(inputStr) {
			case "1":
				performance();
				break mainMenu;
			case "2":
				System.out.println(user);
				user.showItem();
				
				System.out.print("\t\t\t\t\t0. 되돌아가기");
				inputStr = sc.nextLine();
				if(inputStr.equals("0")) {showInfo();}
				break mainMenu;
			case "3":
				clearConsole();
				System.out.println("───── 하루 휴식을 취합니다 ─────");
				user.moveDay();
				showInfo(); 
				break mainMenu;
			default:
				showInfo();
				break mainMenu;
			}
		} 
	showInfo();
	}
	public void performance() {  		// 행동선택창
		if(user.perforUse == 0 || user.perforUse < 0) {
			System.out.println("하루 행동력을 모두 소모했습니다.\n");
			user.moveDay();
			System.out.println("시작한지 : " + user.playDay);
			showInfo();
		}
		System.out.println();
		System.out.print("\t\t\t\t\t행동력 : ");
		user.perforUseShow();
		System.out.println("     ");
		System.out.println("\t╭══════════════════════════════════════════╮");
		System.out.println("\t│ 행 동 │ 1.외 출 | 2.상 점 | 3.교 육 | 4.뒤 로 |");
		System.out.println("\t╰══════════════════════════════════════════╯");
		
		inputStr = sc.nextLine();
		perfor:
		while(true) {
			switch(inputStr) {
			case "1":
				System.out.println("\t╔═══════════╗\n"
						         + "\t|   외  출        |\n"
						         + "\t╚═══════════╝");
				user.moveMoment();
				streetEvent(user);
				break perfor;
			case "2":
				System.out.println("\t╔═══════════╗\n"
						         + "\t|   상  점        |\n"
						         + "\t╚═══════════╝\n");
				System.out.println("상점으로 이동합니다.");
				clearConsole();
				Shop s = new Shop(user,m); // 상점 열때마다 새로
				break perfor;
			case "3":
				user.educate(m);
				break perfor;
			case "4":
				showInfo();
				break perfor;
			default:
				continue perfor;
			}
		}
	}
	public void streetEvent(Character user) { // 길거리 이벤트
		this.user = user;
		input = new Random().nextInt(4)+1;
		event:
		while(true) {
		if(input == 1) {
			//랜덤 이벤트 발생
			// 돈줍기
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("\r\n"
					+ "⠀⠀⠀⠀⢀⠠⠒⣉⣉⡑⠢⢄⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⡴⢁⠔⠉⢀⢆⠈⠑⢦⠑⡄⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⢰⢀⡇⡤⠤⠎⠈⠦⠤⡄⢇⢸⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⢸⠸⡀⠈⢢⠀⠀⠀⡎⠀⢸⢀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠘⡄⢣⡀⡧⠔⠒⠤⡇⢠⠃⡜⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠘⢦⡙⠢⠤⠤⠤⠚⠁⠞⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠈⠑⠒⠒⠒⠈⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "");
			System.out.println("  길거리에서 돈을 주웠다."
					+ "\n  행운(++)이 따르는 기분이 든다!"
					+ "\n\t\t+ 300 gold"
					+ "\n\t\t+ 10 행운");
			user.fortune += 10;
			user.gold += 300;
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("\t\t>> 다음으로 (아무키나 입력)");
			inputStr = sc.nextLine();
			if(inputStr!= null) {
				showInfo();
				break event;
			}
		}
		if(input == 2) {
			// 소매치기 당함
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("왠지 주머니가 가볍다."
					+ "\n\t\t - 300 gold"
					+ "\n\t\t + 5 행운");
			user.gold -= 300;
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("\t\t>> 다음으로 (아무키나 입력)");
			inputStr = sc.nextLine();
			if(inputStr!= null) {
				showInfo();
				break event;
			}
		}
		if(input == 3) {
			// 도를 아십니까?
			// 선택창
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⢀⡴⠒⢦⠀⠀⠀⠀⠀⢠⡶⠋⠀⠀⠀⠀⠰⠒⢦⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠘⠇⠀⣼⠀⠀⠀⠀⠀⠘⠃⠀⠀⠀⠀⠀⣠⣀⣸⠃⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠈⠣⠄⠀⠀⠀⠈⠁⠀⠀⠀⠠⠜⠁⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠖⠲⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⣇⠀⠀⡼⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⢯⡙⢦⣠⠴⠒⠚⢻⡟⠓⠲⢦⣄⡴⢋⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠙⢦⣠⠴⡆⠀⡟⢳⠀⢰⠦⣄⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠈⢧⡞⠁⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠇⠀⠀⠀⠀⠸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "");
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println(" 길거리를 가다 누군가 도를 아냐고 물어본다.　"
							 + " 어떻게 할까?\n"
							 + "1.잘 안다고 말한다　　 2. 모른다고 대답한다.\n");
			inputStr = sc.nextLine();
			if(inputStr.equals("1")) {
				// 대화문
				System.out.println("도의 어원을 알고 계십니까? 어쩌고 저쩌고...\n");
				System.out.println("∠ 행인 : ^^;;;;");
				System.out.println("(행인이 도망간다)");
				System.out.println("누구든 말로 이길 수 있을 것만 같다.");
				System.out.println("\t\t + 10 자신감");
			}else if(inputStr.equals("2")){
				System.out.println("그것도 모르냐며 무시당했다.");
				user.confidence -= 5;
				user.gold -=10;
				System.out.println("\t\t - 5 자신감");
				System.out.println("\t\t - 10 gold");
			}
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("\t\t>> 다음으로 (아무키나 입력)");
			inputStr = sc.nextLine();
			if(inputStr!= null) {
				showInfo();
				break event;
			}
		}
		if(input == 4) {
			System.out.println("﹥─────────────────────────────────﹤");
			System.out.println("∠ 이벤트 중인데 드시고 의견 남겨주세요.");
			System.out.println("  1.수락   2.거절");
			inputStr = sc.nextLine();
			switch(inputStr) {	
			case "1":	
				if(user.fortune > 30) {
					System.out.println("진짜 카페 개업 이벤트였다.");
					System.out.println("\t\t + 10 자신감");
					user.confidence += 10;	
				}else {
					System.out.println("음료를 받은 김에 잠시 앉아보라고 권유 당했다.");
					System.out.println("1시간 정도 얘기를 듣다가 왔다.");
					System.out.println("\t\t - 5 자신감");
					user.confidence -= 5;
				}
				System.out.println("﹥─────────────────────────────────﹤");
				System.out.println("\t\t>> 다음으로 (아무키나 입력)");
				inputStr = sc.nextLine();
				if(inputStr!= null) {
					showInfo();
					break event;}
			case "2":	
				System.out.println("거절하고 집에 가기로 했다.");
				System.out.println("﹥─────────────────────────────────﹤");
				System.out.println("\t\t>> 다음으로 (아무키나 입력)");
				inputStr = sc.nextLine();
				if(inputStr!= null) {
					showInfo();
					break event;}
					}
				}
			}
		}
	public void clearConsole() {  		// 화면 정리 메소드
		for(int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
}

