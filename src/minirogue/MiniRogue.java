package minirogue;

import java.io.IOException;

//import gameInterface.GraphicalUi;
import gameInterface.TextUi;
import gameLogic.Game;
import gameLogic.Player;

public class MiniRogue {
	
	public static void main(String[] args ){
		
		Player player = new Player();
		Game newGame = new Game(player);
		
		//GraphicalUi ui = new GraphicalUi();
		TextUi ui = new TextUi();
		
		try{
			ui.run(newGame);
		}catch(IOException e){
			System.out.println (e.toString());
		}
	}
}
