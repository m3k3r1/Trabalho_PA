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

        if(getGameData().nCardsTurned() == getGameData().getCardStackSize()){
            getGameData().setArea(getGameData().getArea() + 1);
            getGameData().clearCardStack();
            getGameData().initializeCardStack();
            return this;
        }
		return new AwaitCardSelection(getGameData());
		
	}

}
