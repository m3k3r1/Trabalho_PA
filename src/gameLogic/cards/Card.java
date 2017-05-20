package gameLogic.cards;
import gameLogic.GameData;
import gameLogic.Player;

public abstract class Card {
	protected int hp;
	protected int dmg;
	protected int reward;
	protected int level;
	protected String name;
	protected boolean turned = false;
	
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

	public void turnCard(){
		turned = true;
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
	public boolean isBoss(){
		return false;
	}
	public boolean isTrap(){
		return false;
	}
	public boolean isTurned(){ return turned; }
	public void attackMonster(int damage){
		
	}
	
	//
	public int cardDiceEffect(Player p, int d){return 0;}
	public int playerOption(Player p, int d){return 0;}

	public void trapEffect(GameData g, int d) {}
}
