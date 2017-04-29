package gameLogic.cards;

import gameLogic.Player;

public class Treasure extends Card{
	public Treasure(int l){
		super(l);
	}
	
	@Override
	public void cardDiceEffect( Player p, int d){
		switch(d){
		case 1: p.addArmor(1);
				break;
		case 2: p.addXp(2);
				break;
		case 3: //ADD DAMAGE TO WHAT ???
				break;
		case 4: //FREZE TURN
				break;
		case 5: //ADD DAMAGE TO WHAT PER TURN
				break;
		case 6: p.addHp(8);
				break;
		}
	}
	
	@Override
	public boolean isTreasure(){
		return true;
	}
	
}
