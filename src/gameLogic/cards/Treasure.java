package gameLogic.cards;

import gameLogic.Player;

public class Treasure extends Card{
	public Treasure(int l){
		super(l);
		name = "Treasure";
	}
	
	@Override
	public void cardDiceEffect(Player p, int d){
		switch(d){
		case 1: p.addArmor(1);
				break;
		case 2: p.addXp(2);
				break;
		case 3: p.addSpell(1);
				break;
		case 4: p.addSpell(2);
				break;
		case 5: p.addSpell(3);
				break;
		case 6: p.addSpell(4);
				break;
		}
	}
	
	@Override
	public boolean isTreasure(){
		return true;
	}
	
}
