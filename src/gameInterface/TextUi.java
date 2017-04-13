package gameInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import gameLogic.Game;
import gameLogic.Constants;

public class TextUi implements Constants {
	
	Game game;
	
	public TextUi(){
		game = null;
	}
	
	public void run(Game g){
		game = g;
		
		menu();
	}
	
	public void startGameInterface(){
		int d = difficultyMenu();
		
		game.setDificulty(d);
		
		showCardStack();
	}
	
	public void loadGameInterface(String savefile){
		
	}
	
	public void closeGame(){

	}
	

	void showCardStack(){
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
				case 2: loadGameInterface(LOAD_FILE);
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

}
