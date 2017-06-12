package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;

public class AwaitFeatDecision extends StateAdapter{
	private int monsterCard;

	public AwaitFeatDecision(GameData d, int card){
		super(d);
		monsterCard = card;
    }
	
	@Override
	public RogueState skip(){
	    if(getGameData().calculateDiceSum() != 0)
            return new AwaitSpellDecision(getGameData(), monsterCard,getGameData().calculateDiceSum());
		else{
            getGameData().getCard(monsterCard).setHp(getGameData().getCard(monsterCard).getHp() - getGameData().calculateDiceSum());

            if(getGameData().getCard(monsterCard).getHp() > 0){
                getGameData().getPlayer().setHp(getGameData().getPlayer().getHp() - getGameData().getCard(monsterCard).getDamage());
                return new AwaitDiceReroll(getGameData(),monsterCard);}
            else {
                getGameData().getPlayer().addXp(getGameData().getCard(monsterCard).getReward());
                getGameData().getCard(monsterCard).useCard();
                if(getGameData().getCard(monsterCard).isBoss()) {
                    getGameData().moveLevel();
                    getGameData().delBoss();
                }
                return new AwaitCardSelection(getGameData());
			}
            
		}
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
