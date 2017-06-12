package gameLogic.cards;

import gameLogic.GameData;
import gameLogic.Player;

import java.awt.image.BufferedImage;

public class Trap extends Card {
    public Trap(BufferedImage l) {
        super(l);

        name = "Trap";
    }

    @Override
    public boolean isTrap() {
        return true;
    }


    @Override
    public int cardEffect(GameData data, int d) {
        switch (d) {
            case 1:
                if (data.getPlayer().getFood() > 0) {
                    data.getPlayer().addFood(-1);
                    data.outputBuffer("Food: -1");
                }
                break;
            case 2:
                if (data.getPlayer().getGold() > 0) {
                    data.getPlayer().addGold(-1);
                    data.outputBuffer("Gold: -1");
                }
                break;
            case 3:
                if (data.getPlayer().getArmor() > 0) {
                    data.getPlayer().addArmor(-1);
                    data.outputBuffer("Armor: -1");
                }
                break;
            case 4:
                data.getPlayer().addHp(-1);
                data.outputBuffer("Hp: -1");
                break;
            case 5:
                if (data.getPlayer().getXp() > 0) {
                    data.getPlayer().addXp(-1);
                    data.outputBuffer("Xp: -1");
                }
                break;
            case 6:
                //data.clearCardStack();
                //data.initializeCardStack();

                if (data.getLevel() != 14) {
                    if (data.getLevel() > 0 && data.getLevel() <= 2) {
                        data.setArea(data.getArea() + 2);
                        data.outputBuffer("You skip 2 Areas");
                    } else if (data.getLevel() >= 3 && data.getLevel() <= 4) {
                        data.setArea(data.getArea() + 3);
                        data.outputBuffer("You skip 3 Areas");
                    }

                    data.setLevel(data.getLevel() + 1);
                    data.outputBuffer("You skipped 1 level");
                }

                data.addHp(-2);
                data.outputBuffer("Hp: -2");

        }
        return 0;
    }
}
