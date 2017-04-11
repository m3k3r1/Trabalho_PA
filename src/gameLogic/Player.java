package gameLogic;

public class Player {

		//Stats
		private int hp;
		private int armor;
		private int food;
		private int gold;
		
		private int xp;
		
		//Spells
		// (?) spells;
		
		public Player(){
			hp = 0;
			armor = 0;
			food = 0;
			gold = 0;
			
			xp = 0;
		}
		
		public Player(int h, int a, int f, int g, int x){
			hp = h;
			armor = a;
			food = f;
			gold = g;
			
			xp = x;
		}
		
		public int getHp() { return hp; }
		public int getArmor() { return armor; }
		public int getFood() { return food; }
		public int getGold() { return gold; }
		
		public void addHp(int h) { hp += h; }
		public void addArmor(int a) { armor += a; }
		public void addFood(int f) { food += f; }
		public void addGold(int g) { gold += g; }
		
		public int getXp() { return xp; }
		public void addXp(int x) { xp += x; }
		
		// public (?) getSpells();
		// public void setSpells();
}