package gameLogic.cards;

import gameLogic.Constants;
import gameLogic.Player;

public class Merchant extends Card implements Constants {
	
	public Merchant(int l){
		super(l);
		name = "Merchant";
	}
	
	@Override
	public boolean isMerchant(){
		return true;
	}
	
	public int playerOption(Player p, int b){
		switch(b){
			case 1:
				if(p.canBuy(PRICE_RATION)){
					p.addFood(1);
					return 1;
				}
				break;
				
			case 2:
				if(p.canBuy(PRICE_HEALTH_POTION)){
					p.addHp(1);
					return 1;
				}
				break;
				
			case 3:
				if(p.canBuy(PRICE_BIG_HEALTH_POTION)){
					p.addHp(4);
					return 1;
				}
				break;
				
			case 4:
				if(p.canBuy(PRICE_ARMOR_PIECE)){
					p.addArmor(1);
					return 1;
				}
				break;
				
			case 5:
				if(p.canBuy(PRICE_SPELL)){
					p.addSpell((int)(Math.random() * 4) + 1);
					return 1;
				}
				break;
		}
		
		return 0;
	}
	
}
