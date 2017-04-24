package gameLogic.cards;
import gameLogic.Player;;

public abstract class Card {
	protected int hp;
	protected int dmg;
	protected int reward;
	protected int level;
	
	public Card(){
		hp = 0;
		dmg = 0;
		reward = 0;
	}
	
	public Card(int l){
		level = l;
	}
	
	//
	public int getHp(){
		return hp;
	}
	public int getDamage(){
		return dmg;
	}
	public int getReward(){
		return reward;
	}
	
	//
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
	public void cardDiceEffect( Player p, int d){

	}
}
