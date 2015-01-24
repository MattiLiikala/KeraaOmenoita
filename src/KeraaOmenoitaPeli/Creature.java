package KeraaOmenoitaPeli;
import java.awt.Color;
import java.io.Serializable;

public class Creature implements Serializable {
	private World world;
	/**
	 * x- ja y-koordinaatit on tässä määritelty julkisiksi,
	 * koska niitä tullaan kutsumaan paljon, joten ei jouduta
	 * kutsumaan joka kerta havainnointimetodeita
	 */
	public int x;
	public int y;
	//merkki, joka kuvastaa hahmoa
	private char glyph;
	//hahmon väri
	private Color color;
	
	private CreatureAi ai;
	public int score;
	
	//KONSTRUKTORI
	public Creature(World world, char glyph, Color color){
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.score = 0;
	}
		
	
	//METODIT
	//Havainnointimetodit
	public char glyph(){
		return glyph;
	}
	public Color color(){
		return color;
	}
	
	//Asetusmetodit
	public void setCreatureAi(CreatureAi ai){
		this.ai = ai;
	}
	
	/**
	 * Liikkumismetodi
	 * @param mx x-koordinaatti mihin liikutaan
	 * @param my y-koordinaatti mihin liikutaan
	 */
	public void moveBy(int mx, int my){
	    Creature other = world.creature(x+mx, y+my);
	   
	    if (other == null){
	        ai.onEnter(x+mx, y+my, world.tile(x+mx, y+my));
	    }
	    else{
	        attack(other);
	        score++;
	    }
	}
	
	/**
	 * hyökkäys, tässä pelissä itseasiassa omenantoimintametodi,
	 * mutta uudelleenkäytettävyyden nimissä attack, kuullostaa järkevämmältä
	 * @param other
	 */
	public void attack(Creature other){
	    world.remove(other);
	}
	
	
	public boolean canEnter(int wx, int wy) {
		return world.tile(wx, wy).isGround() && world.creature(wx, wy) == null;
	}

	
}
