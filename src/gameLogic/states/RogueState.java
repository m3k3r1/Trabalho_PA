package gameLogic.states;

public interface RogueState {

	RogueState setDificulty(int d);
	RogueState setStartingArea(int a);
	RogueState setCard(int card);
	RogueState skip();
	RogueState startGame();
	RogueState quit();
	
	// COMBAT METHODS
	RogueState rerollDice(int dice);
}
