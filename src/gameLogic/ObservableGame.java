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
    private void loadGameInstance(){
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
    private void saveGameInstance(){
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
        loadGameInstance();

        setChanged();
        notifyObservers();
    }
    public void saveGame(){
        saveGameInstance();

        setChanged();
        notifyObservers();
    }

    //Game Info
    public GameData getGameData(){return game.getGameData();}
    public int getSpellsSize(){
        return game.getDiceSize();
    }
    public String getSpell(int index){
        return game.getSpell(index);
    }
    public String getBuffer(){
        return game.getBuffer();
    }
    public void clearBuffer() { game.clearBuffer();}
    public int getArea() {
        return game.getArea();
    }
    public final Player getPlayer(){
        return game.getPlayer();
    }
    public final ImageIcon getCardImage(int index){
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
    public int getMonsterHp(){
       return game.getMonsterHp();
    }
    public int getCardStackSize(){
        return game.getCardStackSize();
    }
    public boolean isUsed(int index){
        return  game.cardIsUsed(index);
    }

    //States Handling
    public  void newGame(){
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
    public void chooseCard(int pos){
        game.chooseCard(pos);

        setChanged();
        notifyObservers();


        if(pos == 0 || pos == 3){
            game.getGameData().getCard(pos + 1).turnCard();
            game.getGameData().getCard(pos + 2).turnCard();
        }else if( pos == 1 ) {
            game.getGameData().getCard(pos + 1).useCard();
            game.getGameData().getCard(pos + 2).turnCard();
        }else if (pos == 2 ) {
            game.getGameData().getCard(pos - 1).useCard();
            game.getGameData().getCard(pos + 1).turnCard();
        }else if (pos == 4) {
            game.getGameData().getCard(pos + 1).useCard();
        }else if(pos == 5) {
            game.getGameData().getCard(pos - 1).useCard();
        }

        game.setState(getState().checkNewArea());
        game.setState(getState().checkHp());

        setChanged();
        notifyObservers();
    }
    public void chooseOption(int option){
        game.chooseOption(option);

        setChanged();
        notifyObservers();

        game.setState(getState().checkNewArea());
        game.setState(getState().checkHp());

        setChanged();
        notifyObservers();
    }
    public void rerrollDice(int dice){
        game.rerollDiceOption(dice);

        setChanged();
        notifyObservers();

        game.setState(getState().checkNewArea());
        game.setState(getState().checkHp());

        setChanged();
        notifyObservers();
    }
    public void featOption(boolean b, int dice){
        game.featOption(b,dice);

        setChanged();
        notifyObservers();

        game.setState(getState().checkNewArea());
        game.setState(getState().checkHp());

        setChanged();
        notifyObservers();
    }
    public void spellOption(int spell){
        game.spellOption(true, spell);

        setChanged();
        notifyObservers();

        game.setState(getState().checkNewArea());
        game.setState(getState().checkHp());

        setChanged();
        notifyObservers();
    }
    public void skip(){
        game.skip();

        setChanged();
        notifyObservers();

        game.setState(getState().checkNewArea());
        game.setState(getState().checkHp());

        setChanged();
        notifyObservers();
    }
}

