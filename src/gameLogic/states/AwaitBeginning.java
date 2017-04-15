package gameLogic.states;

import gameLogic.GameData;


public class AwaitBeginning extends StateAdapter  {
	

	public AwaitBeginning(GameData data){
		super(data);
	}
	
	@Override
	public RogueState setDificulty(int d){ 
		getGameData().setDificulty(d);
		return this; 
	}
	
	@Override
	public RogueState setStartingArea(RogueState state){
		return state;
	}
	
	@Override
	public RogueState startGame(){
		return new AwaitCardSelection(getGameData());
	}
}
