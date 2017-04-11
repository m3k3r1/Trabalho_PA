package gameLogic;


public class Game implements Constants {
	Player user;
	

	public Game() {
		user = new Player();		
	}
	
	public int throwDice(){
		return (int)(Math. random() * 6 + 1);
	}
	
	public void setDificulty(int d){
			switch(d){
				case 1 : user.addArmor(CASUAL_ARMOR);
						 user.addHp(CASUAL_HP);
						 user.addGold(CASUAL_GOLD);
						 user.addFood(CASUAL_FOOD);
					 	 break;
				case 2 : user.addHp(NORMAL_HP);
			 	     	 user.addGold(NORMAL_GOLD);
			 	     	 user.addFood(NORMAL_FOOD);
			 	     	 break;
				case 3 : user.addHp(HARD_HP);
			         	 user.addGold(HARD_GOLD);
			         	 user.addFood(HARD_FOOD);
			         	 break;
				case 4 : user.addHp(IMPOSSIBLE_HP);
			         	 user.addGold(IMPOSSIBLE_GOLD);
			         	 user.addFood(IMPOSSIBLE_FOOD);
			         	 break;
			}
	}
	
	public void run(){
		
	}
	
	
}
