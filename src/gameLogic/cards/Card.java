package gameLogic.cards;
import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.GameData;
import gameLogic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Card implements Serializable{
	protected int hp;
	protected int dmg;
	protected int reward;
	protected int level;
	protected String name;
	protected boolean turned = false;
	protected boolean used = false;
	protected ImageIcon image;

	public Card(BufferedImage i){
	    image =  new ImageIcon(i.getScaledInstance(215, 290, Image.SCALE_SMOOTH));
    }
    public Card(BufferedImage i, int l){
        image =  new ImageIcon(i.getScaledInstance(215, 290, Image.SCALE_SMOOTH));
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
	public ImageIcon getImage(){
	    return image;
    }
	
	//Setter's
	public void setHp(int h){
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
	public void attackMonster(int damage){}

	public int cardEffect(GameData data, int d){return 0;}

	public void useCard(){ used = true; }
	public boolean isUsed(){ return used; }
}
