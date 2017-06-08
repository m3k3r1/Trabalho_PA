package gameInterface.graphicalInterface.statesPanels.combatStatesPanels;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.combatStates.AwaitSpellDecision;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class AwaitSpellDecisionPanel extends JPanel implements Observer, Constants {
    ObservableGame game;

    public AwaitSpellDecisionPanel(ObservableGame g){
        game = g;
        game.addObserver(this);

        update(game, this);
    }

    @Override
    public void update(Observable t, Object o) {

    }

    @Override
    protected void paintComponent(Graphics g) {

    }

}
