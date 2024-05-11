package game;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Character {
	Scanner sc = new Scanner(System.in);
	Main m;
	Shop sh;
	Item[] inventory = new Item[0];
	String name, inputStr;
	int input;
	int playDay,dday;
	static final int MAX_CONFIDENCE = 100,MAX_STRESS = 100, MAX_PERFOR = 3, MAX_DAY = 100; //  최대
	
	// character status
	int stress, gold,confidence, perforUse; // 스트레스, 소지금, 자신감, 가용행동력
	int talkingSkill, sense, fortune; // 화술, 감각(촉), 행운
	int sumAppraisal = this.confidence + this.talkingSkill + this.sense + this.fortune;
	
	public Character(String name) {
		this.name = name;
		this.stress = 30;
		this.gold = 10000;
		this.confidence = 30;
		this.perforUse = MAX_PERFOR;
		this.talkingSkill = 30;
		this.sense = 30;
		this.fortune = 30;
		this.playDay = 98;
		this.dday = MAX_DAY - this.playDay; // 디데이 표시
		this.sumAppraisal = this.confidence + this.talkingSkill + this.sense + this.fortune; // 평가
		inventory = new Item[0]; // 소지품 하나 임시로 넣어둠
	}
	public void perforUseShow() { // 행동력 표시
		for(int i = 0; i < MAX_PERFOR; i++) {
			if(perforUse <= i) {
				System.out.print("○");
			}else {
				System.out.print("●");
			}
		}
	}
	public void userCondition() {
		System.out.print(" STRESS: ");
		if(this.stress > 70) {
			System.out.print("주의, ");
		}else if(this.stress < 50) {
			System.out.print("건강, ");
		}
		
		System.out.print("CONFIDENCE: ");
		if(this.confidence < 10) {
			System.out.print("저조 |");
		}else if(this.confidence >= 10) {
			System.out.print("건강 |");
		}
	}
	public void buyItem(Item item) { // 플레이어가 구매
		System.out.println(" ☞ " + item.name +"을(를) 샀다!");
		for(int i = 0; i < 2; i++) {
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.println(".");
	}
		Item[] tmp = new Item[this.inventory.length + 1];
		for(int i = 0; i < this.inventory.length; i++) {
		tmp[i] = this.inventory[i];
		}
		tmp[tmp.length-1] = item;
		this.inventory = tmp;
		
		// item의 능력치만큼 캐릭터의 능력치 향상
		this.confidence += item.confidence;
		this.stress += item.stress;
		this.talkingSkill += item.talkingSkill;
		this.sense += item.sense;
		this.fortune += item.fortune;

		if(this.stress > 100) {this.stress = 100;}
		if(this.stress < 0) {this.stress = 0;}
		
		if(this.confidence > 100) {this.confidence = 100;}
		if(this.confidence < 0) {this.confidence = 0;}
		
		if(this.talkingSkill > 100) {this.talkingSkill = 100;}
		if(this.talkingSkill < 0) {this.talkingSkill = 0;}
		
		if(this.sense > 100) {this.sense = 100;}
		if(this.sense < 0) {this.sense = 0;}
		
		if(this.fortune > 100) {this.fortune = 100;}
		if(this.fortune < 0) {this.fortune = 0;}
		
		
	}
	public void moveMoment() {   // 이동 문구
		System.out.println("\t\t·");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.println("\t\t·");
		perforUse--;
		System.out.print("▷ 행동력을 1 소모합니다.\t");
		System.out.println("(남은 행동력" + this.perforUse + ")");
		
	}
	public void goHome() { // 이동 문구
		System.out.println("집으로 가는중...");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	public void moveDay() { // 날짜변경 문구
		for(int i = 0; i < 2; i++) {
			System.out.println(".");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
		System.out.println("날짜가 바뀝니다.");
		this.playDay++;
		this.perforUse = MAX_PERFOR; // 행동력 충전
		System.out.printf("  ┌──────┐\r\n"
				+ "  │이제  ！│\r\n"
				+ "  │%d일째！│\r\n"
				+ "  └──────┘\r\n"
				+ "  ヽ(＾ω＾)ﾉ三三　 　 \n"
				+ "  　 (　 へ )三三　\n"
				+ "  　　く 三三 　　　 \n"
				+ "￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
				+ "",playDay);
		if(this.confidence <= 0) {
			this.confidence = 5;
		}
		if(this.stress >= 100) {
			this.stress =99;
		}
	}
	public void clearConsole() { // 화면 정리 메소드
		for(int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
	public void showItem() {
		System.out.println("────────────────── 소지품창 ───────────────────");
		for(int i = 0; i < this.inventory.length; i++) { 
			System.out.println((i+1) +". "+ this.inventory[i]);
		}
		System.out.println("\n───────────────────────────────────────────");
	}
	@Override
	public String toString() {
		String str = String.format("────────────── %s의 상태───────────────\n"
				+    "\t화술 : %d\n"
				+    "\t감각 : %d\n"
				+    "\t행운 : %d\n"
				+   "\t스트레스 : %d\n"
				+    "\t자신감 : %d\n\n"
				+    "\t＃ 전 체 평 가 : %d\n",this.name,this.talkingSkill,this.sense,this.fortune,this.stress,this.confidence,this.sumAppraisal);
		return str;
	}
	public void educate(Main m) {
		this.m = m;
		System.out.println();
		System.out.print("\t\t\t\t\t행동력 : ");
		perforUseShow();
		System.out.println();
		System.out.println("\t╭══════════════════════════════════════╮");
		System.out.println("\t│ 교 육 │  1.학 원   |  2.알 바  | 3.뒤 로 |");
		System.out.println("\t╰══════════════════════════════════════╯");
		education:
		while(true) {
			inputStr = sc.nextLine();
			switch(inputStr) {
			case "1":
				if(this.gold < 100) {
					System.out.println("― 학원 수강료가 없습니다 ― ");
					System.out.println("소지금 : "+ this.gold);
					break education;
				}
				System.out.println();
				System.out.print("\t\t\t\t\t행동력 : ");
				perforUseShow();
				System.out.println();
				System.out.println("\t╭══════════════════════════════════════╮\n"
							+	   "\t│ 학  원 │ 1.스피치 | 2.타로상담사 | 3.뒤 로 |\n" 
							+ 	   "\t╰══════════════════════════════════════╯\n"
							+ "			* 학원 수강료 -100 gold");
				while(true) {
					inputStr = sc.nextLine();
				switch(inputStr) {
				case "1":
					talkingEdu();
					break education;
				case "2":
					senseEdu();
					break education;
				default:
					educate(m);
					break education;
						}
					}
			case "2":
				partTime();
				break education;
			case "3":
				break education;
			default:
				continue education;
				}
			}
		}
	private void partTime() {
		boolean i = new Random().nextBoolean();
		if(i) {
			System.out.println("\t╔═══════════╗\n"
			         +         "\t  카 페 알 바   \n"
			         +         "\t╚═══════════╝");
			try {
				TimeUnit.SECONDS.sleep(1); 
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			System.out.println("───────────────────────────────────────────");
			System.out.println("\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣶⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⢻⡇⠈⠙⠛⠛⠛⠛⠛⠋⣿⡟⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢷⣼⡇⠀^⠀⠀⠀⠀⠀^ ⣿⣧⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣇⠀⠀⠀⠀⠀⠀⠀⠀ ⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢷⣤⣤⣤⣤⣤⣤⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣼⣇⣀⣀⣿⣧⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⢰⣿⣿⣿⣿⣿⣿⣿⣿⠠⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠙⣷⠈⠛⠛⢿⣯⣿⠟⠛⠛⢠⣿⠛⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠁⠀⢹⡇⠀⠀⠀⠛⠋⠀⠀⠀⣼⡟⠀ ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠸⣷⠀⠀⠀⠚⠃⠀⠀⠀⣿⡇⠀ ⢸⡇⠀         ⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⣿⠀⠀⠀⠚⠃⠀⠀⢰⣿⠁⠀ ⢸⡇          ⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⣶⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⣿⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠃⠛⠛⠛⠀⠀⠀⠀⠀⠀\r\n"
					+ "");
			System.out.println("───────────────────────────────────────────");
			System.out.println(" ☞ 타로 카페에서 알바를 시작합니다.");
			try {
				TimeUnit.SECONDS.sleep(1); 
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			System.out.print(" ▷ 결과 : ");
			input = new Random().nextInt(3)+1;
			if(input == 1) {
				
				System.out.println("성공적이었다.");
				this.sense += 5;
				this.gold += 1000;
				this.confidence += 5;
			}if(input == 2) {
				
				
				System.out.println("그럭저럭이었다.");
				this.gold += 500;
			}
			if(input ==3) {
				
				System.out.println("전혀 집중하지 못했다.");
				this.gold += 100;
				this.stress += 5;
			}
		}else {
			System.out.println("\t╔═══════════╗\n"
			         +         "\t  마 트 판 매   \n"
			         +         "\t╚═══════════╝");
			try {
				TimeUnit.SECONDS.sleep(1); 
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			System.out.println("───────────────────────────────────────────");
			System.out.println("\r\n"
					+ "⠀⠀⠀⠀⠀⠀⢰⡟⠛⠛⠛⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠈⠛⠛⠛⢻⡈⣷⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠸⡇⠀⠀⢸⡆⠀⠀⢸⡇⠀⠀⢸⡇⠀⠀⢸⠇⠀⠀⣸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡄⢳⠶⠶⠾⣷⠶⠶⢾⡷⠶⠶⢾⡷⠶⠶⣾⠶⠶⢶⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡸⣇⠀⠀⢿⡀⠀⠸⡇⠀⠀⢸⡇⠀⠀⡏⠀⠀⣸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡇⢻⡶⠶⢾⡷⠶⠶⣿⠶⠶⣾⠷⠶⢾⡷⠶⢶⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡈⣷⠀⠀⣿⠀⠀⣿⠀⠀⣿⠀⠀⣾⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠞⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⢛⣿⠛⠛⠛⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⢁⡟⠉⠉⠉⠉⠉⠉⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠀⠻⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⣟⠛⠛⠛⣿⠛⠛⠛⠛⠛⠛⢿⠛⠛⠛⣻⠟⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡷⡶⢿⡅⠀⠀⠀⠀⠀⠀⢨⡷⢶⢾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢧⣥⡾⠃⠀⠀⠀⠀⠀⠀⠘⢷⣬⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "");
			System.out.println("───────────────────────────────────────────");
			System.out.println(" ☞ 마트 판매 알바를 시작합니다.");
			try {
				TimeUnit.SECONDS.sleep(1); 
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			System.out.print(" ▷ 결과 : ");
			input = new Random().nextInt(3)+1;
			if(input == 1) {
				
				System.out.println("성공적이었다.");
				this.talkingSkill += 5;
				this.gold += 1000;
				this.confidence += 5;
			}if(input == 2) {
				
				
				System.out.println("그럭저럭이었다.");
				this.talkingSkill += 3;
				this.gold += 500;
			}
			if(input ==3) {
				
				System.out.println("전혀 집중하지 못했다.");
				this.gold += 100;
				this.stress += 5;
			}
		}
		moveMoment();
	}
	private void senseEdu() {
		System.out.println("\t╔═══════════╗\n"
		         +         "\t   타로 상담   \n"
		         +         "\t╚═══════════╝");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
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
		System.out.println(" ☞ 타로 상담사 과정을 수강합니다.");
		this.gold -= 100;
		System.out.println("\t\t\t- 100 gold");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.print(" ▷ 결과 : ");
		input = new Random().nextInt(3)+1;
		if(input == 1) {
			
			System.out.println("배운 걸 모두 이해했다!");
			this.sense += 10;
			this.fortune += 5;
		}if(input == 2) {
			
			System.out.println("그럭저럭이었다.");
			this.sense += 5;
		}
		if(input ==3) {
			
			System.out.println("무슨 말인지 모르겠다.");
			this.sense += 1;
			this.stress += 5;
		}
		moveMoment();
		
	}
	private void talkingEdu() {
		System.out.println("\t╔═══════════╗\n"
		         +         "\t   스 피 치   \n"
		         +         "\t╚═══════════╝");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.println("───────────────────────────────────────────");
		System.out.println("\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡿⠁⠀⠀⠀⢰⣷⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠁⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⢠⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠟⠀⢀⡀⠀⠀⠀⠸⣿⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢀⣿⠁⢀⣼⡿⠃⢀⣀⠀⠀⠀⢹⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⣿⠇⢀⣾⡏⠀⣴⡿⠋⠀⠀⠀⣿⣷⣶⣶⣶⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⢸⣿⠀⢸⣿⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⢸⣿⠀⢸⣿⡀⠸⣿⡀⠀⠀⠀⠀⠀⢀⣀⣴⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢿⣇⠀⢿⣧⠀⠹⣷⣤⣀⡀⠀⠘⣿⣟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠈⣿⣆⠈⠻⣷⣄⡈⠙⠛⠁⠀⠀⠈⠛⠿⣶⣶⣶⣶⠾⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣷⣄⠈⠛⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
				+ "");
		System.out.println("───────────────────────────────────────────");
		System.out.println(" ☞ 스피치 학원에서 수업을 듣습니다.");
		this.gold -= 100;
		System.out.println("\t\t\t- 100 gold");
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.print(" ▷ 결과 : ");
		input = new Random().nextInt(3)+1;
		if(input == 1) {
			
			System.out.println("성공적이었다.");
			this.talkingSkill += 10;
			this.confidence += 5;
		}if(input == 2) {
			
			System.out.println("그럭저럭이었다.");
			this.talkingSkill += 5;
		}
		if(input ==3) {
			
			System.out.println("집중하지 못했다.");
			this.talkingSkill += 1;
			this.stress += 5;
		}
		moveMoment();
	}
}


	

