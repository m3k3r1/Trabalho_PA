package gameInterface.graphicalInterface;


import gameInterface.graphicalInterface.GraphicalPanel;
import gameInterface.graphicalInterface.Resources;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class UserStatsPanel extends JPanel implements Observer, Constants{

    private ObservableGame game;

    private JLabel statsImage;

    public UserStatsPanel(ObservableGame g){
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        statsImage = new JLabel(new ImageIcon(GraphicalPanel.getCharacterStats().getScaledInstance(940, 350, Image.SCALE_SMOOTH)));
    }

    private void setupLayout(){
        //Makes Panel Transparent
        setBackground(new Color(0,0,0,1));
        add(statsImage);
    }

    @Override
    public void update(Observable t, Object o){
        if( game.getState() instanceof AwaitBeginning)
            setVisible(false);
        else
            setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}
