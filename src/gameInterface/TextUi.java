package gameInterface;

import java.io.IOException;
import java.util.Scanner;

import gameLogic.Constants;
import gameLogic.Game;
import gameLogic.states.*;
import gameLogic.states.combatStates.*;


public class TextUi implements Constants {
	
	Game game;
	
	public TextUi(){
		game = null;
	}
	
	public void run(Game g) throws IOException{
		
		game = g;
		
		while(true){
			RogueState state = game.getState();
			
			if(state instanceof AwaitBeginning )
				beginningUi();
			else if(state instanceof AwaitCardSelection)
				cardSelectionUi();
			else if(state instanceof AwaitOptionSelection)
				optionSelectionUi();
			else if(state instanceof AwaitTrading)
				tradingUi();
			else if(state instanceof AwaitDiceReroll)
				diceRerollOptionUi();
			else if (state instanceof AwaitFeatDecision)
				featOptionUi();
			else if (state instanceof AwaitSpellDecision)
				spellOptionUi();
		}		
	}
	
	public void beginningUi(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Dificuldade: ");
		game.setDificulty(sc.nextInt());
		System.out.print("Area: ");
		game.setStartingArea(sc.nextInt());		
		game.startGame();
	}
	
	public void cardSelectionUi(){
		Scanner sc = new Scanner(System.in);
		int card = 0;
		
		System.out.println("Player -> H : " +  game.getHp() + "| A : " + game.getArmor()
		+ "| F : " + game.getFood() + "| G : " + game.getGold());
			
		for(int i = 0; i < 6; i++)
			System.out.println("Card " + i + " : " + game.showCard(i));
		
		
		System.out.print("Turn Card > ");
		card = sc.nextInt();
		game.chooseCard(card);
	
	}
	public void optionSelectionUi(){
		System.out.println("[OPTION SELECTION]");
		Scanner sc = new Scanner(System.in);
		System.out.print("Opção > ");
		game.chooseOption(sc.nextInt());
	}
	public void tradingUi(){
		System.out.println("[MERCHANT SELECTION]");
		Scanner sc = new Scanner(System.in);
		System.out.print("Opção > ");
		game.chooseOption(sc.nextInt());
	}
	
	public void diceRerollOptionUi(){
		Scanner sc = new Scanner(System.in);
		System.out.println("[COMBAT]");
		System.out.print(" Do you wanna reroll any dice ? \n" );
		for(int i = 0; i < game.getDiceSize(); i++)
			System.out.println("Dice " + i + " : " + game.getDiceValue(i));
		System.out.print("Dice to reroll > ");
		game.rerollDiceOption(sc.nextInt());
	}
	
	public void featOptionUi(){
		int dice = -1;
		int option;
		boolean o = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.print(" Do you wanna use feat ? (yes/no) " );
		option = sc.nextInt();
		
		if(option == 1){
			for(int i = 0; i < game.getDiceSize(); i++)
				System.out.println("Dice " + i + " : " + game.getDiceValue(i));
			System.out.print("Dice to reroll > ");
			dice = sc.nextInt();
			o = true;
		}

		game.featOption(o, dice);
	}
	
	public void spellOptionUi(){
		Scanner sc = new Scanner(System.in);
		String option;
		
		System.out.print(" Do you wanna use spell ? (yes/no) " );
		option = sc.nextLine();
	}
	
	// METHODS TO HANDLE GAME SAVING AND LOADING
	// SAVE GAME
	// OPEN BYTE FILE 
	public static ObjectOutputStream openFile2ObjectWrite(String name) throws IOException{
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name));
			
			return out;
		} catch(IOException e) {
			System.out.println("Error opening file " + name);
			throw e;
		}
	}
	
	// WRITE TO BYTE FILE AND CLOSE
	public void writeGame2File (String name, Player p) throws IOException {
		if(game != null) {
			ObjectOutputStream out = null;
			try {
				out = openFile2ObjectWrite(name);
				out.writeObject(game);
			} catch (IOException e) {
				System.out.println("Error writing to file" + name);
				throw e;
			} finally {
				if( out != null) out.close();
			}
		}
	}
	
	// LOAD GAME
	// OPEN BYTE FILE
	public ObjectInputStream openFile2ObjectRead (String name) throws IOException{
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(name));
			
			return in;
		} catch(IOException e) {
			System.out.println("Error opening file " + name);
			throw e;
		}
	}
	
	// LOAD GAME OBJECTS FROM BYTE FILE 
	public void readGameFile2Game (String name) throws Exception {
		ObjectInputStream in = null;
		Game g = null;
		try {
			in = openFile2ObjectRead(name);
			
			g = (Game) in.readObject();
		} catch (EOFException e){
			System.out.println("End of file " + name);
		} catch (ClassNotFoundException e) {
			System.out.println("Error reconstruting object");
			throw e;
		} finally {
			if(in != null){ 
				in.close();
				game = g;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	// GAME BEGINS HERE
	public void startGameInterface(){
		Scanner sc = new Scanner(System.in);
		int d = difficultyMenu();
		
		game.setDificulty(d);
		game.setStartingArea(1);
		
		do{
			int card = 0;
			
			showPlayerStats();
			System.out.print("\nCarta > " + game.showCard(card) );
			card = sc.nextInt();
			game.chooseCard(card);
		}while(true);

		
	}
	
	public void loadPreviousGameInterface(String savefile){
		
	}
	
	public void closeGame(){

	}
	
	public void showPlayerStats(){
		System.out.print("Player -> H : " +  game.getHp() + "| A : " + game.getArmor()
		+ "| F : " + game.getFood() + "| G : " + game.getGold());
	}
	
	private void showCardStack(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try{
			//clearScreen();
			printTxt(LOGO_TXT);
			printTxt(CARD_STACK_FILE);
			sc.nextLine();
		}
		catch(IOException e){
	        System.out.println (e.toString());
	        System.out.println("Could not find menu elements");
		}
	}
	
	private void chooseCard(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int card = -1;
	}
	
	int difficultyMenu(){
		int menu_op = 0;
		@SuppressWarnings("resource")
	    Scanner sc = new Scanner(System.in);

	    	do{
	    		try{
	    			//clearScreen();
	    			printTxt(LOGO_TXT);
	    			printTxt(DIF_MENU_TXT);
	    			
	    			System.out.print("Opção >> ");
	    			menu_op = sc.nextInt();
				
	    			if(menu_op < 0 || menu_op > 4){
	    				System.out.print("\n\tOpção Inválida :: Prima ENTER para tentar outra vez");
	    				sc.nextLine();
	    			}
	    		}
	    		catch(IOException e){
	    			System.out.println (e.toString());
	    			System.out.println("Could not find menu elements");
	    		}
	    	}while (menu_op < 0 || menu_op > 5);
	  
		
		return menu_op;
	}
	void aboutGame(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		try{
			//clearScreen();
			printTxt(ABOUT_TXT);
			sc.nextLine();
		}
		catch(IOException e){
	        System.out.println (e.toString());
	        System.out.println("Could not find game elements");
		}
	}
	void printTxt(String filename) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line = in.readLine();
		System.out.print("\n\n\n");
		
		while(line != null)
		{
		  System.out.println(line);
		  line = in.readLine();
		}
		in.close(); 
	}
	int menu(){
	    int menu_op = 0;
	    Scanner sc = new Scanner(System.in);

	    do{
	    	do{
	    		try{
	    			//clearScreen();
	    			printTxt(LOGO_TXT);
	    			printTxt(MENU_TXT);
				
	    			System.out.print("Opção >> ");
	    			menu_op = sc.nextInt();
				
	    			if(menu_op < 0 || menu_op > 4){
	    				System.out.print("\n\tOpção Inválida :: Prima ENTER para tentar outra vez");
	    				sc.nextLine();
	    			}
	    		}
	    		catch(IOException e){
	    			System.out.println (e.toString());
	    			System.out.println("Could not find menu elements");
	    		}
	    	}while (menu_op < 0 || menu_op > 4);
	    	
			switch (menu_op){
				case 1: startGameInterface();
						break;
				case 2: loadPreviousGameInterface(LOAD_FILE);
						break;
				case 3: aboutGame();
						break;
				case 4: closeGame();
						break;
			}
	    }while(menu_op != 4);
	    
	    sc.close();
	    
	    return menu_op;
	}

*/}
