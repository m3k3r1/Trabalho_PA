package gameLogic.states;

import java.io.Serializable;

public interface RogueState extends Serializable {

    //AwaitBeginning
	RogueState setDificulty(int d);
	RogueState setStartingArea(int a);
    RogueState startGame();

    //AwaitCardSelection
	RogueState setCard(int card);

	//AwaitOptionSelection
	RogueState playerOption(int option);

	//AwaitDiceReroll
	RogueState rerollDice(int dice);

	//AwaitFeatDecision
    RogueState featOption(boolean option, int dice);

    //AwaitSpellDecision
    RogueState spellOption(boolean option, int spell);

    //Useful methods
    RogueState skip();
    RogueState checkNewArea();
    RogueState checkHp();

}
