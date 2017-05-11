package gameLogic;

import gameLogic.states.*;

public class Game implements Constants {
	private GameData data;
	private RogueState state; 
	
	public Game(Player p) {
		data = new GameData(p);
		setState(new AwaitBeginning(data));
	}
	
	// GETS
	public GameData getGameData(){
		return data;
	}
	public void setGameData(GameData d){
		this.data = d;
	}
	public RogueState getState(){
		return state;
	}
	public int getLevel(){ 
		return data.getLevel();
	}
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
	public String showCard(int c){
		return data.showCard(c);
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
	
	// SETS
	private void setState(RogueState s){
		this.state = s; 
	}

	
	//Methods to be used for UI's
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
