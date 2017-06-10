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
		
		userDamage = getGameData().calculateDiceSum();
		
		return new AwaitSpellDecision(getGameData(), monsterCard, userDamage);
	}
    private void attackMonster(int damage){
        getGameData().attackMonster(monsterCard, damage);
    }

    private void attackUser(){
        getGameData().attackUser(monsterCard);
    }

    @Override
	public RogueState featOption(boolean option, int dice){
		if(option){
			
			getGameData().takeHp(-2);
			
			if(dice >= 0)
				getGameData().rerollDice(dice);

			getGameData().getPlayer().addHp(-2);
		}
		
		
		userDamage = getGameData().calculateDiceSum();


        if(getGameData().hasHp())
            attackMonster(userDamage);

        else
            return new AwaitBeginning(getGameData());


        if(getGameData().hasHp(monsterCard)) {
            attackUser();
            return new AwaitDiceReroll(getGameData(), monsterCard);
        }else
            return new AwaitCardSelection(getGameData());

	}
}
