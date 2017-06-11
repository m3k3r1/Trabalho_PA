package gameLogic;

import com.sun.corba.se.spi.activation.ServerOperations;
import gameLogic.states.*;

import javax.swing.*;
import java.io.Serializable;

public class Game implements Constants , Serializable {
	private GameData data;
	private RogueState state; 
	
	public Game(Player p) {
		data = new GameData(p);
		setState(new AwaitBeginning(data));
	}

	//Player Data
	public int getHp(){
		return data.getHp();
	}
	public int getArmor(){
		return data.getArmor();
	}
	public int getGold(){
		return data.getGold();
	}
	public int getFood(){
		return data.getFood();
	}

	//Game Data
    public GameData getGameData(){
	    return data;
    }
    public String getSpell(int index){
	    switch (getDiceValue(index)){
            case 1 : return "Fire";
            case 2 : return "Ice";
            case 3 : return "Poison";
            case 4 : return "Heal";
        }

        return "NULL";
    }
	public String getBuffer(){
		return data.getBuffer();
	}
	public void clearBuffer(){
	    data.clearOutputBuffer();
    }
	public final Player getPlayer() {
        return data.getPlayer();
    }
	public ImageIcon getCard(int index){return data.getCard(index).getImage();}
	public boolean cardIsUsed(int index){
		return data.getCard(index).isUsed();
	}
    public int getLevel(){
        return data.getLevel();
    }
    public int getXp(){
        return data.getXp();
    }
    public int getArea(){
        return data.getArea();
    }
    public int getDiceSize(){
        return data.getDiceSize();
    }
    public int getDiceValue(int p){
        return data.getDiceValue(p);
    }
    public int getSpellValue(int p){
        return data.getSpellValue(p);
    }
    public String showCard(int c){
		//if(data.getCard(c).isTurned())
			return data.showCard(c);
		//1return "NOT TURNED";
	}
    public int getCardStackSize(){
        return data.getCardStackSize();
    }
	public int getMonsterHp(){
		return data.getMonsterHp();
	}
    public Boolean isTurned(int index){
	    return data.getCard(index).isTurned();
    }

    //States handling
    public RogueState getState(){
        return state;
    }
    public void setState(RogueState s){
        this.state = s;
    }

    public void startGame(){
		setState(getState().startGame());
	}
	public void setDificulty(int d){
		setState(getState().setDificulty(d));
	}
	public void setStartingArea(int a){
		setState(getState().setStartingArea(a));
	}
	public void chooseCard(int pos){
		setState(getState().setCard(pos));
	}
	public void chooseOption(int option){
		setState(getState().playerOption(option));


	}
	public void rerollDiceOption(int option){
		setState(getState().rerollDice(option));

	}
	public void featOption(boolean option, int dice){
		setState(getState().featOption(option, dice));

	}
	public void spellOption(boolean option, int spell){
		setState(getState().spellOption(option, spell));

	}
	public void skip(){
		setState(getState().skip());
	}
}
