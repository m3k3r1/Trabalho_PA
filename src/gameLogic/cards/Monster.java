package gameLogic.cards;

import gameLogic.Constants;

public class Monster extends Card implements Constants{
	
	public Monster(int l){
		super(l);
		
		initializeStats();
	}
	
	private void initializeStats(){
		
		setHp((5*level) + 1);
		
		switch(level){
			case 1 : setDamage(MONSTER_DAMAGE_1);
					 setReward(MONSTER_REWARD_1);
					 break;
			case 2 : setDamage(MONSTER_DAMAGE_2);
					 setReward(MONSTER_REWARD_2);
					 break;
			case 3 : setDamage(MONSTER_DAMAGE_3);
					 setReward(MONSTER_REWARD_3);
					 break;
			case 4 : setDamage(MONSTER_DAMAGE_4);
					 setReward(MONSTER_REWARD_4);
					 break;
			case 5 : setDamage(MONSTER_DAMAGE_5);
					 setReward(MONSTER_REWARD_5);
					 break;
					 
		}
	}
}
