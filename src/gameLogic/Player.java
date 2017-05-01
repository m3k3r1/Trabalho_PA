package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Player {

		// STATS
		private int hp;
		private int armor;
		private int food;
		private int gold;
		private int xp;
		
		// SPELL INVENTORY, MAX 2 SPELLS
		// TODO STRING ARRAY MIGHT BE EASIER
		// 1 - FIREBALL; 2 - ICE; 3 - POISON; 4 - HEALING
		private int[] spells;
		
		
		public Player(){
			hp = 0;
			armor = 0;
			food = 0;
			gold = 0;
			xp = 0;
			spells = new int[] {0, 0};
		}
		
		public Player(int h, int a, int f, int g, int x){
			hp = h;
			armor = a;
			food = f;
			gold = g;
			xp = x;
			spells = new int[] {0, 0};
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
		
		public boolean canBuy(int c) {
			if(gold >= c)
				return true;
			else
				return false;
		}
		
		public int getSpells() {
			int num = 0;
			for(int i = 0; i < 2; i++)
				if(spells[i] > 0)
					num++;
			
			return num;
		}
		
		public int[] getArraySpell(){ return spells; }
		
		// ADDS ONLY IF IT HAS SPACE
		public void addSpell(int s) {
				for(int i = 0; i < 2; i++)
					if(spells[i] != 0)
						spells[i] = s;
		}
		
		// LOOKS FOR THE SPELL ID
		public void removeSpell(int s) {
				for(int i = 0; i < 2; i++)
					if(spells[i] == s)
						spells[i] = 0;
		}
}
