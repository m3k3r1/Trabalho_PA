package gameInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import gameLogic.*;

public class TextUi implements Constants {
	
	Game game;
	
	public TextUi(Game g){
		
		game = g;
		
		menu();
	}
	
	int menu(){
	    int menu_op = 0;
	    Scanner sc = new Scanner(System.in);

	    do{
			try{
				//clearScreen();
				printTxt(LOGO_TXT);
				printTxt(MENU_TXT);
				
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
	
	
	void printTxt(String filename) throws IOException{
		
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
	
	public void run(){
		
	}
}
