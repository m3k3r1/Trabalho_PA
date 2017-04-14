package gameLogic;

import gameLogic.states.*;

public class Game implements Constants {
	private GameData data;
	private RogueState state; 
	
	public Game(Player p) {
		data = new GameData(p);
		setState(new AwaitBeginning(data));
	}
	
	private void setState(RogueState s){
		this.state = s; 
	}
	public RogueState getState(){
		return state;
	}
		
	public int throwDice(){
		return (int)(Math. random() * 6 + 1);
	}
	public void setDificulty(int d){
		data.setDificulty(d);
	}	
}
