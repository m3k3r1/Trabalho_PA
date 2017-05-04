package gameLogic.cards;

import gameLogic.Player;

public class Event extends Card{
	
	public Event(int l){
		super(l);
	}
	
	@Override
	public boolean isEvent(){
		return true;
	}
	
	@Override
	public void cardDiceEffect(Player p, int dice){
		switch(dice)  {
			case 1: p.addFood(1); break;
			case 2: p.addHp(2); break;
			case 3: p.addGold(2); break;
			case 4: p.addXp(2); break;
			case 5: p.addArmor(1); break;
			case 6: // FIGHT MONSTER COMBAT STATE; break;
		}
	}
}
