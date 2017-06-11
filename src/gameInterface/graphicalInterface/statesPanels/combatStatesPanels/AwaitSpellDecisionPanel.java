package gameInterface.graphicalInterface.statesPanels.combatStatesPanels;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.RogueState;
import gameLogic.states.combatStates.AwaitFeatDecision;
import gameLogic.states.combatStates.AwaitSpellDecision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitSpellDecisionPanel extends JPanel implements Observer, Constants {
    ObservableGame game;
    JLabel monsterCard;
    JButton buttons[];
    JButton start;

    public AwaitSpellDecisionPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        buttons = new JButton[game.getDiceStackSize()];
        start = new JButton("Start Fight");

        setupComponents();
        setupLayout();

        update(game, this);
    }
    public void  setupComponents(){
        monsterCard = new JLabel(new ImageIcon(GraphicalPanel.getMonsterCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        for(int i = 0; i < game.getSpellsSize(); i++){
            buttons[0] = new JButton("");
        }
    }
    public void setupLayout(){
        setPreferredSize(new Dimension(1300,600));
        add(monsterCard);

        Box box1 = Box.createVerticalBox();
        for(int i = 0 ; i < game.getSpellsSize(); i++)
            box1.add(buttons[i]);

        Box box2 = Box.createVerticalBox();
        box2.add(start);

        buttons[0].addActionListener(new Spell1Listener());
        start.addActionListener(new StartListener());

        add(box2);
        add(box1);

    }

    class Spell1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //if(game.getDiceValue(0) != 1)
            game.spellOption(0);
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

        for(int i = 0 ; i < game.getSpellsSize(); i++)
            buttons[i].setText(game.getSpell(i));


        if(!game.getBuffer().equals("")) {
            JOptionPane.showMessageDialog(null, game.getBuffer(), "InfoBox: " + "MiniRogue", JOptionPane.INFORMATION_MESSAGE);
            game.clearBuffer();
        }

        RogueState state =  game.getState();
        if(state instanceof AwaitSpellDecision)
            setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

    }

}
