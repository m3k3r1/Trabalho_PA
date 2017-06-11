package gameLogic.states;

import gameLogic.GameData;

public class AwaitTrading extends StateAdapter {
	int card;
	

	public AwaitTrading(GameData d, int c){
		super(d);
		card = c;
	}
	
	@Override
	public RogueState skip(){
		return new AwaitCardSelection(getGameData());
	}
	
	@Override
	public RogueState playerOption(int option){


        getGameData().getCard(card).cardEffect(getGameData(), option);


		return new AwaitCardSelection(getGameData());
		
	}

}
