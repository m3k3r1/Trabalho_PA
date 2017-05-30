package minirogue;

import gameInterface.graphicalInterface.GraphicalUi;
//import gameInterface.TextUi;
import gameLogic.Game;
import gameLogic.Player;
import gameLogic.ObservableGame;

public class MiniRogue {
	
	public static void main(String[] args ){
		
		Player player = new Player();
		Game newGame = new Game(player);
		
		GraphicalUi ui = new GraphicalUi(new ObservableGame(newGame));


		/*
		TextUi ui = new TextUi();

		try{
			ui.run(newGame);
		}catch(IOException e){
			System.out.println (e.toString());
		}
		*/
	}
}
