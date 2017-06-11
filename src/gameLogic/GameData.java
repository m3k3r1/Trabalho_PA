package gameLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import gameInterface.graphicalInterface.GraphicalPanel;
import gameLogic.cards.*;

public class GameData implements Constants, Serializable {
	
	private int level;
	private int area;

	private Player user;
	private ArrayList<Card> cardStack;

	private ArrayList<Integer> diceStack;
    private ArrayList<Integer> extraDiceDamage;

	private String buffer;
	
	public GameData(Player p){
		user = p;
		level = 1;
		area = 1;
	
		diceStack = new ArrayList<Integer>();
		diceStack.add(0);
		diceStack.add(0);

		cardStack = new ArrayList<Card>();
        extraDiceDamage = new ArrayList<Integer>();

		generateDiceValues();
		clearOutputBuffer();
	}

	//Game
    public Player getPlayer(){
        return user;
    }
    public boolean hasHp(){
        if(user.getHp() > 0)
            return true;
        return false;
    }
    public void setDificulty(int d){
        switch(d){
            case 1 : user.addArmor(CASUAL_ARMOR);
                user.addHp(CASUAL_HP);
                user.addGold(CASUAL_GOLD);
                user.addFood(CASUAL_FOOD);
                break;
            case 2 : user.addHp(NORMAL_HP);
                user.addGold(NORMAL_GOLD);
                user.addFood(NORMAL_FOOD);
                break;
            case 3 : user.addHp(HARD_HP);
                user.addGold(HARD_GOLD);
                user.addFood(HARD_FOOD);
                break;
            case 4 : user.addHp(IMPOSSIBLE_HP);
                user.addGold(IMPOSSIBLE_GOLD);
                user.addFood(IMPOSSIBLE_FOOD);
                break;
        }
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int l){
        level = l;
    }
    public int getArea(){
        return area;
    }
    public void setArea(int a){
        area = a;
    }
    public boolean checkBossArea(){
        if(area == 2 || area == 4 || area == 7 || area == 10 || area == 14){
            return true;
        }


        return false;
    }
    public void clearCardStack(){
        cardStack.clear();
    }

    //Player
	public void dimFood(){
		if(user.getFood() <= 0)
			user.addHp(-2);
		else
			user.setFood(user.getFood() - 1);
	}
    public int getHp(){
        return user.getHp();
    }
    public int getArmor(){
        return user.getArmor();
    }
    public int getGold(){
        return user.getGold();
    }
    public int getFood(){
        return user.getFood();
    }
    public void setHp(int h){
        user.setHp(h);
    }
    public void addHp(int h){
        user.addHp(h);
    }
    public int getXp(){
        return user.getXp();
    }
    public void addReward(int card){
        user.addXp(cardStack.get(card).getReward());
    }
    public int getSpellValue(int p){
        return user.getSpellValue(p);
    }
    public void removeSpell(){
        user.removeSpell();
    }

    //Card
    public void initializeCardStack(){

        cardStack.add(new Event(GraphicalPanel.getEventCard()));
        cardStack.add(new Merchant(GraphicalPanel.getMerchantCard()));
        cardStack.add(new Monster(GraphicalPanel.getMonsterCard(),level));
        cardStack.add(new Resting(GraphicalPanel.getRestingCard()));
        cardStack.add(new Trap(GraphicalPanel.getTrapCard()));
        cardStack.add(new Treasure(GraphicalPanel.getTreasureCard()));

        Collections.shuffle(cardStack);

        if(checkBossArea()) {
            cardStack.add(new Boss(GraphicalPanel.getBossCard(), level));
            cardStack.get(6).turnCard();
        }

        cardStack.get(0).turnCard();
    }
    public final Card getCard(int pos){
        if(pos < 7){
            return cardStack.get(pos);
        }
        return null;
    }
    public String showCard(int c){
        return cardStack.get(c).toString();
    }
    public int nCardsTurned() {
        int counter = 0;
        for (Card c : cardStack){
            if (c.isUsed())
                counter++;
        }
        return counter;
    }
    public int cardsTurned(){
        int counter = 0;

        for(int i = 0; i < cardStack.size(); i++){
            if(cardStack.get(i).isTurned())
                counter++;
        }

        return counter;
    }
    public int getCardStackSize(){
        return cardStack.size();
    }
    public void createEventMonster(){
        cardStack.add(new Monster(GraphicalPanel.getMonsterCard(),level));
    }
    public void createBossMonster(){
        cardStack.add(new Boss(GraphicalPanel.getBossCard(), level));
    }
    public int getMonsterHp(){
        for(Card  a : cardStack)
            if((a.isMonster() || a.isBoss()) && a.getHp() > 0 )
                return a.getHp();
        return 0;
    }
    public boolean hasHp(int c){
        if(cardStack.get(c).getHp() >= 0)
            return true;
        return false;
    }
    public void attackUser(int card){
        user.addHp(-cardStack.get(card).getDamage());
        outputBuffer("You attack with " + calculateDiceSum() + " DAMAGE " +
                "Monster ATTACKED YOU : " + cardStack.get(card).getDamage() + " DAMAGE");

    }
    public void delBoss(){
        cardStack.remove(6);
    }

    //Dice
    public boolean diceStackhas6(){
        int nDices = 1;

        if(user.getXp() >= 12)
            nDices = 2;
        else if(user.getXp() >= 18)
            nDices = 3;

        for(int i = 0; i < nDices; i++){
            if(diceStack.get(i) == 6)
                return true;
        }
        return false;
    }
    public int throwDice(){
        return (int)(Math. random() * 6 + 1);
    }
    public int getDiceSize(){
        return diceStack.size();
    }
    public int getDiceValue(int p){
        return diceStack.get(p);
    }
    public void generateDiceValues(){
        diceStack.clear();
        int nDices = 1;

        if(user.getXp() >= 12)
            nDices = 2;
        else if(user.getXp() >= 18)
            nDices = 3;

        for(int i = 0; i < nDices; i++){
            diceStack.add(throwDice());
            extraDiceDamage.add(0);
        }

    }
    public void rerollDice(int dice){
        diceStack.set(dice, throwDice());
    }
    public int calculateDiceSum(){
        int sum = 0;

        checkDiceValues();

        for(int i = 0; i < getDiceSize(); i++){
            sum += diceStack.get(i) + extraDiceDamage.get(i);
        }
        return sum;
    }
    private void checkDiceValues() {
        for (int i = 0; i < getDiceSize(); i++) {
            if (diceStack.get(i) == 1)
                diceStack.set(i, 0);
            extraDiceDamage.set(i, 0);
        }
    }
    public void addExtraDamage(int diceIndex){
        int sum = extraDiceDamage.get(diceIndex) + 6;
        extraDiceDamage.set(diceIndex, sum);
    }
    public void refreshDices(){
        int nDices = 1;

        if(user.getXp() >= 12)
            nDices = 2;
        else if(user.getXp() >= 18)
            nDices = 3;

        for(int i = 0; i < nDices; i++){
            diceStack.set(i, throwDice());
        }
    }

    //Useful Methods
    public void outputBuffer(String g){
        buffer = g;
    }
    public void clearOutputBuffer(){
        buffer = "";
    }
    public String getBuffer(){
        return buffer;
    }
}
