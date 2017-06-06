package gameInterface.graphicalInterface;


import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;
import gameLogic.states.AwaitOptionSelection;
import gameLogic.states.AwaitTrading;
import gameLogic.states.combatStates.AwaitDiceReroll;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DungeonPanel extends JPanel implements Observer, Constants {
    private ObservableGame game;
    private JLabel dungeonImage;


    public DungeonPanel(ObservableGame g){
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        dungeonImage = new JLabel("");
    }

    private void setupLayout(){
        setPreferredSize(new Dimension(360, 505));
        add(dungeonImage);
    }

    @Override
    public void update(Observable t, Object o){
        if( game.getState() instanceof AwaitBeginning ||
                game.getState() instanceof AwaitTrading ||
                game.getState() instanceof AwaitOptionSelection ||
                game.getState() instanceof AwaitDiceReroll)
            setVisible(false);
        else
            setVisible(true);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GraphicalPanel.getDungeonCard(), 0, 0, 360, 505, this);
        g.drawImage(GraphicalPanel.getToken(), 30, 200, 30,30,this);
        g.drawImage(GraphicalPanel.getToken(), 305, 462, 30,30,this);
    }
}
