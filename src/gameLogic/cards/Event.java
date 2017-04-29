package gameLogic.cards;

public class Event extends Card{
	
	public Event(int l){
		super(l);
	}
	
	@Override
	public boolean isEvent(){
		return true;
	}
	
}
