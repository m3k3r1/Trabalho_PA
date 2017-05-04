package gameLogic.states;

import gameLogic.GameData;

public class AwaitCardSelection extends StateAdapter {

	public AwaitCardSelection(GameData d) {
		super(d);
	}
	
	@Override
	public RogueState playerOption(int option, int card){
		getGameData().mechantTransaction(card, option);
		return new AwaitTrading(getGameData());
	}
	
	
	@Override
	public  RogueState setCard(int card, int option){
		
		if(getGameData().cardIsMerchant(getGameData().getCard(card))){
			return playerOption(option, card);
		}
		else if(getGameData().cardIsEvent(getGameData().getCard(card)) ){
			getGameData().eventType(card);
			return this;
		}
				
		else if(getGameData().cardIsTreasure(getGameData().getCard(card))){
			getGameData().treasureType(card);
			return this;
		}
			

		return new AwaitOptionSelection(getGameData(), card); 
	}

}
