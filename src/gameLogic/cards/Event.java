package gameLogic.cards;

import gameLogic.GameData;
import gameLogic.Player;

import java.awt.image.BufferedImage;

public class Event extends Card{
	
	public Event(BufferedImage i){
		super(i);
		name = "Event";
	}
	
	@Override
	public boolean isEvent(){
		return true;
	}

	@Override
    public int cardEffect(GameData data, int d){
        switch(d)  {
            case 1:
                data.getPlayer().addFood(1);
                data.outputBuffer("Food: +1");
                return 0;
            case 2:
                data.getPlayer().addHp(2);
                data.outputBuffer("Hp: +2");
                return 0;
            case 3:
                data.getPlayer().addGold(2);
                data.outputBuffer("Armor: +2");
                return 0;
            case 4:
                data.getPlayer().addXp(2);
                data.outputBuffer("Xp: +2");
                return 0;
            case 5:
                data.getPlayer().addArmor(1);
                data.outputBuffer("Armor: +1");
                return 0;
            case 6:
                data.outputBuffer("YOU FOUND A MONSTER");
                return 1;
        }

        return 0;
    }
}
