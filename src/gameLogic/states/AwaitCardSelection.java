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
			if(!getGameData().hasHp())
				return new AwaitBeginning(getGameData());
			
			return new AwaitDiceReroll(getGameData(), card);
		} 
		
		return new AwaitOptionSelection(getGameData(), card); 
	}

}
