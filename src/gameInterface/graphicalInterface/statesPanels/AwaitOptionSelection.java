package gameInterface.graphicalInterface.statesPanels;


import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;
import gameLogic.states.AwaitCardSelection;
import gameLogic.states.RogueState;
import gameLogic.states.combatStates.AwaitDiceReroll;
import gameLogic.states.combatStates.AwaitFeatDecision;
import gameLogic.states.combatStates.AwaitSpellDecision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitOptionSelection extends JPanel implements Observer, Constants{

    ObservableGame game ;

    private JLabel restingImage;
    private JButton button1;
    private JButton button2;
    private JButton button3;


    public AwaitOptionSelection(ObservableGame g){
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        restingImage = new JLabel(new ImageIcon(GraphicalPanel.getRestingCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        button1 = new JButton("Reinforce Weapon");
        button2 = new JButton("Search Ration");
        button3 = new JButton("Heal");
    }

    public void setupLayout(){
        setPreferredSize(new Dimension(1300,600));

        Box image = Box.createVerticalBox();
        image.add(restingImage);
        Box box1 = Box.createVerticalBox();
        box1.add(button1);
        box1.add(button2);
        box1.add(button3);

        add(image);
        add(box1);


        button1.addActionListener(new WeaponListener());
        button2.addActionListener(new FoodListener());
        button3.addActionListener(new HealingListener());
    }

    //Listeners
    class WeaponListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseOption(1);
        }
    }
    class FoodListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {game.chooseOption(2);        }
    }
    class HealingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseOption(3);
        }
    }

    @Override
    public void update(Observable t, Object o) {
        setVisible(false);

        RogueState state =  game.getState();
        if(state instanceof gameLogic.states.AwaitOptionSelection) {
            setVisible(true);
        }


    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
