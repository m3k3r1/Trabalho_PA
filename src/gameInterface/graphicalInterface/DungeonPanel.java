package gameInterface.graphicalInterface;


import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;

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
        dungeonImage = new JLabel(new ImageIcon(GraphicalPanel.getDungeonCard().getScaledInstance(360, 505, Image.SCALE_SMOOTH)));
    }

    private void setupLayout(){
        add(dungeonImage);
    }

    @Override
    public void update(Observable t, Object o){
        if( game.getState() instanceof AwaitBeginning)
            setVisible(false);
        else
            setVisible(true);

    }

}
