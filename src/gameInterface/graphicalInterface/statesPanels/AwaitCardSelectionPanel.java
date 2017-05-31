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


    //BUTOES SÒ PARA TESTE !!!!!!
    private JButton card;
    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JButton card5;
    private JButton card6;
    private JButton card7;

    public AwaitCardSelectionPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        card = new JButton(new ImageIcon(GraphicalPanel.getRestingCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card1 = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card2 = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card3 = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card4 = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card5 = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card6 = new JButton(new ImageIcon(GraphicalPanel.getBackCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));
        card7 = new JButton(new ImageIcon(GraphicalPanel.getBossCard().getScaledInstance(215, 290, Image.SCALE_SMOOTH)));

    }

    public void setupLayout(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(1300,600));

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(Box.createHorizontalGlue());
        box.add(card);
        box.add(Box.createHorizontalGlue());

        Box box1 = Box.createVerticalBox();
        box1.add(card1);
        box1.add(card2);
        box1.add(Box.createHorizontalGlue());


        Box box2 = Box.createVerticalBox();
        box2.add(card3);
        box2.add(Box.createHorizontalGlue());


        Box box3 = Box.createVerticalBox();
        box3.add(card4);
        box3.add(card5);
        box3.add(Box.createHorizontalGlue());


        Box box4 = Box.createVerticalBox();
        box4.add(card7);

        add(box);
        add(box1);
        add(box2);
        add(box3);
        add(box4);
    }

    @Override
    public void update(Observable t, Object o) {
        setVisible(game.getState() instanceof AwaitCardSelection);
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
