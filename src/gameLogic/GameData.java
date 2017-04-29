package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import gameLogic.cards.*;

public class GameData implements Constants {
	
	private int level;
	private int area;
	
	private Player user;
	private ArrayList<Card> cardStack;
	
	public GameData(Player p){
		user = p;
		level = 1;
	
		cardStack = new ArrayList<Card>();
		
		initializeCardStack();
	}
	
	public int getLevel(){
		return level;
	}
	public int getArea(){
		return area;
	}
	public void setLevel(int l){
		level = l;
	}
	public void setArea(int a){
		area = a;
	}
	
	//
	public void initializeCardStack(){
		cardStack.add(new Boss(level));
		cardStack.add(new Event(level));
		cardStack.add(new Merchant(level));
		cardStack.add(new Monster(level));
		cardStack.add(new Resting(level));
		cardStack.add(new Trap(level));
		cardStack.add(new Treasure(level));
	
		Collections.shuffle(cardStack);	
	}

	//
	public void setDificulty(int d){
		switch(d){
			case 1 : user.addArmor(CASUAL_ARMOR);
					 user.addHp(CASUAL_HP);
					 user.addGold(CASUAL_GOLD);
					 user.addFood(CASUAL_FOOD);
				 	 break;
			case 2 : user.addHp(NORMAL_HP);
		 	     	 user.addGold(NORMAL_GOLD);
		 	     	 user.addFood(NORMAL_FOOD);
		 	     	 break;
			case 3 : user.addHp(HARD_HP);
		         	 user.addGold(HARD_GOLD);
		         	 user.addFood(HARD_FOOD);
		         	 break;
			case 4 : user.addHp(IMPOSSIBLE_HP);
		         	 user.addGold(IMPOSSIBLE_GOLD);
		         	 user.addFood(IMPOSSIBLE_FOOD);
		         	 break;
		}
	}

	public final Card getCard(int pos){ 
		if(pos < 6){
			return cardStack.get(pos);
		}
		return null;
	}
	public boolean cardIsMerchant(Card c){
		return c.isMerchant();
	}
	public boolean cardIsEvent(Card c){
		return c.isEvent();
	}
	public boolean cardIsTreasure(Card c){
		return c.isTreasure();
	}
}
