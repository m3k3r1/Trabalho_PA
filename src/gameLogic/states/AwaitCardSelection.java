package gameLogic.states;

import gameLogic.GameData;
import gameLogic.cards.Monster;
import gameLogic.states.combatStates.AwaitDiceReroll;

public class AwaitCardSelection extends StateAdapter {
	static int previousArea;
	
	public AwaitCardSelection(GameData d) {
		super(d);
	}
	
	private void areaAwareness(){
		if (getGameData().getArea() != 1 && getGameData().getArea() != previousArea)
			getGameData().nextArea();
		
		getGameData().dimFood();
		previousArea =  getGameData().getArea();

	}
	
	
	@Override
	public  RogueState setCard(int card){
		if(!getGameData().hasHp())
			return new AwaitBeginning(getGameData());

		areaAwareness();
		getGameData().getCard(card).turnCard();
		
		if(getGameData().getCard(card).isMerchant()){
			return new AwaitTrading(getGameData(), card);
		}

		else if(getGameData().getCard(card).isEvent()) {
			if(getGameData().getCard(card).cardDiceEffect(getGameData().getPlayer(), getGameData().throwDice()) == 0)
				return this;
			else{
				getGameData().createEventMonster();
				return new AwaitDiceReroll(getGameData() , 6);
			}
			
		}
				
		else if(getGameData().getCard(card).isTreasure()){
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
		else if(getGameData().getCard(card).isMonster() ||
				getGameData().getCard(card).isBoss()){
			if(!getGameData().hasHp())
				return new AwaitBeginning(getGameData());
			
			return new AwaitDiceReroll(getGameData(), card);
		} 
		
		return new AwaitOptionSelection(getGameData(), card); 
	}

}
