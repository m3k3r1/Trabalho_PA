package gameLogic.states;

import gameLogic.GameData;

public class StateAdapter implements RogueState , gameLogic.Constants{
	private GameData data;
	
	public StateAdapter(GameData d){
		this.data = d;
	}
	
	public GameData getGameData(){
		return data;
	}

	@Override
	public RogueState setDificulty(int d) {return this;}
	@Override
	public RogueState setStartingArea(int a) {return this;}
	@Override
	public RogueState setCard(int pos) {return this;}
	@Override
	public RogueState setCard(int card, int option) {return this;}
	@Override
	public RogueState playerOption(int option) {return this;}
	@Override
	public RogueState playerOption(int option, int card) {return this;}
	@Override
	public RogueState startGame() {return this;}
	@Override
	public RogueState quit() {return this;}
	@Override
	public RogueState skip() {return this;}
	@Override
	public RogueState rerollDice(int dice) {return this;}
	@Override
	public RogueState featOption(boolean option, int dice) {return this;}
	@Override
	public RogueState spellOption(boolean option, int spell) {return this;}

}
 