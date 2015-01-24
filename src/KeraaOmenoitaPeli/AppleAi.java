package KeraaOmenoitaPeli;

public class AppleAi implements CreatureAi{
	
	protected Creature creature;
	
	public AppleAi(Creature creature){
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}

	@Override
	public void onEnter(int x, int y, Tile tile) {
		
	}
}
