package gameLogic.cards;

import gameLogic.Constants;

import java.awt.image.BufferedImage;

public class Boss extends Card implements Constants{
	
	public Boss(BufferedImage l, int le){
        super(l, le);
        name = "Monster";

        initializeStats();
	}
	
	@Override
	public boolean isBoss(){
		return true;
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
