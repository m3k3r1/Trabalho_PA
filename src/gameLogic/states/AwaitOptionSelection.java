package gameLogic.states;

import gameLogic.GameData;
import gameLogic.cards.Card;

public class AwaitOptionSelection extends StateAdapter{

	public AwaitOptionSelection(GameData d) {
		super(d);
	}
	
	@Override
	public RogueState setCard(int pos){
		return null;

	}

}