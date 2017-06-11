package gameLogic.cards;

import gameLogic.Constants;
import gameLogic.GameData;
import gameLogic.Player;

import java.awt.image.BufferedImage;

public class Merchant extends Card implements Constants {
	
	public Merchant(BufferedImage l){
		super(l);
		name = "Merchant";
	}
	
	@Override
	public boolean isMerchant(){
		return true;
	}

    @Override
    public int cardEffect(GameData data, int d){
        switch(d){
            case 1:
                if(data.getPlayer().canBuy(PRICE_RATION)){
                    data.getPlayer().addGold(-PRICE_RATION);
                    data.getPlayer().addFood(1);
                    data.outputBuffer("Food: +1");
                    return 1;
                }
                break;

            case 2:
                if(data.getPlayer().canBuy(PRICE_HEALTH_POTION)){
                    data.getPlayer().buy(PRICE_HEALTH_POTION);
                    data.getPlayer().addHp(1);
                    data.outputBuffer("Hp: +1");
                    return 1;
                }
                break;

            case 3:
                if(data.getPlayer().canBuy(PRICE_BIG_HEALTH_POTION)){
                    data.getPlayer().buy(PRICE_BIG_HEALTH_POTION);
                    data.getPlayer().addHp(4);
                    data.outputBuffer("Hp: +4");
                    return 1;
                }
                break;

            case 4:
                if(data.getPlayer().canBuy(PRICE_ARMOR_PIECE)){
                    data.getPlayer().buy(PRICE_ARMOR_PIECE);
                    data.getPlayer().addArmor(1);
                    data.outputBuffer("Armor: +1");
                    return 1;
                }
                break;

            case 5:
                if(data.getPlayer().canBuy(PRICE_SPELL)){
                    data.getPlayer().buy(PRICE_SPELL);
                    data.getPlayer().addSpell((int)(Math.random() * 4) + 1);
                    data.outputBuffer("Spell Added");
                    return 1;
                }
                break;

            case 6:
                if(data.getPlayer().getArmor() > 0){
                    data.getPlayer().addArmor(-1);
                    data.getPlayer().addGold(3);
                    data.outputBuffer("Sold 1 armor piece! Gold: +3");
                }
                else
                    data.outputBuffer("You have no armor pieces to sell!");
                break;

            case 7:
                if(data.getPlayer().hasSpell()){
                    data.getPlayer().removeSpell();
                    data.getPlayer().addGold(4);
                    data.outputBuffer("Spell sold. Gold: +4");
                }
                else
                    data.outputBuffer("No spells to sell!");
                break;

        }

        return 0;
    }
}
