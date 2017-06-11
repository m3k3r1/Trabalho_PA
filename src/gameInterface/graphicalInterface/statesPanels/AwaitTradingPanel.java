package gameInterface.graphicalInterface.statesPanels;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitTradingPanel extends JPanel implements Observer, Constants {

    ObservableGame game ;

    private JLabel merchantImage;

    private JButton buy[];
    private JButton sell[];
    private JButton skip;

    public AwaitTradingPanel(ObservableGame g){
        game = g;
        this.game.addObserver(this);

        buy = new JButton[6];
        sell = new JButton[3];

        setupComponents();
        setupLayout();

        update(game, this);
    }
    public void setupComponents(){
        merchantImage = new JLabel(new ImageIcon(GraphicalPanel.getMerchantCard().getScaledInstance(360, 505, Image.SCALE_SMOOTH)));

        buy[0] = new JButton("Ration");
        buy[1] = new JButton("Health Potion");
        buy[2] = new JButton("Big Health Potion");
        buy[3] = new JButton("Armor Piece");
        buy[4] = new JButton("Any Spell");

        sell[0] = new JButton("Armor Piece");
        sell[1] = new JButton("Spell");

        skip = new JButton("Finish");
    }
    public void setupLayout(){

        setPreferredSize(new Dimension(1300,600));
        Box image = Box.createVerticalBox();
        image.add(merchantImage);
        Box box1 = Box.createVerticalBox();
        box1.add(buy[0]);
        box1.add(buy[1]);
        box1.add(buy[2]);
        box1.add(buy[3]);
        box1.add(buy[4]);
        Box box2 = Box.createVerticalBox();
        box2.add(sell[0]);
        box2.add(sell[1]);
        Box box3 = Box.createVerticalBox();
        box3.add(skip);

        add(image);
        add(box1);
        add(box2);
        add(box3);

        buy[0].addActionListener(new RationListener());
        buy[1].addActionListener(new HealthPotionListener());
        buy[2].addActionListener(new BigHealthPotionListener());
        buy[3].addActionListener(new ArmorPiece());
        buy[4].addActionListener(new AnySpell());
        sell[0].addActionListener(new AnyPiece());
        sell[1].addActionListener(new AnySpellSell());
        skip.addActionListener(new SkipListener());
    }

    //Listeners
    class RationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            game.chooseOption(1);
        }
    }
    class HealthPotionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            game.chooseOption(2);
        }
    }
    class BigHealthPotionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            game.chooseOption(3);
         }
    }
    class ArmorPiece implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            game.chooseOption(4);
        }
    }
    class AnySpell implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseOption(5);
        }
    }
    class AnyPiece implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseOption(6);
        }
    }
    class AnySpellSell implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseOption(7);
        }
    }
    class SkipListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.skip();
        }
    }

    @Override
    public void update(Observable t, Object o) {

        setVisible(false);

        RogueState state =  game.getState();
        if(state instanceof AwaitTrading) {
            setVisible(true);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
    }
}