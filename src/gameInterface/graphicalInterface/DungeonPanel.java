package gameInterface.graphicalInterface;


import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;
import gameLogic.states.AwaitOptionSelection;
import gameLogic.states.AwaitTrading;
import gameLogic.states.combatStates.AwaitDiceReroll;
import gameLogic.states.combatStates.AwaitFeatDecision;
import gameLogic.states.combatStates.AwaitSpellDecision;

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

        if (game.getArea() <= 2)
            g.drawImage(GraphicalPanel.getToken(), 30 + (game.getArea() - 1) * 40, 200, 30, 30, this);
        if (game.getArea() >= 3 && game.getArea() <= 4)
            g.drawImage(GraphicalPanel.getToken(), 30 + (game.getArea() - 3) * 40, 260, 30, 30, this);
        if (game.getArea() >= 5 && game.getArea() <= 7)
            g.drawImage(GraphicalPanel.getToken(), 30 + (game.getArea() - 5) * 40, 320, 30, 30, this);
        if (game.getArea() >= 8 && game.getArea() <= 10)
            g.drawImage(GraphicalPanel.getToken(), 30 + (game.getArea() - 8) * 40, 380, 30, 30, this);
        if (game.getArea() >= 11)
            g.drawImage(GraphicalPanel.getToken(), 30 + (game.getArea() - 11) * 40, 440, 30, 30, this);

        if (game.getState() instanceof AwaitDiceReroll || game.getState() instanceof AwaitSpellDecision || game.getState() instanceof AwaitFeatDecision)
            if(game.getMonsterHp() < 11)
                g.drawImage(GraphicalPanel.getToken(), 305, 462 + (game.getMonsterHp() * 40), 30, 30, this);
            if(game.getMonsterHp() > 10 && game.getMonsterHp() < 21)
                g.drawImage(GraphicalPanel.getToken(), 263, 422 + (game.getMonsterHp() * 40) - 11, 30, 30, this);
            if(game.getMonsterHp() > 20)
                g.drawImage(GraphicalPanel.getToken(), 221, 422 + (game.getMonsterHp() * 40) - 21, 30, 30, this);
        else
            g.drawImage(GraphicalPanel.getToken(), 305, 462, 30, 30, this);
    }
}
