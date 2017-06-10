package gameLogic;

import java.awt.*;
import java.io.*;
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

    //Save/Load Handling
    private void loadGameInstace(){
        Game aux = game ;
        try {

            FileInputStream fi = new FileInputStream("game.bin");
            ObjectInputStream oi = new ObjectInputStream(fi);
            aux = ((Game) oi.readObject());
            oi.close();
            game = aux;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error on reading file "+ e);
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("File game.bin: " + e);
        }

    }
    private void saveGameInstace(){
        try{
            FileOutputStream fo = new FileOutputStream("game.bin");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(game);
            oo.close();
        }catch(IOException e){
            System.out.println("Error on saving in object file " + e);
        }
    }
    public void loadGame(){
        loadGameInstace();

        setChanged();
        notifyObservers();
    }
    public void saveGame(){
        saveGameInstace();

        setChanged();
        notifyObservers();
    }

    //Game Info
    public String getBuffer(){
        return game.getBuffer();
    }
    public int getArea() {
        return game.getArea();
    }
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
    public int getDiceStackSize(){
        return game.getDiceSize();
    }
    public int getDiceValue(int index){
        return game.getDiceValue(index);
    }

    //States Handling
    public  void newGame(){
        //game.newGame();
        game = new Game(new Player());
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

