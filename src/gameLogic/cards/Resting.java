package gameLogic.cards;

import gameLogic.Player;

public class Resting extends Card{
	public Resting(int l){
		super(l);
	}
	
	@Override
	public boolean isResting(){
		return true;
	}
	
	public void restingDecision(Player p, int r){
		switch(r){
			case 1: p.addXp(1); break;
			case 2: p.addFood(1); break;
			case 3: p.addHp(1); break;
		}
	}
}
