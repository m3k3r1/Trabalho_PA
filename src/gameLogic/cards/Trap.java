package gameLogic.cards;

import gameLogic.GameData;
import gameLogic.Player;

public class Trap extends Card{
	public Trap(int l){
		super(l);
		
		name= "Trap";
	}

	@Override
	public boolean isTrap(){
		return true;
	}

	@Override
	public void trapEffect(GameData g, int d){
		Player p = g.getPlayer();
		int l = g.getLevel();
		
		switch(d){
			case 1:
				if(p.getFood() > 0)
					p.addFood(-1);
				break;
			case 2:
				if(g.getGold() > 0)
					p.addGold(-1);
				break;
			case 3:
				if(p.getArmor() > 0)
					p.addArmor(-1);
				break;
			case 4:
				p.addHp(-1);
				break;
			case 5:
				if(p.getXp() > 0)
					p.addXp(-1);
				break;
			case 6:
				if(l > 0 && l <= 2)
					g.setArea(g.getArea() + 2);
				else if(l >= 3 && l <= 4)
					g.setArea(g.getArea() + 3);
				
				p.addHp(-2);
				g.setLevel(l + 1);
				
		}
		
	}
}
