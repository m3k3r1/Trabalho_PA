package gameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import gameLogic.cards.*;

public class GameData implements Constants {
	private Player user;
	private ArrayList<Card> gameCardStack;
	private HashMap<Integer, Card> cardStack;
	private ArrayList<Integer > posChoosen;
	
	public GameData(Player p){
		user = p;
		cardStack = null;
		gameCardStack = null;
		posChoosen = null;
		
		initializeCardStack();
		shuffleStack();
	}
	
	private void initializeCardStack(){
		cardStack.put(1 , new Boss());
		cardStack.put(2 , new Event());
		cardStack.put(3, new Merchant());
		cardStack.put(4, new Resting());
		cardStack.put(5, new Trap());
		cardStack.put(6, new Treasure());
	}
	
	private boolean isPosAlreadyChoosen(int cardId){
		
		for(int a: posChoosen){
			if(cardId == a)
				return true;
		}
		return false;
			
	}
	
	private void shuffleStack(){
		int card = 0;
		
		do{
			card = (int)(Math. random() * 6 + 1);
		}while(!isPosAlreadyChoosen(card));
		
		gameCardStack.add(cardStack.get(card));
		
	}

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
}
