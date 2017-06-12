package gameLogic.states;

import gameLogic.GameData;
import gameLogic.cards.Monster;
import gameLogic.states.combatStates.AwaitDiceReroll;
import gameLogic.states.combatStates.AwaitFeatDecision;

public class AwaitCardSelection extends StateAdapter {

	public AwaitCardSelection(GameData d) {
		super(d);
	}

	@Override
	public  RogueState setCard(int card){
		if(getGameData().getCard(card).isMerchant()){
			return new AwaitTrading(getGameData(), card);
		}
		else if(getGameData().getCard(card).isEvent()) {
            switch(getGameData().getCard(card).cardEffect(getGameData(), getGameData().throwDice())) {
                case 1:
                    getGameData().createEventMonster();
                    getGameData().getCard(card).useCard();
                    return new AwaitDiceReroll(getGameData(), 6);
                default: getGameData().getCard(card).useCard();return this;
            }
		}
		else if(getGameData().getCard(card).isTreasure()){
			getGameData().getCard(card).cardEffect(getGameData(), getGameData().throwDice());
            getGameData().getCard(card).useCard();
			return this;
		}
		else if(getGameData().getCard(card).isTrap()){
			if(getGameData().skillCheck(getGameData().throwDice())) {
				getGameData().getCard(card).cardEffect(getGameData(), getGameData().throwDice());
			}
            getGameData().getCard(card).useCard();
            return this;
		}
		else if(getGameData().getCard(card).isMonster() || getGameData().getCard(card).isBoss())
			    return new AwaitDiceReroll(getGameData(),card);


		return new AwaitOptionSelection(getGameData(), card); 
	}

}
