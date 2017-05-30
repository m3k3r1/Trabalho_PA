package gameLogic;

import java.util.Observable;

import gameLogic.states.AwaitBeginning;
import gameLogic.states.RogueState;

public class ObservableGame extends Observable{
    Game game;

    public ObservableGame(Game g){
        game = g;

    }

    public RogueState getState(){
        return game.getState();
    }

    //
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

}

