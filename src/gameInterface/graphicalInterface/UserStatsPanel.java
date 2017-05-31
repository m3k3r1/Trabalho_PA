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


    //BUTOES SÒ PARA TESTE !!!!!!
    private JButton user;


    public UserStatsPanel(ObservableGame g){
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        user = new JButton("User");
    }

    private void setupLayout(){
        add(user);
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
        super.paintComponent(g);

        //g.drawImage(GraphicalPanel.getStats(), x, y,this);
    }
}
