package KeraaOmenoitaPeli;

import java.awt.Color;
import asciiPanel.AsciiPanel;

//Laattaluokka
public enum Tile {
	//lattia, sein�t ja reunukset
    FLOOR((char)250, AsciiPanel.yellow),
    WALL((char)177, AsciiPanel.yellow),
    BOUNDS('x', AsciiPanel.brightBlack);
    
    //ATTRIBUUTIT
    private char glyph;
    private Color color;
    /**
     * metodi joka palauttaa laatan symbolin
     * @return glyph
     */
    public char glyph() {
    	return glyph;
    }
    
    /**
     * palauttaa v�rin
     * @return color
     */
    public Color color() { 
    	return color;
    }
 
    Tile(char glyph, Color color){
        this.glyph = glyph;
        this.color = color;
    }
    
    /**
     * totuusarvo onko kyseess� maalaatta
     * @return onko maa
     */
    public boolean isGround(){
    	if(this != WALL && this != BOUNDS){
    		return true;
    	}
    	return false;
    }
}