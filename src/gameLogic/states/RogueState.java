package gameLogic.states;

import java.io.Serializable;

public interface RogueState extends Serializable {

	RogueState setDificulty(int d);
	RogueState setStartingArea(int a);
	RogueState setCard(int card);
	RogueState setCard(int card, int option);
	RogueState playerOption(int option);
	RogueState playerOption(int option, int card);
	RogueState skip();
	RogueState startGame();
	RogueState quit();
	RogueState featOption(boolean option, int dice);
	RogueState spellOption(boolean option, int spell);
	
	// COMBAT METHODS
	RogueState rerollDice(int dice);
}
