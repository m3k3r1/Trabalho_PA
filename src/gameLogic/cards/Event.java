package gameLogic.cards;

import gameLogic.Player;

public class Event extends Card{
	
	public Event(int l){
		super(l);
		name = "Event";
	}
	
	@Override
	public boolean isEvent(){
		return true;
	}
	
	@Override
	public int cardDiceEffect(Player p, int dice){
		switch(dice)  {
			case 1: 
				p.addFood(1); 
				return 0;
			case 2: 
				p.addHp(2); 
				return 0;
			case 3: 
				p.addGold(2);
				return 0;
			case 4: 
				p.addXP(2);
				return 0;
			case 5: 
				p.addArmor(1); 
				return 0;;
			case 6: 
				return 1;
		}
		
		return 0;
	}
}
