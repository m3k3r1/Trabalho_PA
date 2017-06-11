package gameLogic.cards;

import gameLogic.Constants;

import java.awt.image.BufferedImage;

public class Boss extends Card implements Constants{
	
	public Boss(BufferedImage l, int le){
        super(l, le);
        name = "Boss";

        initializeStats();
	}
	
	@Override
	public boolean isBoss(){
		return true;
	}
	
	private void initializeStats(){

		switch(level){
			case 1 :
			    setDamage(BOSS_DAMAGE_1);
                setReward(BOSS_XP_REWARD_1);
                setHp(BOSS_HP_1);
                break;
			case 2 :
			    setDamage(BOSS_DAMAGE_2);
                setReward(BOSS_XP_REWARD_2);
                setHp(BOSS_HP_2);
                break;
			case 3 :
			    setDamage(BOSS_DAMAGE_3);
                setReward(BOSS_XP_REWARD_3);
                setHp(BOSS_HP_3);
                break;
			case 4 :
			    setDamage(BOSS_DAMAGE_4);
                setReward(BOSS_XP_REWARD_4);
                setHp(BOSS_HP_4);
                break;
			case 5 :
			    setDamage(BOSS_DAMAGE_5);
                setReward(BOSS_XP_REWARD_5);
                setHp(BOSS_HP_5);
			    break;
					 
		}
	}
}
