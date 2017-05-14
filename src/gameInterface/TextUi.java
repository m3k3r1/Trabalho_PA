package gameInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		+ "| F : " + game.getFood() + "| G : " + game.getGold() + "| Xp : " + game.getXp());
			
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
		int option = 0;
		
		System.out.println("[COMBAT]  - MONSTER HP : " + game.getMonsterHp());
		System.out.println("Player -> H : " +  game.getHp() + "| A : " + game.getArmor()
		+ "| F : " + game.getFood() + "| G : " + game.getGold() + "| Xp : " + game.getXp());
		
		for(int i = 0; i < game.getDiceSize(); i++)
			System.out.println("Dice " + i + " : " + game.getDiceValue(i));
		System.out.print(" Do you wanna reroll any dice ? (1/0)" );
		option = sc.nextInt();
		
		if(option == 1){
			System.out.print("Dice to reroll > ");
			game.rerollDiceOption(sc.nextInt());
		}else{
			game.skip();
		}
		
	}
	
	public void featOptionUi(){
		int dice = -1;
		int option;
		boolean o = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.print(" Do you wanna use feat ? (1/0) " );
		option = sc.nextInt();
		
		if(option == 1){
			for(int i = 0; i < game.getDiceSize(); i++)
				System.out.println("Dice " + i + " : " + game.getDiceValue(i));
			System.out.print("Dice to reroll > ");
			dice = sc.nextInt();
			o = true;
			game.featOption(o, dice);
		}else{
			game.skip();
		}

		
	}
	
	public void spellOptionUi(){
		Scanner sc = new Scanner(System.in);
		int option;
		boolean o = false;
		
		System.out.print(" Do you wanna use spell ? (1/0) " );
		option = sc.nextInt();
		if(option == 1){
			for(int i = 0; i < 2; i++){
				switch(game.getSpellValue(i)){
					case 1: System.out.println("[" + i + "]" + "Fireball");
							o = true;
							break;
					case 2: System.out.println("[" + i + "]" + "Ice");
							o = true;
							break;
					case 3: System.out.println("[" + i + "]" + "Poison");
							o = true;
							break;
					case 4: System.out.println("[" + i + "]" + "Healing");
							o = true;
							break;
					default:System.out.println("[" + i + "]" + "Empty Slot");
							break;
				}		
			}
			
			game.spellOption(o, sc.nextInt());	
		}
		else{
			game.skip();
		}
		
		
	}
	
	
	
	
	
	
	
	private void handleSaveGameToFileOption() throws IOException
	{
		String fileName;

		System.out.print("File name: ");
		fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

		if(fileName==null)
				return;

		if(fileName.length() < 1)
				return;

		saveGameToFile(fileName);
	}

	private void saveGameToFile(String fileName) throws IOException
	{
		ObjectOutputStream oout = null;

		try{
			//Create an object output stream connected to a file named fileName.
			oout = new ObjectOutputStream(new FileOutputStream(fileName));

			//Write/serialize the game object to the open object output stream.
			oout.writeObject(game);
		}finally{
				//If the object output stream was successfuly created, close it.
				if(oout != null)
						oout.close();
		}
	}

	private Game retrieveGameFromFile(String fileName) throws IOException, ClassNotFoundException
	{
		ObjectInputStream oin = null;

		try{
				//Create an object input stream connected to a file named fileName.
				oin = new ObjectInputStream(new FileInputStream(fileName));

				//Retrieve a serialized instance of ThreeInRowGame from the object input stream and return a reference to it.
				return (Game)oin.readObject();
		}finally{
				//If the object input stream was successfuly created, close it.
				if(oin != null)
						oin.close();
		}
}
	
	
	
	
	
	private int menu(){
	    int menu_op = 0;
	    Scanner sc = new Scanner(System.in);

	    do{
	    	try{
	    		
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
	    	}	
	    }while (menu_op < 0 || menu_op > 4);
	    	

	   
	    
	    sc.close();
	    
	    return menu_op;
	}
	
	private void printTxt(String filename) throws IOException {
		
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

*/
