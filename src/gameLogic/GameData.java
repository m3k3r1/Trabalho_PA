package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import gameLogic.cards.*;

public class GameData implements Constants {
	
	private int level;
	private int area;
	
	private Player user;
	private ArrayList<Card> cardStack;
	private ArrayList<Integer> diceStack;
	
	public GameData(Player p){
		user = p;
		level = 1;
		area = 1;
	
		diceStack = new ArrayList<Integer>();
		cardStack = new ArrayList<Card>();
		
		initializeCardStack();
	}
	public void takeHp(int h){
		user.addHp(h);
	}
	public int getDiceSize(){
		return diceStack.size();
	}
	
	public int getDiceValue(int p){
		return diceStack.get(p);
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
	public int getHp(){
		return user.getHp();
	}
	public int getArmor(){
		return user.getArmor();
	}
	public int getGold(){
		return user.getGold();
	}
	public int getFood(){
		return user.getFood();
	}
	public String showCard(int c){
		return cardStack.get(c).toString();
	}
	//
	public void initializeCardStack(){
		//TODO : BOSS NEEDS TO BE SET APART
		
		//cardStack.add(new Boss(level));
		
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
	
	public int throwDice(){
		return (int)(Math. random() * 6 + 1);
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
	
	public boolean cardIsMonster(Card c){
		return c.isMonster();
	}
	
	public void eventType(int card){
		cardStack.get(card).cardDiceEffect(user, throwDice());
	}
	
	public void treasureType(int card){
		cardStack.get(card).cardDiceEffect(user, throwDice());
	}
	
	public void merchantTransaction(int card, int option){
		cardStack.get(card).playerOption(user, option);
	}
	
	public void restingChoice(int card, int option){
		cardStack.get(card).playerOption(user ,option);
	}
	
	public void generateDiceValues(){
		for(int i = 0; i < 3; i++)
			diceStack.add(throwDice());
	}
	
	public void rerollDice(int dice){
		diceStack.set(dice, throwDice());
	}

}
