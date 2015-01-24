package KeraaOmenoitaPeli;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.rmi.RemoteException;

import asciiPanel.AsciiPanel;

public class World implements Serializable {
	//ATTRIBUUTIT
	//laatat
    private Tile[][] tiles;
    //maailman leveys
    private int width;
    //maailman korkeus
    private int height;
    
    private List<Creature> creatures;
    
    public int P1score;
    public int P2score;
    //KONSTRUKTORIT
    /**
     * maan konstruktori
     * @param tiles
     */
    public World (Tile[][] tiles) throws RemoteException{
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.creatures = new ArrayList<Creature>();
		this.P1score = 0;
		this.P2score = 0;
	}
    
    //METODIT
    

	/**
     * palauttaa leveyden
     * @return maan leveys
     */
    public int width() {
    	return width; 
    }
    
    /**
     * palauttaa korkeuden
     * @return maan korkeus
     */
    public int height() {
    	return height; 
    }
    
    /**
     * palauttaa laatan symbolin
     * @param x koordinaatti
     * @param y koordinaatti
     * @return glyph, laatan symboli
     */
    public char glyph(int x, int y){
        return tile(x, y).glyph();
    }
    
    /**
     * palauttaa laatan v‰rin
     * @param x koordinaatti
     * @param y koordinaatti
     * @return color, laatan v‰ri
     */
    public Color color(int x, int y){
        return tile(x, y).color();
    }
    
    /**
     * metodi jolla testataan ollaanko rajojen sis‰ll‰
     * @param x koordinaatti
     * @param y koordinaatti
     * @return kyseisen laatan jos ollaan rajojen sis‰ll‰,
     *  muutoin palauttaa rajalaatan
     */
    public Tile tile(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height)
            return Tile.BOUNDS;
        else
            return tiles[x][y];
    }
    
    /**
     * lis‰‰ otuksen tyhj‰lle paikalle
     * @param creature
     */
    public void addAtEmptyLocation(Creature creature){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y).isGround() || creature(x,y) != null);
		
		creature.x = x;
		creature.y = y;
		creatures.add(creature);
	}
    
    /**
     * onko sijainnissa otus
     * @param x-koordinaatti
     * @param y-koordinaatti
     * @return palauttaa otuksen
     */
    public Creature creature(int x, int y){
        for (Creature c : creatures){
            if (c.x == x && c.y == y)
                return c;
        }
        return null;
    }
   
    /**
     * poistaa otuksen
     * @param other
     */
    public void remove(Creature other) {
        creatures.remove(other);
    }
    
}