package gameLogic.cards;

import gameLogic.Player;
import java.io.Serializable;

public abstract class Card implements Serializable{
	protected int hp;
	protected int dmg;
	protected int reward;
	protected int level;
	protected String name;

	public Card(){
		hp = 0;
		dmg = 0;
		reward = 0;
	}

	public Card(int l){
		level = l;
	}

	//Getter's
	public int getHp(){
		return hp;
	}
	public int getDamage(){
		return dmg;
	}
	public int getReward(){
		return reward;
	}

	//Setter's
	protected void setHp(int h){
		hp = h;
	}
	protected void setDamage(int d){
		dmg = d;
	}
	protected void setReward(int r){
		reward = r;
	}

	//
	@Override
	public String toString(){
		return name;
	}
	public boolean isMerchant(){
		return false;
	}
	public boolean isEvent(){
		return false;
	}
	public boolean isTreasure(){
		return false;
	}
	public boolean isResting(){
		return false;
	}
	public boolean isMonster(){
		return false;
	}

	//
	public void cardDiceEffect( Player p, int d){}
	public int playerOption( Player p, int d){return 0;}
}
