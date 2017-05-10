package gameInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import gameLogic.Constants;
import gameLogic.Game;
import gameLogic.cards.*;
import gameLogic.states.*;


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
		
		
		if((card & 1) == 0){
			System.out.println("Card > " + game.showCard(card));
		}
		else{
			System.out.println("Card > " + game.showCard(card));
			System.out.println("Card > " + game.showCard(card + 1));
		}
		
		System.out.print("Turn Card > ");
		card = sc.nextInt();
		game.chooseCard(card);

		
	}
	public void optionSelectionUi(){
		
	}
	public void tradingUi(){
		
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
