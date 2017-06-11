package gameLogic.states.combatStates;

import gameLogic.states.*;
import gameLogic.GameData;

public class AwaitDiceReroll extends StateAdapter {
	private int monsterCard;
	
	public AwaitDiceReroll(GameData d, int c){
		super(d);
		monsterCard = c;

		getGameData().refreshDices();
	}

	@Override
	public RogueState skip(){
		return new AwaitFeatDecision(getGameData(), monsterCard);
	}
	
	@Override
	public RogueState rerollDice(int dice){

		if(getGameData().getDiceValue(dice) == 6)
            getGameData().addExtraDamage(dice);

		if(!getGameData().hasHp())
			return new AwaitBeginning(getGameData()); 

		getGameData().rerollDice(dice);

		return this;
	}
}
