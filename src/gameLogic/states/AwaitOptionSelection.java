package gameLogic.states;

import gameLogic.GameData;

public class AwaitOptionSelection extends StateAdapter{
	int restingCard;

	public AwaitOptionSelection(GameData d, int c){
		super(d);
		restingCard = c;
	}
	
	@Override
	public RogueState playerOption(int option){
		getGameData().getCard(restingCard).cardEffect(getGameData(), option);

		getGameData().getCard(restingCard).useCard();

		return new AwaitCardSelection(getGameData());
	}
	

}