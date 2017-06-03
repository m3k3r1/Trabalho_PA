package gameInterface.graphicalInterface;

import gameLogic.Constants;
import gameLogic.ObservableGame;
import gameLogic.states.*;
import gameLogic.states.combatStates.AwaitDiceReroll;
import gameLogic.states.combatStates.AwaitFeatDecision;
import gameLogic.states.combatStates.AwaitSpellDecision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class GraphicalUi extends JFrame implements Observer, Constants {
    private GraphicalPanel gamePanel;
    ObservableGame observableGame;


    public GraphicalUi(ObservableGame game) {
        super("MiniRogue");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        observableGame = game;
        game.addObserver(this);
        setLayout(new BorderLayout());

        setContentPane(new JLabel(new ImageIcon(GraphicalPanel.getGameBackground().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH))));

        setLayout(new FlowLayout());

        addMenuBar();
        Container container = getContentPane();



        gamePanel = new GraphicalPanel(observableGame);
        container.add(gamePanel);

        setBounds(0,0,screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    //TopBar
    public void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        addGameMenu(menuBar);
        addHelpMenu(menuBar);
    }
    private void addGameMenu(JMenuBar menuBar) {
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem loadGame = new JMenuItem("Load");
        loadGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        JMenuItem saveGame = new JMenuItem("Save");
        saveGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem exitGame = new JMenuItem("Exit");
        exitGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        gameMenu.add(newGame);
        gameMenu.add(loadGame);
        gameMenu.add(saveGame);
        gameMenu.addSeparator();

        gameMenu.add(exitGame);

        menuBar.add(gameMenu);

        newGame.addActionListener(new NewGameBarListener());
        loadGame.addActionListener(new LoadGameBarListener());
        saveGame.addActionListener(new SaveGameBarListener());
        exitGame.addActionListener(new ExitGameBarListener());

    }
    private void addHelpMenu(JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Help");

        JMenuItem manual = new JMenuItem("Manual");
        manual.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK)
        );

        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK)
        );

        helpMenu.add(manual);
        helpMenu.add(about);

        menuBar.add(helpMenu);

        manual.addActionListener(new ManualHelpBarListener());
        manual.addActionListener(new AboutHelpBarListener());
    }

    //Listeners
    class NewGameBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            observableGame.newGame();
        }

    }
    class LoadGameBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    class SaveGameBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    class ExitGameBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    class ManualHelpBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    class AboutHelpBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Player -> H : " + observableGame.getPlayer().getHp() + "| A : " + observableGame.getPlayer().getArmor() + "| F : " + observableGame.getPlayer().getFood() + "| G : " + observableGame.getPlayer().getGold() + "| Xp : " + observableGame.getPlayer().getXp());
        repaint();
    }
}
