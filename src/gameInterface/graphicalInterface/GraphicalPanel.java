package gameInterface.graphicalInterface;

import gameInterface.graphicalInterface.statesPanels.AwaitCardSelectionPanel;
import gameInterface.graphicalInterface.statesPanels.AwaitOptionSelectionPanel;
import gameInterface.graphicalInterface.statesPanels.AwaitTradingPanel;
import gameInterface.graphicalInterface.statesPanels.combatStatesPanels.AwaitDiceRerrollPanel;
import gameLogic.ObservableGame;
import gameInterface.graphicalInterface.statesPanels.AwaitBeginningPanel;
import gameLogic.Constants;
import sun.util.BuddhistCalendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class GraphicalPanel extends JPanel implements Observer,  Constants {

    static private BufferedImage introImage = null;
    static private BufferedImage gameBackground = null;
    static private BufferedImage backCard = null;
    static private BufferedImage characterStats = null;
    static private BufferedImage dungeonCard = null;
    static private BufferedImage treasureCard = null;
    static private BufferedImage eventCard = null;
    static private BufferedImage merchantCard = null;
    static private BufferedImage restingCard = null;
    static private BufferedImage monsterCard = null;
    static private BufferedImage trapCard = null;
    static private BufferedImage bossCard = null;
    static private BufferedImage token = null;

    static {
        try {
            introImage = ImageIO.read(Resources.getResourceFile(INTRO_IMAGE));
            gameBackground = ImageIO.read(Resources.getResourceFile(GAME_BACKGROUND));
            characterStats = ImageIO.read(Resources.getResourceFile(CHARACTER_STATS));
            backCard = ImageIO.read(Resources.getResourceFile(BACK_CARD));
            dungeonCard = ImageIO.read(Resources.getResourceFile(DUNGEON_STATS));
            treasureCard = ImageIO.read(Resources.getResourceFile(TREASURE_CARD));
            eventCard = ImageIO.read(Resources.getResourceFile(EVENT_CARD));
            merchantCard = ImageIO.read(Resources.getResourceFile(MERCHANT_CARD));
            restingCard = ImageIO.read(Resources.getResourceFile(RESTING_CARD));
            monsterCard = ImageIO.read(Resources.getResourceFile(MONSTER_CARD));
            trapCard = ImageIO.read(Resources.getResourceFile(TRAP_CARD));
            bossCard = ImageIO.read(Resources.getResourceFile(BOSS_CARD));
            token = ImageIO.read(Resources.getResourceFile(MORTY_TOKEN));
        } catch (IOException e) {
            System.out.println("Error loading images ");
        }
    }

    public static BufferedImage getIntroImage(){
        return introImage;
    }
    public static BufferedImage getGameBackground() {
        return gameBackground;
    }
    public static BufferedImage getBackCard(){ return backCard; }
    public static BufferedImage getCharacterStats() {
        return characterStats;
    }
    public static BufferedImage getDungeonCard() {
        return dungeonCard;
    }
    public static BufferedImage getTreasureCard() {
        return treasureCard;
    }
    public static BufferedImage getEventCard() {
        return eventCard;
    }
    public static BufferedImage getMerchantCard() {
        return merchantCard;
    }
    public static BufferedImage getRestingCard() {
        return restingCard;
    }
    public static BufferedImage getMonsterCard() {
        return monsterCard;
    }
    public static BufferedImage getTrapCard() {
        return trapCard;
    }
    public static BufferedImage getBossCard() {
        return bossCard;
    }
    public static BufferedImage getToken(){ return token; }

    UserStatsPanel userStatsPanel;
    DungeonPanel dungeonPanel;

    AwaitBeginningPanel awaitBeginningPanel;
    AwaitCardSelectionPanel awaitCardSelectionPanel;
    AwaitOptionSelectionPanel awaitOptionSelectionPanel;
    AwaitTradingPanel awaitTradingPanel;

    AwaitDiceRerrollPanel awaitDiceRerrollPanel;

    ObservableGame observableGame;

    static private BufferedImage stats;

    public static BufferedImage getStats() {
        return stats;
    }

    static {
        try {
            stats = ImageIO.read(Resources.getResourceFile("img/graphicalElements/cards/stats_small.png"));
        } catch (IOException e) {
            System.out.println("Error loading images ");
        }
    }

   public GraphicalPanel(ObservableGame game){
       observableGame = game;
       this.observableGame.addObserver(this);

       setupComponents();
       setupLayout();

       update(game, null);
   }
   private void setupComponents(){
       setBackground(new Color(0,0,0,1));

       userStatsPanel = new UserStatsPanel(observableGame);
       dungeonPanel = new DungeonPanel(observableGame);

       awaitBeginningPanel = new AwaitBeginningPanel(observableGame);
       awaitCardSelectionPanel = new AwaitCardSelectionPanel(observableGame);
       awaitOptionSelectionPanel = new AwaitOptionSelectionPanel(observableGame);
       awaitTradingPanel = new AwaitTradingPanel(observableGame);

       awaitDiceRerrollPanel = new AwaitDiceRerrollPanel(observableGame);
   }
   private void setupLayout(){
       JPanel main = new JPanel();
       main.add(awaitBeginningPanel);
       main.add(awaitCardSelectionPanel);
       main.add(awaitOptionSelectionPanel);
       main.add(awaitTradingPanel);

       main.add(awaitDiceRerrollPanel);

       main.setBackground(new Color(0,0,0,1));

       JPanel statsPanel = new JPanel();
       statsPanel.add(userStatsPanel);
       statsPanel.setBackground(new Color(0,0,0,1));

       JPanel dungPanel = new JPanel();
       dungPanel.add(dungeonPanel);
       dungPanel.setBackground(new Color(0,0,0,1));

       setLayout(new BorderLayout());
       add(main, BorderLayout.CENTER);
       add(statsPanel, BorderLayout.SOUTH);
       add(dungPanel, BorderLayout.WEST);
   }

   @Override
   public void update(Observable t, Object o) {

   }
}
