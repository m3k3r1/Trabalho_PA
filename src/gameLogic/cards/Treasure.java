package gameLogic.cards;

import gameLogic.GameData;
import gameLogic.Player;

import java.awt.image.BufferedImage;

public class Treasure extends Card{
	public Treasure(BufferedImage l){
		super(l);
		name = "Treasure";
	}

	@Override
	public boolean isTreasure(){
		return true;
	}

	@Override
    public int cardEffect(GameData data, int d){
	    switch(d){
            case 1:
                data.getPlayer().addArmor(1);
                data.outputBuffer("Armor: +1");
                break;
            case 2:
                data.getPlayer().addXp(2);
                data.outputBuffer("Xp: +1");
                break;
            case 3:
                data.getPlayer().addSpell(1);
                data.outputBuffer("Spell Added");
                break;
            case 4:
                data.getPlayer().addSpell(2);
                data.outputBuffer("Spell Added");
                break;
            case 5:
                data.getPlayer().addSpell(3);
                data.outputBuffer("Spell Added");
                break;
            case 6:
                data.getPlayer().addSpell(4);
                data.outputBuffer("Spell Added");
                break;
        }

        return 0;}
	
}
