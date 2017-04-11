package gameInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import gameLogic.Game;

public class TextUi {
	
	Game game;
	
	public TextUi(Game g){
		
		game = g;
		
		try{
			logo();
		}
		catch(IOException e){
	        System.out.println (e.toString());
	        System.out.println("Could not find file");
		}
	}
	
	void logo() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("img/consoleElements/logo.txt"));
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
