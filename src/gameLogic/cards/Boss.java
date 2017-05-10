package gameLogic.cards;

import gameLogic.Constants;

public class Boss extends Card implements Constants{
	
	public Boss(int l){
		super(l);
		name = "Boss";
		
		initializeStats();
	}
	
	private void initializeStats(){
		setHp(5*level);
		
		//TODO 
		switch(level){
			case 1 : setDamage(BOSS_DAMAGE_1);
					 break;
			case 2 : setDamage(BOSS_DAMAGE_2);
					 break;
			case 3 : setDamage(BOSS_DAMAGE_3);
					 break;
			case 4 : setDamage(BOSS_DAMAGE_4);
					 break;
			case 5 : setDamage(BOSS_DAMAGE_5);
					 break;
					 
		}
	}
}
