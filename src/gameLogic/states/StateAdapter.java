package gameLogic.states;

import gameLogic.GameData;
import gameLogic.Player;

public class StateAdapter implements RogueState , gameLogic.Constants {
	private GameData data;
	
	public StateAdapter(GameData d){
		this.data = d;
	}
	
	public GameData getGameData(){
		return data;
	}
	private void toBuffer(String s){
		getGameData().outputBuffer(s);
	}

	@Override
	public RogueState setDificulty(int d) {return this;}
	@Override
	public RogueState setStartingArea(int a) {return this;}
	@Override
	public RogueState setCard(int pos) {return this;}
	@Override
	public RogueState playerOption(int option) {return this;}
	@Override
	public RogueState startGame() {return this;}
	@Override
	public RogueState checkNewArea(){
		System.out.print("Stack : " + getGameData().getCardStackSize() +
		"Cards Turned : " + getGameData().nCardsTurned());
		if(getGameData().getCardStackSize() == getGameData().nCardsTurned()) {
			getGameData().setArea(getGameData().getArea() + 1);
			getGameData().getPlayer().addFood(-2);
			getGameData().clearCardStack();
			getGameData().initializeCardStack();
		    return new AwaitCardSelection(getGameData());
		}
		return this;
	}
	@Override
    public RogueState checkHp(){
	    if(getGameData().getPlayer().getHp() <= 0) {
            getGameData().clearCardStack();
            getGameData().initializeCardStack();
			getGameData().outputBuffer("WASTED");
            return new AwaitBeginning(new GameData(new Player()));
        }
        return this;
    };
	@Override
	public RogueState skip() {return this;}
	@Override
	public RogueState rerollDice(int dice) {return this;}
	@Override
	public RogueState featOption(boolean option, int dice) {return this;}
	@Override
	public RogueState spellOption(boolean option, int spell) {return this;}

}
 