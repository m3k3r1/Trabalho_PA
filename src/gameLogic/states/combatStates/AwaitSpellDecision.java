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

        getGameData().getCard(monsterCard).setHp(getGameData().getCard(monsterCard).getHp() - getGameData().calculateDiceSum());
	}

	@Override
	public RogueState skip(){
        if(getGameData().getPlayer().hasSpell())
            return new AwaitSpellDecision(getGameData(), monsterCard,userDamage);

        if(!getGameData().hasHp())
            return new AwaitBeginning(getGameData());

        if(getGameData().getCard(monsterCard).getHp() > 0)
            getGameData().getPlayer().setHp(getGameData().getPlayer().getHp() -getGameData().getCard(monsterCard).getDamage());

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
			
			getGameData().removeSpell();
		}

		else{
			bonusDamage = 0;
			return new AwaitBeginning(getGameData());
		}

		if(getGameData().getCard(monsterCard).getHp() > 0 && !freezeSpell){
            getGameData().getPlayer().setHp(getGameData().getPlayer().getHp() -getGameData().getCard(monsterCard).getDamage());

			freezeSpell= false;
			return new AwaitDiceReroll(getGameData() , monsterCard);
		}

		return new AwaitCardSelection(getGameData());
	}

}
