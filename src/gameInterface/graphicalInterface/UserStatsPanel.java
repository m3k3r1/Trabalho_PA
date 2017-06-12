package gameInterface.graphicalInterface;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;

import javax.swing.*;
import java.awt.*;
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
        statsImage = new JLabel();
    }

    private void setupLayout(){
        //Makes Panel Transparent
        setBackground(new Color(0,0,0,1));
        setPreferredSize(new Dimension(940, 350));
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
        super.paintComponent(g);
        g.drawImage(GraphicalPanel.getCharacterStats(), 0 ,0, 940, 350, this );

        // XP
        if(game.getPlayer().getXp() < 21)
            g.drawImage(GraphicalPanel.getToken(),70 + game.getPlayer().getXp() * 40, 34, 30, 30, this );
        else
            g.drawImage(GraphicalPanel.getToken(),70 + game.getPlayer().getXp() * 40, 76, 30, 30, this );

        // GOLD
        g.drawImage(GraphicalPanel.getToken(),70 + game.getPlayer().getGold() * 40, 148, 30, 30, this );

        // HP
        if(game.getPlayer().getHp() < 0)
            g.drawImage(GraphicalPanel.getToken(),70, 218 , 30, 30, this );
        else
            g.drawImage(GraphicalPanel.getToken(),70 + game.getPlayer().getHp() * 40, 218 , 30, 30, this );

        // ARMOR
        g.drawImage(GraphicalPanel.getToken(),70 + game.getPlayer().getArmor() * 40, 287, 30, 30, this );

        // SPELLS
        for(int i = 0, a = 0; i < 2; i++) {
            if(i == 1 && game.getPlayer().getSpellValue(i - 1) == game.getPlayer().getSpellValue(i))
                a++;

            if(game.getPlayer().getSpellValue(i) != 0)
                g.drawImage(GraphicalPanel.getToken(), 310 + a * 40 + ((80) * (game.getPlayer().getSpellValue(i) - 1 )), 287, 30, 30, this);
        }

        // FOOD
        g.drawImage(GraphicalPanel.getToken(),635 + (game.getPlayer().getFood() * 40), 287, 30, 30, this );
    }
}
