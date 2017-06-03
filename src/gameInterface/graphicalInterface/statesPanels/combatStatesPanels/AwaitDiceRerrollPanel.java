package gameInterface.graphicalInterface.statesPanels.combatStatesPanels;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.RogueState;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class AwaitDiceRerrollPanel extends JPanel implements Observer, Constants{

    ObservableGame game;

    public AwaitDiceRerrollPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void  setupComponents(){

    }

    public void setupLayout(){

    }


    @Override
    public void update(Observable t, Object o) {

        setVisible(false);

        RogueState state =  game.getState();
        if(state instanceof gameLogic.states.AwaitTrading) {
            System.out.println("TRAAwaitOptionSelection");
            setVisible(true);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
    }
}
