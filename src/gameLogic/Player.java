package gameLogic;

import java.io.Serializable;

public class Player implements Serializable {

		private int hp;
		private int armor;
		private int food;
		private int gold;
		private int xp;
		private int[] spells; // 1 - FIREBALL; 2 - ICE; 3 - POISON; 4 - HEALING

		public Player(){
			hp = 0;
			armor = 0;
			xp = 0;
			spells = new int[] {0, 0};
		}
		public Player(int h, int a, int f, int g, int x){
			hp = h;
			armor = a;
			xp = x;
			spells = new int[] {0, 0};
		}
	
		public int getHp() { return hp; }
		public int getArmor() { return armor; }
		public int getFood() { return food; }
		public int getGold() { return gold; }
		public void addHp(int h) { if(hp + h < 21) hp += h; }
		public void addArmor(int a) { if(armor + a < 6) armor += a; }
		public void addFood(int f) { if(food + f < 7) food += f; }
		public void addGold(int g) { if(gold + g < 21) gold += g; }
		public void setHp(int h){
			hp = h;
		}
		public int getXp() { return xp; }
		public void addXp(int x) { if(xp + x < 37) xp += x; }
		public int getSpellValue(int p){
			return spells[p];
		}
		public boolean canBuy(int c) {
			if(gold >= c)
				return true;
			else
				return false;
		}
		public void buy(int cost){
			gold -= cost;
		}
		public void addSpell(int s) {
				for(int i = 0; i < 2; i++)
					if(spells[i] == 0) {
                        spells[i] = s;
                        break;
                    }
		}
		public boolean removeSpell() {
				for(int i = 0; i < 2; i++){
					if(spells[i] != 0){
						spells[i] = 0;
						return true;
					}
				}
				return false;
		}				
		public void setFood(int f) {
			food = f ;
		}
		public boolean hasSpell(){
			if( spells[0] == 0 && spells[1] == 0)
			    return false;
			return true;
		}
}
