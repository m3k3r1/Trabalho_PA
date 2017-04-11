package minirogue;

import gameInterface.TextUi;
import gameLogic.Game;

public class MiniRogue {
	
	public static void main(String[] args ){
		Game newGame = new Game();
		TextUi ui = new TextUi(newGame);
		
		ui.run();
	}
}
