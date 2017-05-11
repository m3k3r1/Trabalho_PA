package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;

public class AwaitDiceReroll extends StateAdapter {
	private int monsterCard;
	
	public AwaitDiceReroll(GameData d, int c){
		super(d);
		monsterCard = c;
		getGameData().generateDiceValues();
	}
	
	private void checkHp(){
		
	}
	
	@Override
	public RogueState skip(){
		return new AwaitFeatDecision(getGameData(), monsterCard);
	}
	
	@Override
	public RogueState rerollDice(int dice){
		getGameData().rerollDice(dice);
		
		return new AwaitFeatDecision(getGameData(), monsterCard);
	}
}
