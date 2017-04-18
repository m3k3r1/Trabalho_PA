package gameLogic.states;

import gameLogic.GameData;
import gameLogic.cards.Card;

public class StateAdapter implements RogueState , gameLogic.Constants{
	private GameData data;
	
	public StateAdapter(GameData d){
		this.data = d;
	}
	
	public GameData getGameData(){
		return data;
	}

	@Override
	public RogueState setDificulty(int d) {return this;}
	@Override
	public RogueState setStartingArea(RogueState state) {return this;}
	@Override
	public RogueState setCard(Card chosenCard) {return this;}
	@Override
	public RogueState startGame() {return this;}
	@Override
	public RogueState quit() {return this;}

}
 