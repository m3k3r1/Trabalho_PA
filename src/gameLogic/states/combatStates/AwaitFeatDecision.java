package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;

public class AwaitFeatDecision extends StateAdapter{
	private int monsterCard;
	private int userDamage;
	
	public AwaitFeatDecision(GameData d, int card){
		super(d);
		monsterCard = card;
	}
	
	@Override
	public RogueState skip(){
		//calculate userDamage first
		userDamage = 1;
		
		return new AwaitSpellDecision(getGameData(), monsterCard, userDamage);
	}
	
	@Override
	public RogueState featOption(boolean option, int dice){
		if(option){
			getGameData().takeHp(-2);
			if(dice >= 0)
				getGameData().rerollDice(dice);
		}
		
		//Calculate dice sum
		userDamage = dice;
		
		return new AwaitSpellDecision(getGameData(), monsterCard, userDamage);
	}
}
