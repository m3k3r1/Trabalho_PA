package gameLogic.states;

public interface RogueState {

	RogueState setDificulty(int d);
	RogueState setStartingArea(RogueState state);
	RogueState setCard(int pos);
	RogueState startGame();
	RogueState quit();
}
