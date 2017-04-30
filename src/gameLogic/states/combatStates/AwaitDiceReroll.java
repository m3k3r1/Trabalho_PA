package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;

public class AwaitDiceReroll extends StateAdapter {
	public AwaitDiceReroll(GameData d){
		super(d);
		getGameData().generateDiceValues();
	}

	@Override
	public RogueState rerollDice(int dice){
		getGameData().rerollDice(dice);
		
		return new AwaitFeatDecision(getGameData());
	}
}
