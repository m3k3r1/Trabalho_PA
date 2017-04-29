package gameLogic.states;

import gameLogic.GameData;

public class AwaitCardSelection extends StateAdapter {

	public AwaitCardSelection(GameData d) {
		super(d);
	}
	
	@Override
	public  RogueState setCard(int card){
		
		if(getGameData().cardIsMerchant(getGameData().getCard(card)))
			return new AwaitTrading(getGameData());
		else if(getGameData().cardIsEvent(getGameData().getCard(card))||
				getGameData().cardIsTreasure(getGameData().getCard(card)))
			return this;
		
		return new AwaitOptionSelection(getGameData()); 
	}

}
