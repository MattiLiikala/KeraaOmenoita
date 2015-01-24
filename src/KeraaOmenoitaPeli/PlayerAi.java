package KeraaOmenoitaPeli;

public class PlayerAi implements CreatureAi{
	
	protected Creature creature;
	
	public PlayerAi(Creature creature){
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}
	
	public void onEnter(int x, int y,  Tile tile){
		if (tile.isGround()){
			creature.x = x;
			creature.y = y;
		} 
	}
	
	
	
}
