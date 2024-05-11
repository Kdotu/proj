package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Shop {
	
	Main m = new Main();
	Scanner sc = new Scanner(System.in);
	Character user;
	String inputStr;
	Item[] shopList = new Item[6]; // 상점판매 아이템 수
	Item item;
	String name;
	int price,input,shot;
	
	public Shop(Character user, Main m) {
		
		this.user = user;
		this.m = m;
		shopList = new Item[7]; 	// 생성할 아이템
		// 도구점 판매
		String[] itemStr = new String[7];
		// 자신감
		shopList[0] = new Item("구강청결제",8000,0,20,10,0,0);		 
		shopList[1] = new Item("재미로 읽는 유사과학",25000,20,10,0,0,0); 
		// 화술
		shopList[2] = new Item("사람을 설득하는 기술",15000,5,10,10,0,-1); 
		shopList[3] = new Item("기초영단어",8000,10,5,10,0,0); 
		// 센스
		shopList[4] = new Item("물티슈",1500,0,0,0,5,5);
		// 행운
		shopList[5] = new Item("네잎클로버",3000,0,3,0,0,10);
		shopList[6] = new Item("행운의 편지",1000,0,10,0,0,5);
		
		shop:
		while(true) {
			System.out.println();
			System.out.print("\t\t\t\t\t행동력 : ");
			user.perforUseShow();
			System.out.println();
			System.out.println("\t╭══════════════════════════════════════╮");
			System.out.println("\t│ 상 점 │  1.아이템  | 2.복 권  | 3.뒤 로             |");
			System.out.println("\t╰══════════════════════════════════════╯");
		inputStr = sc.nextLine();
			switch(inputStr) {
			case "1":
				System.out.println("\t╭────────────────────────────────────╮\r\n"
						+          "\t  1.아이템구입 | 2.아이템 강화 | 3.뒤 로 \n"
						+          "\t╰────────────────────────────────────╯");
				inputStr = sc.nextLine();
				input = Integer.parseInt(inputStr);
				
				if(inputStr.equals("1")) { // 구입결정
					showShopList();
					System.out.println("  ╭────────────────╮");
					System.out.println("       구입할 물건 선택>>");
					System.out.println("  ╰────────────────╯");
					Item i = sellItem();
					System.out.println(" ╭───────────────────────╮");
					System.out.println("    남은소지금 : " + user.gold);
					System.out.println(" ╰───────────────────────╯");
					if(user.gold >= i.price) { // 돈이 있어야 구매
						useGold(i.price);
						user.buyItem(i);
					}else {
						System.out.println("소지금 부족");
						System.out.println("남은소지금 : " + user.gold);
					}
				}else if(inputStr.equals("2")) {// 강화결정
					if(user.inventory == null || user.inventory.length == 0) {
						System.out.println("소지품이 없습니다.");
						waiting();
						break shop;
					}
					System.out.println("  \t\t\t╭───────────────╮\n"
							+          "  \t\t\t  비 용 100 gold\n"
							+          "  \t\t\t╰───────────────╯");
					Item i = itemPlus(user);
					if(user.gold >= 100) { // 돈이 있어야 구매
						user.gold -= 100;
						i=itemInchant(i,user);
						waiting();
					}else {
						System.out.println("* 소지금 부족 *");
						System.out.println("남은 소지금 : " + user.gold);
						waiting();
					}
				}
				break shop;
			case "2":
				lottery(user);
				break shop;
			case "3":
				System.out.println("되돌아갑니다.");
				break shop;
			default:
				continue shop;
			}
		}Main m1 = new Main();
	}
	public Item itemInchant(Item i, Character user) {
		System.out.println(" ▷ ");
		int addPoint = new Random().nextInt(10)+1; // 추가할 스탯포인트
		String[] addStat = new String[] {"자신감","센스","화술","행운"};
		// 성공, 실패 확률
		input = new Random().nextInt(4);
		System.out.println("✧･ﾟ: *✧･ﾟ:* " + addStat[input] + " 가루를 뿌립니다　　 *:･ﾟ✧*:･ﾟ✧\r\n");
		if(addPoint % 2 == 0) {
			if(input == 0) {i.confidence += addPoint;}
			if(input == 1) {i.sense -= addPoint;}
			if(input == 2) {i.talkingSkill += addPoint;}
			if(input == 3) {i.fortune += addPoint;}
			user.gold -= 100;
			System.out.println("✧ 아이템의 효과가 상승했습니다 ✧");
			System.out.println(addStat[input] +" + "+ addPoint);
		}else {
			System.out.println("...아무 일도 일어나지 않았습니다...");
			}
		return i;
	}
	private Item itemPlus(Character user) {
		this.user = user;
		itemPlus:
		while(true) {
			user.showItem(); // 소지품창 공개~
			System.out.println("  ╭───────────╮");
			System.out.println("      물건 선택>>");
			System.out.println("  ╰───────────╯");
			inputStr = sc.nextLine();
			input = Integer.parseInt(inputStr);
			
			if(input > user.inventory.length || input < 1) {
				System.out.println("잘못 입력하였습니다.");
				continue itemPlus;
			}else {
				System.out.println(user.inventory[input-1] + "을 강화하시겠습니까? (Y/N)");
				inputStr = sc.nextLine();
				if(inputStr.equals("Y") || inputStr.equals("y")) {
					return user.inventory[input-1]; // 유저가 고른 인덱스를 반환
				}
			}
		}
	}
	public void showShopList() {
		System.out.println("--------------------------");
		for(int i = 0; i < this.shopList.length; i++) {
			System.out.printf("%2d. %s\n", i+1, this.shopList[i]);
			if((i+1) % 2 == 0) {
				System.out.println("--------------------------");
			}
		}
	}
	public void waiting() {
		for(int i = 0; i < 2; i++) {
			System.out.println();
			try {
				TimeUnit.SECONDS.sleep(1); 
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
	}
	public void useGold(int price) { // 골드사용
		if(user.gold <= 0) {
			System.out.println("소지금 부족");
		}else {	
			user.gold -= price;
		}
	}
	public Item sellItem() {
			sell:
			while(true) {
				inputStr = sc.nextLine();
				input = Integer.parseInt(inputStr);
				if(input > this.shopList.length || input < 1) {
					System.out.println("잘못 입력하였습니다.");
					continue sell;
				}else {
					System.out.println(this.shopList[input-1] + "을 구입하시겠습니까? (Y/N)");
					inputStr = sc.nextLine();
					if(inputStr.equals("Y") || inputStr.equals("y")) { // 아이템 구매 여부
						return shopList[input-1]; // 유저가 고른 인덱스를 반환
					}
				}
				return null;
			}
		}
	public void lottery(Character user) {
		this.user = user;
		System.out.println("──────────── □ 복권판매소 □ ────────────");
		System.out.println("    ✧ ✧ ✧ 일확천금의 꿈을 꿔보자 ✧ ✧ ✧");
		System.out.println("────────────────────────────────────── ");
		System.out.println("\t(☞ 1회 비용 100 gold)\n\t(☞ 당첨번호는 복권 시도할 때마다 달라집니다)\n");
		System.out.println("\t(☞ 숫자를 랜덤으로 뽑아서 진행합니다.)");
		System.out.println("────────────────────────────────────── ");
		System.out.println("\t□ 4자리 모두 일치 => 1등 : 10000 gold\n"
				+ "\t□ 3자리 일치 => 2등 : 5000 gold\n"
				+ "\t□ 2자리 일치 => 100 gold\n"
				+ "\t□ 모두 불일치 => 꽝!");
		System.out.println("─────────────────────────────────── ");
		System.out.println("\t 1. 도전                2. 되돌아가기");
		System.out.println("─────────────────────────────────── ");
		
		lottery:
		while(true) {
			// 당첨번호 만들기
			int[] lottoNum = new int[4];
			
			lottoNum[0] = new Random().nextInt(10);
			for(int i= 0; i < lottoNum.length; i++) {
				for(int j = 0; j < i; j++) {
					if(lottoNum[i] == lottoNum[j]) {
						lottoNum[j] = new Random().nextInt(10);
					}
				}
			}
			inputStr = sc.nextLine();
			if(user.gold < 100) {
				System.out.println("소지금 부족");
				break lottery;
			}
			lotto1:
			while(true) {
			switch(inputStr) {
			case "1":
				int[] userNum = new int[4];
				System.out.println("\t ─ 랜덤 숫자를 뽑습니다 ─");
				for(int i = 0; i < lottoNum.length; i++) {
					 for(int j = 0; j < i; j++) {
					userNum[i] = new Random().nextInt(10);
					 if(userNum[i]==userNum[j]) { i--;}
					 }
				}
				System.out.println(" ☞ " + Arrays.toString(userNum) + "으로 하시겠습니까?(Y/N)");
				inputStr = sc.nextLine();
				if(inputStr.equals("Y") || inputStr.equals("y")) {
					user.gold -= 100; // 로또 구입
					System.out.println(" ☞ 로또 구입비용 - 100gold");
					lottoLoding();
					System.out.println("당첨번호 : " + Arrays.toString(lottoNum));
					System.out.println("내 번호 : " + Arrays.toString(userNum));
					
					for(int i = 0; i < lottoNum.length; i++) {
						if(lottoNum[i] == userNum[i]){shot++;}
					}
						if(shot == 4) {
							System.out.println("\t 1등 당첨 축하합니다!");
							user.gold += 10000; // 상금 지급
							user.confidence +=15;
						}
						if(shot == 3) {
							System.out.println("\t 2등 당첨 축하합니다!");
							user.gold += 1000; // 상금 지급
							user.confidence +=10;
						}
						if(shot == 2) {
							System.out.println("\t 3등 당첨 축하합니다!");
							user.gold += 100; // 상금 지급
						}if(shot == 1 || shot == 0) {
							System.out.println("\t 꽝! 다음엔 당첨될거에요");
							user.confidence -=5;
						}
							if(user.confidence < 0) {
								System.out.println("자신감이 바닥을 쳤습니다.(행동 불가)");
								user.moveDay();
								break lottery;}
							System.out.println("────────────────────────────────── ");
							System.out.println(" ☞ 아무키나 누르세요");
							inputStr = sc.nextLine();
							if(inputStr!= null) {break lottery;}
						}
			case "2":
				break lottery;}
				}
			}
		}
	private void lottoLoding() {
		for(int i = 0; i < 2; i++) {
		try {
			TimeUnit.SECONDS.sleep(1); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		System.out.println(".");
		}
	}
}
