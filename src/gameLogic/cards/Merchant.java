package gameLogic.cards;

public class Merchant extends Card{
	
	public Merchant(int l){
		super(l);
	}
	
	@Override
	public boolean isMerchant(){
		return true;
	}
	
}
