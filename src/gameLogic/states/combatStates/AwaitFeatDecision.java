package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;

public class AwaitFeatDecision extends StateAdapter{
	public AwaitFeatDecision(GameData d){
		super(d);
	}
	
	@Override
	public RogueState featOption(boolean option, int dice){
		if(option){
			getGameData().takeHp(-2);
			if(dice >= 0)
				getGameData().rerollDice(dice);
		}
		
		return new AwaitSpellDecision(getGameData());
	}
}
