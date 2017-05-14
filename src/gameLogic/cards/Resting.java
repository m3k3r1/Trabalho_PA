package gameLogic.cards;

import gameLogic.Player;

public class Resting extends Card{
	public Resting(int l){
		super(l);
		name = "Resting";
	}
	
	@Override
	public boolean isResting(){
		return true;
	}
	
	@Override
	public int playerOption(Player p, int r){
		switch(r){
			case 1: p.addXp(1); break;
			case 2: p.addFood(1); break;
			case 3: p.addHp(1); break;
		}
		
		return 0;
	}
}
