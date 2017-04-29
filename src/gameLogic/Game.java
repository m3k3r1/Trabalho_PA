package gameLogic;

import gameLogic.states.*;

public class Game implements Constants {
	private GameData data;
	private RogueState state; 
	
	public Game(Player p) {
		data = new GameData(p);
		setState(new AwaitBeginning(data));
	}
	
	//
	public GameData getGameData(){
		return data;
	}
	public void setGameData(GameData d){
		this.data = d;
	}
	public RogueState getState(){
		return state;
	}
	private void setState(RogueState s){
		this.state = s; 
	}
	
	//
	public int throwDice(){
		return (int)(Math. random() * 6 + 1);
	}
	
	
	
	//Methods to be used for UI's
	public void setDificulty(int d){
		setState(getState().setDificulty(d));
	}
	public void setStartingArea(int a){
		setState(getState().setStartingArea(a));
	}
	public void chooseCard(int pos){
		setState(getState().setCard(pos));
	}
}
