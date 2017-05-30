package gameInterface.graphicalInterface;

import gameInterface.graphicalInterface.statesPanels.AwaitCardSelectionPanel;
import gameLogic.ObservableGame;
import gameInterface.graphicalInterface.statesPanels.AwaitBeginningPanel;
import gameLogic.Constants;
import javax.swing.*;
import java.awt.Image;
import  java.lang.String;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphicalPanel extends JPanel implements Observer,  Constants {

    AwaitBeginningPanel awaitBeginningPanel;
    AwaitCardSelectionPanel awaitCardSelectionPanel;

    ObservableGame observableGame;

   public GraphicalPanel(ObservableGame game){
       observableGame = game;

       setupComponents();

       update(game, null);
   }

   void setupComponents(){
       awaitBeginningPanel = new AwaitBeginningPanel(observableGame);
       awaitCardSelectionPanel = new AwaitCardSelectionPanel(observableGame);

       JPanel test = new JPanel();
       test.add(awaitBeginningPanel);
       test.add(awaitCardSelectionPanel);
       add(test);

       setLayout(new BorderLayout());
       add(test);

       test.setBackground(Color.CYAN);
   }


   @Override
   public void update(Observable t, Object o) {

   }
}
