package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;

public class AwaitFeatDecision extends StateAdapter{
	private int monsterCard;

	public AwaitFeatDecision(GameData d, int card){
		super(d);
		monsterCard = card;

		getGameData().getCard(card).setHp(getGameData().getCard(card).getHp() - getGameData().calculateDiceSum());
	}
	
	@Override
	public RogueState skip(){
	    if(getGameData().getPlayer().hasSpell() && getGameData().calculateDiceSum() > 0)
            return new AwaitSpellDecision(getGameData(), monsterCard,getGameData().calculateDiceSum());

        if(!getGameData().hasHp())
            return new AwaitBeginning(getGameData());

        if(getGameData().getMonsterHp() > 0)
            getGameData().getPlayer().setHp(getGameData().getPlayer().getHp() - getGameData().getCard(monsterCard).getDamage());
        else
            return new AwaitCardSelection(getGameData());

        if(getGameData().diceStackhas6())
            return new AwaitDiceReroll(getGameData(),monsterCard);

        getGameData().refreshDices();
        return new AwaitFeatDecision(getGameData(), monsterCard);
	}


    @Override
	public RogueState featOption(boolean option, int dice){
		if(option){
            getGameData().rerollDice(dice);
			getGameData().getPlayer().addHp(-2);
		}

        return this;
	}
}
