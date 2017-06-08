package gameInterface.graphicalInterface.statesPanels;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitCardSelection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitCardSelectionPanel extends JPanel implements Observer, Constants {
    ObservableGame game;

    private JButton cards[] ;

    public AwaitCardSelectionPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        cards = new JButton[7];

        setupComponents();
        setupLayout();

        update(game, this);
    }
    public void setupComponents(){

        for(int i = 0; i < 6; i++)
            cards[i] = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
    }
    public void setupLayout(){

        for(int i = 0; i < 6; i++) {
            cards[i].setBorder(null);
            cards[i].setBorderPainted(false);
            cards[i].setMargin(new Insets(0, 0, 0, 0));
            cards[i].setFocusPainted(false);
            cards[i].setContentAreaFilled(false);
            cards[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        }



        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(1300,600));

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(cards[0]);
        box.add(Box.createHorizontalGlue());

        Box box1 = Box.createVerticalBox();
        box1.add(cards[1]);
        box1.add(Box.createVerticalGlue());
        box1.add(cards[2]);
        box1.add(Box.createHorizontalGlue());

        Box box2 = Box.createVerticalBox();
        box2.add(cards[3]);
        box2.add(Box.createHorizontalGlue());

        Box box3 = Box.createVerticalBox();
        box3.add(cards[4]);
        box3.add(Box.createVerticalGlue());
        box3.add(cards[5]);
        box3.add(Box.createHorizontalGlue());

        add(box);
        add(box1);
        add(box2);
        add(box3);

        cards[0].addActionListener(new Card1Listener());
        cards[1].addActionListener(new Card2Listener());
        cards[2].addActionListener(new Card3Listener());
        cards[3].addActionListener(new Card4Listener());
        cards[4].addActionListener(new Card5Listener());
        cards[5].addActionListener(new Card6Listener());
        //cards[6].addActionListener(new Card7Listener());


    }

    //Listeners
    class Card1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!game.isTurned(0))
                game.chooseCard(0);
        }
    }
    class Card2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseCard(1);
        }
    }
    class Card3Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseCard(2);
        }
    }
    class Card4Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseCard(3);
        }
    }
    class Card5Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseCard(4);
        }
    }
    class Card6Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseCard(5);
        }
    }
    class Card7Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.chooseCard(6);
        }
    }

    @Override
    public void update(Observable t, Object o) {

        if (game.getState() instanceof AwaitCardSelection){
            setVisible(true);

            //TODO develop this notification idea
            JOptionPane.showMessageDialog(this, game.getBuffer(), "InfoBox: " + "MiniRogue", JOptionPane.INFORMATION_MESSAGE);

            for (int i = 0; i < 6; i++){
                if(game.isTurned(i))
                    cards[i].setIcon(game.getCardImage(i));
            }
        }
        else
            setVisible(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
    }
}
