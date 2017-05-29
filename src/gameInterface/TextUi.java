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
	static int cardInGame = 0;
	static  int lim = 0;

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
		startMenu();
		game.setDificulty(difficultyMenu());
        game.setStartingArea(areaMenu());
		game.startGame();
	}
	public void cardSelectionUi(){
		Scanner sc = new Scanner(System.in);
		int card = 0;

		if ( ((cardInGame + 1) & 1) == 0 ) {
			lim+=2;
		} else {
			lim++;
		}

		showCard(lim);

		if(cardInGame < game.getCardStackSize()){
            if ( ((cardInGame + 1) & 1) == 0 )
                lim--;


            if ( ((cardInGame + 1) & 1) == 0 ) {
                System.out.print(" Card [ " + cardInGame  + " | " + (cardInGame + 1) +  " ]  > ");
                card = sc.nextInt();
                cardInGame++;
                game.chooseCard(card);
            } else {
                game.chooseCard(cardInGame);
                cardInGame++;
            }
        }
	}
	public void optionSelectionUi(){
		game.chooseOption(showRestingOptions());
	}
	public void tradingUi(){
		game.chooseOption(showTradingOptions());
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

    //Beginning UI
	private int startMenu(){
	    int menu_op = 0;
	    Scanner sc = new Scanner(System.in);


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



	    return menu_op;
	}
	private int difficultyMenu(){
		int menu_op = 0;

		Scanner sc = new Scanner(System.in);


			try{

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



		return menu_op;
	}
	private int areaMenu(){
        int menu_op = 0;
        Scanner sc = new Scanner(System.in);

        do{
            try{

                printTxt(LOGO_TXT);
                printTxt(AREA_MENU_TXT);

                System.out.print("Opção >> ");
                menu_op = sc.nextInt();

                if(menu_op < 0 || menu_op > 14){
                    System.out.print("\n\tOpção Inválida :: Prima ENTER para tentar outra vez");
                    sc.nextLine();
                }
            }
            catch(IOException e){
                System.out.println (e.toString());
                System.out.println("Could not find menu elements");
            }
        }while (menu_op < 0 || menu_op > 14);

        return menu_op;
    }

    //Card Selection UI
    private void showCard(int lim){
	    try {
            printTxt(LOGO_TXT);
        }
        catch (IOException e){
            System.out.println (e.toString());
        }
        System.out.println();
        System.out.println(gameStats());
        System.out.println(playerStats());
        System.out.println();

        for(int i = 0; i < game.getCardStackSize(); i++)
            if(i < lim)
                System.out.println("                           [Card " + i + "] -> " + game.showCard(i));
            else
                System.out.println("                           [Card " + i + "] ->  --------- " );
	}

    //Option Selection Ui
    private int showRestingOptions(){
        int menu_op = 0;
        Scanner sc = new Scanner(System.in);

        do{
            try{

                printTxt(LOGO_TXT);
                System.out.println(playerStats());
                printTxt(RESTING_TXT);

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


        return menu_op;

    }

    //Trading UI
    private int showTradingOptions(){
        int menu_op = 0;
        Scanner sc = new Scanner(System.in);


            try{

                printTxt(LOGO_TXT);
                System.out.println(playerStats());
                printTxt(MERCHANT_TXT);

                System.out.print("Opção >> ");
                menu_op = sc.nextInt();
            }
            catch(IOException e){
                System.out.println (e.toString());
            }


        return menu_op;
    }

	//Saves Handling
    private void handleSaveGameToFileOption() throws IOException {
        String fileName;

        System.out.print("File name: ");
        fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

        if(fileName==null)
            return;

        if(fileName.length() < 1)
            return;

        saveGameToFile(fileName);
    }
    private void saveGameToFile(String fileName) throws IOException {
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
    private Game retrieveGameFromFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = null;

        try{
            //Create an object input stream connected to a file named fileName.
            oin = new ObjectInputStream(new FileInputStream(fileName));
            return (Game)oin.readObject();
        }finally{
            //If the object input stream was successfuly created, close it.
            if(oin != null)
                oin.close();
        }
    }

	//Useful Methods
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
    private String gameStats(){
        return "Level : " + game.getLevel() +  " |  Area : " +  game.getArea();
    }
    private String playerStats(){
        return "Player -> H : " +  game.getHp() + "| A : " + game.getArmor() + "| F : " + game.getFood() + "| G : " + game.getGold() + "| Xp : " + game.getXp();
    }
}
