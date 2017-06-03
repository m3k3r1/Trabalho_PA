package gameLogic;

import java.awt.*;
import java.util.Observable;
import gameLogic.states.RogueState;

import javax.swing.*;

public class ObservableGame extends Observable{
    Game game;

    public ObservableGame(Game g){
        game = g;

    }

    public RogueState getState(){
        return game.getState();
    }

    //Game Info
    public final Player getPlayer(){
        return game.getPlayer();
    }
    public final ImageIcon getCardImage(int index){
        if(game.getCard(index) == null)
            game.initialcizeCards();
        return game.getCard(index);
    }
    public Boolean isTurned(int index){
       return game.isTurned(index);
    }

    //States Handling
    public  void newGame(){
        game.newGame();

        setChanged();
        notifyObservers();
    }
    public void startGame(int area, int difLevel){
        game.setStartingArea(area);
        game.setDificulty(difLevel);
        game.startGame();

        setChanged();
        notifyObservers();
    }
    public void chooseCard(int option){
        game.chooseCard(option);

        setChanged();
        notifyObservers();
    }
    public void chooseOption(int option){
        game.chooseOption(option);

        setChanged();
        notifyObservers();
    }

}

