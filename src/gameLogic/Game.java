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
	public String getBuffer(){
		return data.getBuffer();
	}
	public final Player getPlayer(){
	    return data.getPlayer();
    }
    public void initialcizeCards(){
	    data.initializeCardStack();
    }
	public ImageIcon getCard(int index){return data.getCard(index).getImage();}
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
	public String getOutputBuffer(){return data.getBuffer();}
    public Boolean isTurned(int index){
	    return data.getCard(index).isTurned();
    }

    //States handling
    public RogueState getState(){
        return state;
    }
    private void setState(RogueState s){
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
        if(pos == 0 || pos == 3){
            data.getCard(pos+1).turnCard();
            data.getCard(pos+2).turnCard();
        }else if( pos == 1 )
            data.getCard(pos+2).turnCard();
        else if (pos == 2)
            data.getCard(pos+1).turnCard();

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
