package gameInterface.graphicalInterface.statesPanels.combatStatesPanels;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.RogueState;
import gameLogic.states.combatStates.AwaitDiceReroll;
import gameLogic.states.combatStates.AwaitFeatDecision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitFeatDecisionPanel extends JPanel implements Observer, Constants {
    ObservableGame game;
    JLabel monsterCard;
    JButton buttons[];
    JButton start;
    JLabel state;

    public AwaitFeatDecisionPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        buttons = new JButton[game.getDiceStackSize()];
        start = new JButton("Start Fight");
        state = new JLabel("Feat - You will loose 2hp to rerroll");

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

        Box box2 = Box.createVerticalBox();
        box2.add(start);

        buttons[0].addActionListener(new Dice1Listener());
        start.addActionListener(new StartListener());
        state.setForeground(Color.WHITE);
        add(state);
        add(box2);
        add(box1);

    }

    class Dice1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //if(game.getDiceValue(0) != 1)
            game.featOption(true, 0);
        }

    }
    class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.skip();
        }

    }

    @Override
    public void update(Observable t, Object o) {

        setVisible(false);

        if(!game.getBuffer().equals(""))
            JOptionPane.showMessageDialog(null, game.getBuffer(), "InfoBox: " + "MiniRogue", JOptionPane.INFORMATION_MESSAGE);

        for(int i = 0; i < game.getDiceStackSize(); i++)
            buttons[i].setText(""+game.getDiceValue(i));


        RogueState state =  game.getState();
        if(state instanceof AwaitFeatDecision)
            setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
