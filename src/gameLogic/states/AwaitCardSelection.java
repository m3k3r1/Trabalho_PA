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
            switch(getGameData().getCard(card).cardDiceEffect(getGameData().getPlayer(), getGameData().throwDice())) {
                //TODO make all the different cases
                case 1:
                    //TODO use to buffer to output return option
                    getGameData().createEventMonster();
                    return new AwaitDiceReroll(getGameData(), 6);
                default: return this;
            }
		}
		else if(getGameData().getCard(card).isTreasure()){
		    //TODO use toBuffer() to add the output choice 
			getGameData().getCard(card).cardDiceEffect(getGameData().getPlayer(), getGameData().throwDice());
			if(!getGameData().hasHp())
				return new AwaitBeginning(getGameData());
			return this;
		}
		else if(getGameData().getCard(card).isTrap()){
			getGameData().getCard(card).trapEffect(getGameData(),getGameData().throwDice() );
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
