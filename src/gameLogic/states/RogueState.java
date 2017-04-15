package gameLogic.states;

public interface RogueState {

	RogueState setDificulty(int d);
	RogueState setStartingArea(RogueState state);
	RogueState startGame();
	RogueState quit();
}
