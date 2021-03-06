package gameInterface.graphicalInterface.statesPanels;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.AwaitBeginning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AwaitBeginningPanel extends JPanel implements Observer, Constants {

    private String[] dificulties = {"Casual", "Normal", "Hard", "Impossible"};
    private String[] areas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};

    private JLabel dificultyLabel;
    private JComboBox dificultyComboBox;

    private JLabel areaLabel;
    private JComboBox areaComboBox;

    private JButton startGame;

    private ObservableGame game;

    public AwaitBeginningPanel(ObservableGame g){
        game = g;
        this.game.addObserver(this);
        setupComponents();
        setupLayout();

        update(game, this);
    }

    public void setupComponents(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width, screenSize.height));

        dificultyLabel = new JLabel("Dificulty");
        dificultyComboBox  = new JComboBox(dificulties);
        areaLabel = new JLabel("Area");
        areaComboBox = new JComboBox(areas);
        startGame = new JButton("Start Game");

        areaLabel.setFont(new Font("Arial", Font.BOLD, 15));
        areaLabel.setForeground(Color.WHITE);
        dificultyLabel.setFont(new Font("Arial", Font.BOLD, 15));
        dificultyLabel.setForeground(Color.WHITE);
        startGame.setFont(new Font("Arial", Font.BOLD, 15));

        startGame.addActionListener(new StartListener());
    }

    public void setupLayout(){
        setLayout(new GridBagLayout());

        setForeground(Color.WHITE);

        setBackground(new Color(0,0,0,1));
        setPreferredSize(new Dimension(690, 1000));

        Box dificultyBox = Box.createVerticalBox();
        dificultyBox.add(dificultyLabel);
        dificultyBox.add(dificultyComboBox);
        dificultyBox.add(areaLabel);
        dificultyBox.add(areaComboBox);
        dificultyBox.add(startGame);

        add(dificultyBox, new GridBagConstraints());
    }

    @Override
    public void update(Observable t, Object o) {

        if(!game.getBuffer().equals(""))
            JOptionPane.showMessageDialog(null, game.getBuffer(), "InfoBox: " + "MiniRogue", JOptionPane.INFORMATION_MESSAGE);

        setVisible(game.getState() instanceof AwaitBeginning);
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            game.startGame( (areaComboBox.getSelectedIndex()+1), (dificultyComboBox.getSelectedIndex()+1));

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GraphicalPanel.getIntroImage(), 0, 50, 645, 900, this);
    }
}
