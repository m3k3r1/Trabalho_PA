package gameLogic.cards;

import gameLogic.Player;

import java.awt.image.BufferedImage;

public class Event extends Card{
	
	public Event(BufferedImage i){
		super(i);
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
				p.addXp(2);
				return 0;
			case 5: 
				p.addArmor(1); 
				return 0;
			case 6: 
				return 1;
		}
		
		return 0;
	}
}
