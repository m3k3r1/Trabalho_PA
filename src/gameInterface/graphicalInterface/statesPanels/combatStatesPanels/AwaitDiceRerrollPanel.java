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
    JButton start;
    JLabel state;

    public AwaitDiceRerrollPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        buttons = new JButton[game.getDiceStackSize()];
        start = new JButton("Start Fight");
        state = new JLabel("Do you wanna rerroll ? ");

        setupComponents();
        setupLayout();

        update(game, this);
    }
    public void  setupComponents(){
        monsterCard = new JLabel(new ImageIcon(GraphicalPanel.getMonsterCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        for(int i = 0; i < game.getDiceStackSize(); i++){
                buttons[i] = new JButton("" + game.getDiceValue(i));
        }
    }
    public void setupLayout(){
        setPreferredSize(new Dimension(1300,600));
        add(monsterCard);

        Box box1 = Box.createVerticalBox();
        for(int i = 0 ; i < game.getDiceStackSize(); i++)
            box1.add(buttons[i]);

        Box box2 = Box.createVerticalBox();
        box2.add(start);

        buttons[0].addActionListener(new Dice1Listener());
        start.addActionListener(new StartListener());
        state.setForeground(Color.WHITE);
        add(state);
        add(box2);
        add(box1);

    }

    class Dice1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.getDiceValue(0) == 6)
                game.rerrollDice(0);
        }

    }
    class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //if(game.getDiceValue(0) != 1)
            game.skip();
        }

    }

    @Override
    public void update(Observable t, Object o) {
        setVisible(false);

        RogueState state =  game.getState();
        if(state instanceof AwaitDiceReroll){
            setVisible(true);

            for(int i = 0; i < game.getDiceStackSize(); i++) {
                if (game.getDiceValue(i) == 0) {
                    buttons[i].setText(" LOCKED ");
                    continue;
                }
                buttons[i].setText(""+game.getDiceValue(i));
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
