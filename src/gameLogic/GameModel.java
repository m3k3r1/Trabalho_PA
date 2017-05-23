package gameLogic;

import java.io.Serializable;
import gameLogic.states.RogueState;
import gameLogic.states.AwaitBeginning;


public class GameModel implements Serializable{
    private GameData data;
    private RogueState state;

    public GameModel(){

    }
}
