package KeraaOmenoitaPeli;

import asciiPanel.AsciiPanel;
public class CreatureFactory {
	private World world;
	
	public CreatureFactory(World world){
		this.world = world;
	}
	
	/**
	 * uusi pelaaja
	 * @return pelaaja
	 */
	public Creature newPlayer(){
		Creature player = new Creature(world, '@', AsciiPanel.brightBlue);
		world.addAtEmptyLocation(player);
		new PlayerAi(player);
		return player;
	}
	
	/**
	 * uusi omena
	 * @return omena
	 */
	public Creature newApple(){
	    Creature apple = new Creature(world, 'O', AsciiPanel.red);
	    world.addAtEmptyLocation(apple);
	    new AppleAi(apple);
	    return apple;
	}
}
