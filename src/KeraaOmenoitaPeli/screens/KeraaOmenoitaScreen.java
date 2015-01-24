package KeraaOmenoitaPeli.screens;

import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

import KeraaOmenoitaPeli.Creature;
import KeraaOmenoitaPeli.Server;
import KeraaOmenoitaPeli.ServerInt;
import KeraaOmenoitaPeli.World;
import asciiPanel.AsciiPanel;

public class KeraaOmenoitaScreen implements Screen {
	String playerId;
	int playerNumber;
	String host;
	int portNumber;
	private int screenWidth;
    private int screenHeight;
	private World world;
	private Creature player;
	private ServerInt server;
	String lookupName;
	
	public KeraaOmenoitaScreen() throws Exception{
		screenWidth = 80;
        screenHeight = 20;
		playerId = null;
		host = "localhost";
		portNumber = 1099;
		tryConnecting();
	}
	
	/**
     * kertoo scrollauksen x-akselilla
     * @return scrollin (pelaajan) sijainti x-akselilla
     */
    public int getScrollX() {
        return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
    }
    
    /**
     * kertoo scrollauksen y-akselilla
     * @return scrollin (pelaajan) sijainti y-akselilla
     */
    public int getScrollY() {
        return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
    }
	
	/**
	 * kirjoitetaan näytölle tarvittavat tiedot
	 * @throws RemoteException 
	 */
	@Override
	public void displayOutput(AsciiPanel terminal) throws RemoteException {
		if (server.isStarted() == true){
			this.world = server.getWorld();
			int left = getScrollX();
	        int top = getScrollY();
	    
	        displayTiles(terminal, left, top);
	        terminal.writeCenter("Olet: " + playerId, 21);
			terminal.writeCenter("-- Ker"+(char)132+(char)132+" kaikki herkulliset omenat koriisi ennen kilpailijaasi! --", 23);
			terminal.write("1P Omenoita: " + server.getPlayer1Score(), 1, 20);
			terminal.write("2P Omenoita: " + server.getPlayer2Score(), 65, 20);
		}else{
		terminal.writeCenter("Ker" +(char)132+(char)132+" Omenoita!", 11, AsciiPanel.red);
		terminal.writeCenter("paina [Space] yhdist"+(char)132+(char)132+"ksesi palvelimelle", 13, AsciiPanel.yellow);
		}
	}
	
	
	
	private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < screenWidth; x++){
            for (int y = 0; y < screenHeight; y++){
                int wx = x + left;
                int wy = y + top;
     
                Creature creature = world.creature(wx, wy);
                if (creature != null)
                    terminal.write(creature.glyph(), creature.x - left, creature.y - top, creature.color());
                else
                    terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
            }
        }
    }
	
	
	/**
	 * Metodi, joka reagoi käyttäjän painaessa space 
	 */
	@Override
	public Screen respondToUserInput(KeyEvent key) throws RemoteException {
		switch (key.getKeyCode()){
		case KeyEvent.VK_SPACE: try {
				joinGame();
			} catch (Exception e) {
				e.printStackTrace();
			}
		case KeyEvent.VK_LEFT:
        case KeyEvent.VK_H: player = server.playerMove(playerNumber,-1, 0); break;
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_L: player = server.playerMove(playerNumber, 1, 0); break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_K: player = server.playerMove(playerNumber, 0,-1); break;
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_J: player = server.playerMove(playerNumber, 0, 1); break;
        case KeyEvent.VK_Y: player = server.playerMove(playerNumber,-1,-1); break;
        case KeyEvent.VK_U: player = server.playerMove(playerNumber, 1,-1); break;
        case KeyEvent.VK_B: player = server.playerMove(playerNumber,-1, 1); break;
        case KeyEvent.VK_N: player = server.playerMove(playerNumber, 1, 1); break;
		}
		return this;
	}
	
	
	@Override
	public void tryConnecting() throws Exception{
		lookupName = "//" + host + ":" + portNumber + "/" + Server.REGISTRY_NAME;
		server = (ServerInt)Naming.lookup(lookupName);
	}
	
	public void joinGame() throws Exception{
		playerNumber = server.joinGame(this);
		playerId = "Player" + playerNumber;
		this.player = server.getPlayer(playerNumber);
		this.world = server.getWorld();
	}
	
	
}
