package gameLogic.states;

import gameLogic.GameData;
import gameLogic.states.combatStates.AwaitDiceReroll;

public class AwaitCardSelection extends StateAdapter {

	public AwaitCardSelection(GameData d) {
		super(d);
	}
		
	@Override
	public  RogueState setCard(int card){
		
		if(getGameData().cardIsMerchant(getGameData().getCard(card))){
			return new AwaitTrading(getGameData(), card);
		}
		else if(getGameData().cardIsEvent(getGameData().getCard(card)) ){
			getGameData().eventType(card);
			return this;
		}
				
		else if(getGameData().cardIsTreasure(getGameData().getCard(card))){
			getGameData().treasureType(card);
			return this;
		}
		else if(getGameData().cardIsMonster(getGameData().getCard(card))){
			return new AwaitDiceReroll(getGameData());
		}
		
		return new AwaitOptionSelection(getGameData(), card); 
	}

}
