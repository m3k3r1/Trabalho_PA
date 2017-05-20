package gameLogic.states;

import gameLogic.GameData;


public class AwaitBeginning extends StateAdapter  {
	

	public AwaitBeginning(GameData data){
		super(data);
		getGameData().clearCardStack();
	}
	
	@Override
	public RogueState setDificulty(int d){ 
		getGameData().setDificulty(d);
		return this; 
	}
	
	@Override
	public RogueState setStartingArea(int a){
		getGameData().setArea(a);
		return this;
	}
	
	@Override
	public RogueState startGame(){
		getGameData().initializeCardStack();
		return new AwaitCardSelection(getGameData());
	}
}
