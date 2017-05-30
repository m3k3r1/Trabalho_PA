package gameInterface.graphicalInterface.statesPanels;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitBeginningPanel extends JPanel implements Observer, Constants {

    private String[] dificulties = {"Casual", "Normal", "Hard", "Impossible"};
    private String[] areas = {"1", "2"};

    private JLabel dificultyLabel;
    private JComboBox dificultyComboBox;

    private JLabel areaLabel;
    private JComboBox areaComboBox;

    private JButton startGame;

    private ObservableGame game;



    public AwaitBeginningPanel(ObservableGame g){
        game = g;

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        dificultyLabel = new JLabel("Dificulty");
        dificultyComboBox  = new JComboBox(dificulties);
        areaLabel = new JLabel("Area");
        areaComboBox = new JComboBox(areas);
        startGame = new JButton("Start Game");
        startGame.addActionListener(new StartListener());
    }

    public void setupLayout(){


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(dificultyLabel);
        add(dificultyComboBox);
        add(Box.createVerticalGlue());
        add(areaLabel);
        add(areaComboBox);
        add(Box.createVerticalGlue());
        add(startGame);
    }

    @Override
    public void update(Observable t, Object o) {
        setVisible(game.getState()instanceof AwaitBeginning);
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            game.startGame( (dificultyComboBox.getSelectedIndex()+1), (areaComboBox.getSelectedIndex()+1));
            System.out.print("Dificuldade " + (dificultyComboBox.getSelectedIndex()+1) + " | " + "Area : " + (areaComboBox.getSelectedIndex()+1));
        }
    }


}
