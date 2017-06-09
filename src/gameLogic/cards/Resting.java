package gameLogic.cards;

import gameLogic.GameData;
import gameLogic.Player;

import java.awt.image.BufferedImage;

public class Resting extends Card{
	public Resting(BufferedImage l){
		super(l);
		name = "Resting";
	}
	
	@Override
	public boolean isResting(){
		return true;
	}

	@Override
    public int cardEffect(GameData data, int d){
        switch(d){
            case 1:
                data.getPlayer().addXp(1);
                data.outputBuffer("Xp : +1");
                break;
            case 2:
                data.getPlayer().addFood(1);
                data.outputBuffer("Food : +1");
                break;
            case 3:
                data.getPlayer().addHp(1);
                data.outputBuffer("Hp : +1");
                break;
        }

        return 0;
    }
}
