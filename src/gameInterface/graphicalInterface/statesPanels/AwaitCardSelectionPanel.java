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

    public AwaitCardSelectionPanel(ObservableGame g) {
        game = g;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){
        card = new JButton("Carta 1");
        card1 = new JButton("Carta 2");
        card2 = new JButton("Carta 3");
        card3 = new JButton("Carta 4");
        card4 = new JButton("Carta 5");
        card5 = new JButton("Carta 6");
        card6= new JButton("Carta 7");

    }

    public void setupLayout(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(card);
        box.add(card1);
        box.setBackground(Color.BLACK);
        box.add(Box.createVerticalGlue());


        Box box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalGlue());
        box1.add(card2);
        box1.add(card3);
        box1.add(Box.createVerticalGlue());

        Box box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalGlue());
        box2.add(card4);
        box2.add(card5);
        box2.add(Box.createVerticalGlue());

        Box box3 = Box.createVerticalBox();
        box3.add(Box.createVerticalGlue());
        box3.add(card6);
        box3.add(Box.createVerticalGlue());

        add(box);
        add(box1);
        add(box2);
        add(box3);
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
        super.paintComponent(g);
        g.drawImage(GraphicalPanel.getGameBackground(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
