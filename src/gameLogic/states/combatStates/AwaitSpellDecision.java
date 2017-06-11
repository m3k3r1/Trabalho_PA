package gameLogic.states.combatStates;

import gameLogic.GameData;
import gameLogic.states.*;


public class AwaitSpellDecision extends StateAdapter {

    private int monsterCard;
    static int bonusDamage;
    boolean freezeSpell = false;

    public AwaitSpellDecision(GameData d, int card, int damage) {
        super(d);

        bonusDamage = 0;
        monsterCard = card;
    }

    @Override
    public RogueState skip() {
        getGameData().getCard(monsterCard).setHp(getGameData().getCard(monsterCard).getHp() - getGameData().calculateDiceSum() + bonusDamage);

        if (getGameData().getCard(monsterCard).getHp() > 0 && freezeSpell == false)
            getGameData().getPlayer().setHp(getGameData().getPlayer().getHp() - getGameData().getCard(monsterCard).getDamage());

        else {
            getGameData().getCard(monsterCard).useCard();
            if(getGameData().getCard(monsterCard).isBoss())
                getGameData().delBoss();
            return new AwaitCardSelection(getGameData());
        }

        return new AwaitDiceReroll(getGameData(), monsterCard);
    }

    @Override
    public RogueState spellOption(boolean option, int spell) {

        switch (spell) {
            case 1:
                bonusDamage += 8;
                break;
            case 2:
                freezeSpell = true;
                break;
            case 3:
                bonusDamage = 5;
                break;
            case 4:
                if (getGameData().getHp() + 8 > 30)
                    getGameData().setHp(30);
                else
                    getGameData().addHp(8);
        }

        getGameData().removeSpell();
        return this;
    }
}
