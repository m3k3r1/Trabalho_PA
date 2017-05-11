package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;


public class AwaitSpellDecision extends StateAdapter {
	
	private int userDamage;
	private int monsterCard;
	static int bonusDamage;
	
	public AwaitSpellDecision(GameData d, int card ,int damage){
		super(d);
		
		bonusDamage = 0;
		userDamage = damage;
		monsterCard = card;
	}
	
	private void attackMonster(int damage){
		getGameData().attackMonster(monsterCard, damage);
	}
	
	private void attackUser(){
		getGameData().attackUser(monsterCard);
	}
	
	@Override
	public RogueState skip(){
		if(getGameData().hasHp())
			attackMonster(userDamage);
		else
			return new AwaitBeginning(getGameData());
		
		if(getGameData().hasHp(monsterCard))
			attackUser();
		else{
			if(getGameData().cardIsBoss(getGameData().getCard(monsterCard)))
				getGameData().nextLevel();
		
			bonusDamage = 0;
			return new AwaitCardSelection(getGameData());
		}
		
		return new AwaitDiceReroll(getGameData(), monsterCard);
	}
	
	@Override
	public RogueState spellOption(boolean option, int spell){
		boolean freezeSpell = false;
		
		if(option){
			switch(spell){
				case 1: 
					userDamage += 8;
					break;
				case 2: 
					freezeSpell = true;
					break;
				case 3:
					bonusDamage = 5;
					break;
				case 4:
					if(getGameData().getHp() + 8 > 30)
						getGameData().setHp(30);
					else
						getGameData().addHp(8);
			}
			
			getGameData().removeSpell(spell);
		}
		
		if(getGameData().hasHp())
				attackMonster(userDamage);
		else{
			bonusDamage = 0;
			return new AwaitBeginning(getGameData());
		}
		
		if(getGameData().hasHp(monsterCard)){
			attackUser();
			return new AwaitDiceReroll(getGameData() , monsterCard);
		}
		return new AwaitCardSelection(getGameData());
	}

}