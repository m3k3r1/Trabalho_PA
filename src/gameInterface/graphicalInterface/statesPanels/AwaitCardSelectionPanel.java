package gameInterface.graphicalInterface.statesPanels;


import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitCardSelection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitCardSelectionPanel extends JPanel implements Observer, Constants {
    ObservableGame game;

    private JButton card;
    private JButton card1;

    public AwaitCardSelectionPanel(ObservableGame g) {
        game = g;

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        card = new JButton("Carta");
        card1 = new JButton("Carta");
    }

    public void setupLayout(){
        add(card);
        add(card1);
    }

        @Override
    public void update(Observable t, Object o) {
        setVisible(game.getState()instanceof AwaitCardSelection);
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
}
