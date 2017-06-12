package gameLogic.cards;

import gameLogic.Constants;

import java.awt.image.BufferedImage;

public class Monster extends Card implements Constants{
	
	public Monster(BufferedImage l, int le){
		super(l, le);
		name = "Monster";
		
		initializeStats();
	}
	
	@Override
	public boolean isMonster(){
		return true;
	}
	
	@Override
	public void attackMonster(int damage){
		hp -= damage;
	}
	
	private void initializeStats(){
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
