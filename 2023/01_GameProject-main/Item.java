package game;

import java.util.Scanner;

public class Item {
	String name,itemStr;
	int price;
	int stress;
	int confidence;
	int talkingSkill;
	int sense;
	int fortune;
	
	public Item(String name, int price, int stress, int confidence, int talkingSkill, int sense, int fortune) {
		this.name = name;
		this.price = price;
		this.stress = stress;
		this.confidence = confidence;
		this.talkingSkill = talkingSkill;
		this.sense = sense ;
		this.fortune = fortune ;
	}
	
	public String toString() {
		String info = "[" + name + "]" + " : ("; // 이름
		// 아이템 속성
		if(price > 0) {info += "가격:  "+ this.price;}
		if(stress > 0) {info += " | 스트레스 + " + this.stress;
		}else if(stress < 0) {info += " | 스트레스 - " + Math.abs(this.stress);}
		if(confidence > 0) {info += " | 자신감 + " + this.confidence;
		}else if(confidence < 0) {info += " | 자신감 - " + Math.abs(this.confidence);}
		if(talkingSkill > 0) {info += " | 화술 + " + this.talkingSkill;
		}else if(talkingSkill < 0) {info += " | 화술 - " + Math.abs(this.talkingSkill);}
		if(sense > 0) {info += " | 감각 + " + this.sense;
		}else if(sense < 0) {info += " | 감각 - " + Math.abs(this.sense);}
		if(fortune > 0) {info += " | 행운 + " + this.fortune;}
		else if(fortune < 0) {info += " | 행운 - " + Math.abs(this.fortune);}
		return info;
		}
	}

