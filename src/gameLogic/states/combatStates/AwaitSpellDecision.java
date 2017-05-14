package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;


public class AwaitSpellDecision extends StateAdapter {
	
	private int userDamage;
	private int monsterCard;
	static int bonusDamage;
	boolean freezeSpell = false;
	
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
		
		//USER HAS HP 
		if(getGameData().hasHp())
			attackMonster(userDamage);
		//USER HASN'T HP
		else
			return new AwaitBeginning(getGameData());
		
		//IF MONSTER HAS HP
		if(getGameData().hasHp(monsterCard) && !freezeSpell)
			attackUser();
		//IF MONSTER HASN'T HP
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
		
		if(getGameData().hasHp(monsterCard) && !freezeSpell){
			attackUser();
			
			freezeSpell= false;
			return new AwaitDiceReroll(getGameData() , monsterCard);
		}
		return new AwaitCardSelection(getGameData());
	}

}