package gameLogic.states;

import gameLogic.cards.*;

public interface RogueState {

	RogueState setDificulty(int d);
	RogueState setStartingArea(RogueState state);
	RogueState setCard(Card chosenCard);
	RogueState startGame();
	RogueState quit();
}
