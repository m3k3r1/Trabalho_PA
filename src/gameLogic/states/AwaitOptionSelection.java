package gameLogic.states;

import gameLogic.GameData;

public class AwaitOptionSelection extends StateAdapter{
	int restingCard;
	
	
	public AwaitOptionSelection(GameData d) {
		super(d);
	}
	
	public AwaitOptionSelection(GameData d, int c){
		super(d);
		restingCard = c;
	}
	
	@Override
	public RogueState playerOption(int option){


		getGameData().getCard(restingCard).playerOption(getGameData().getPlayer(), getGameData().throwDice());

		if(!getGameData().hasHp())
			return new AwaitBeginning(getGameData());

		if(getGameData().nCardsTurned() == getGameData().getCardStackSize()){
            getGameData().setArea(getGameData().getArea() + 1);
            getGameData().clearCardStack();
            getGameData().initializeCardStack();
            return this;
        }
		return new AwaitCardSelection(getGameData());
	}
	

}