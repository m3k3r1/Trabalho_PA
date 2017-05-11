package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;


public class AwaitSpellDecision extends StateAdapter {
	
	private int userDamage;
	private int monsterCard;
		
	public AwaitSpellDecision(GameData d, int card ,int damage){
		super(d);
		
		userDamage = damage;
		monsterCard = card;
	}
	
	private void attackMonster(int damage){
		getGameData().attackMonster(monsterCard, damage);
	}
	
	private void attackUser(){
		getGameData().attackUser(monsterCard);
	}
	
	@Override
	public RogueState skip(){
		if(getGameData().hasHp())
			attackMonster(userDamage);
		if(getGameData().hasHp(monsterCard))
			attackUser();
			
		return new AwaitDiceReroll(getGameData(), monsterCard);
	}
	
	@Override
	public RogueState spellOption(boolean option, int spell){
		if(option){
			switch(spell){
			 case 1: userDamage += 4 ;
			 		 break;
			 
			}
		}
		
		if(getGameData().hasHp())
				attackMonster(userDamage);
		else
			return new AwaitBeginning(getGameData());
		
		
		if(getGameData().hasHp(monsterCard)){
			attackUser();
			return new AwaitDiceReroll(getGameData() , monsterCard);
		}
		return new AwaitCardSelection(getGameData());
	}

}