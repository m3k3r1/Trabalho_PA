package gameLogic.states;

import gameLogic.GameData;
import gameLogic.cards.Monster;
import gameLogic.states.combatStates.AwaitDiceReroll;

public class AwaitCardSelection extends StateAdapter {
	static int previousArea;
	
	public AwaitCardSelection(GameData d) {
		super(d);
	}

	@Override
	public  RogueState setCard(int card){

	    getGameData().clearOutputBuffer();
		getGameData().getCard(card).turnCard();

		if(getGameData().getCard(card).isMerchant()){
			return new AwaitTrading(getGameData(), card);
		}
		else if(getGameData().getCard(card).isEvent()) {
            switch(getGameData().getCard(card).cardEffect(getGameData(), getGameData().throwDice())) {
                case 1:
                    getGameData().createEventMonster();
                    return new AwaitDiceReroll(getGameData(), 6);
                default: return this;
            }
		}
		else if(getGameData().getCard(card).isTreasure()){
			getGameData().getCard(card).cardEffect(getGameData(), getGameData().throwDice());
			if(!getGameData().hasHp())
				return new AwaitBeginning(getGameData());
			return this;
		}
		else if(getGameData().getCard(card).isTrap()){
			getGameData().getCard(card).cardEffect(getGameData(),getGameData().throwDice() );

			if(!getGameData().hasHp())
				return new AwaitBeginning(getGameData());
			return this;
		}
		else if(getGameData().getCard(card).isMonster() || getGameData().getCard(card).isBoss()){
			return new AwaitDiceReroll(getGameData(), card);
		} 
		
		return new AwaitOptionSelection(getGameData(), card); 
	}

}
