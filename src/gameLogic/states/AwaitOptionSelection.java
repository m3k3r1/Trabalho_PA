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
		getGameData().restingChoice(restingCard, option);
		return new AwaitCardSelection(getGameData());
	}
	

}