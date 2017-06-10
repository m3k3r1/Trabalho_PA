package gameInterface.graphicalInterface.statesPanels.combatStatesPanels;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.RogueState;
import gameLogic.states.combatStates.AwaitDiceReroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitDiceRerrollPanel extends JPanel implements Observer, Constants{

    ObservableGame game;
    JLabel monsterCard;
    JButton buttons[];

    public AwaitDiceRerrollPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        buttons = new JButton[game.getDiceStackSize()];

        setupComponents();
        setupLayout();

        update(game, this);
    }
    public void  setupComponents(){
        monsterCard = new JLabel(new ImageIcon(GraphicalPanel.getMonsterCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        for(int i = 0; i < game.getDiceStackSize(); i++){
            buttons[0] = new JButton("" + game.getDiceValue(0));
        }
    }
    public void setupLayout(){
        setPreferredSize(new Dimension(1300,600));
        add(monsterCard);


        Box box1 = Box.createVerticalBox();
        for(int i = 0 ; i < game.getDiceStackSize(); i++)
            box1.add(buttons[i]);

        add(box1);
    }

    class Dice1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.isTurned(1) )
                game.chooseCard(1);
        }

    }


    @Override
    public void update(Observable t, Object o) {

        setVisible(false);

        RogueState state =  game.getState();
        if(state instanceof AwaitDiceReroll) {

            setVisible(true);
        }

    }
    @Override
    protected void paintComponent(Graphics g) {

    }
}
