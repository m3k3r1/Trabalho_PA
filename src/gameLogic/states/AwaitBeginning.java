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
	public RogueState setStartingArea(int a){
	    if(a <= 2)
	        getGameData().setLevel(1);
	    else if( a > 2 && a <= 4)
	        getGameData().setLevel(2);
	    else if(a > 4 && a <= 7)
	        getGameData().setLevel(3);
	    else if(a > 7 && a <=14)
	        getGameData().setLevel(4);

        getGameData().setArea(a);
		return this;
	}
	
	@Override
	public RogueState startGame(){
		getGameData().initializeCardStack();
		return new AwaitCardSelection(getGameData());
	}
}
