package KeraaOmenoitaPeli;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Timer;

import KeraaOmenoitaPeli.screens.KeraaOmenoitaScreen;
import KeraaOmenoitaPeli.screens.Screen;

public class Server extends UnicastRemoteObject implements ServerInt {
	
	//ATTRIBUUTIT
	public static final String REGISTRY_NAME = Server.class.getName();
	int registryPortNumber;
	private World world;
	private int clients;
	private Hashtable<String, Screen> players;
	private Creature player1;
	private Creature player2;
    private int screenWidth;
    private int screenHeight;
    private boolean timerStarted;
    private boolean gameStarted;
    
    public static void main(String[] args)throws Exception{
    	int registryPortNumber = 1099;
    	LocateRegistry.createRegistry(registryPortNumber);
    	Naming.rebind(REGISTRY_NAME, new Server());
        System.out.println("Server is up");
    }
    
    public Server() throws Exception{
    	screenWidth = 80;
        screenHeight = 20;
        clients = 0;
        players = new Hashtable<String, Screen>();
        player1 = null;
        player2 = null;
        timerStarted = false;
        gameStarted = false;
        try {
			createWorld();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        CreatureFactory creatureFactory = new CreatureFactory(world);
        createCreatures(creatureFactory);
    }
    
	public void createWorld() throws RemoteException{
        world = new WorldBuilder(80, 20).makeWorld().build();
    }
	
	public int getPlayer1Score(){
		return player1.score;
	}
	
	public int getPlayer2Score(){
		return player2.score;
	}
	
	
	public void createCreatures(CreatureFactory creatureFactory){
    	player1 = creatureFactory.newPlayer();
    	player2 = creatureFactory.newPlayer();
    	
    	for(int i = 0; i < 8; i++){
    		creatureFactory.newApple();
    	}
    }
	
	@Override
	public int joinGame(Screen KeraaOmenoitaScreen) throws RemoteException{
		int playerIdNumber;
		try{
			if(gameStarted == false){
				if(clients == 0){
					playerIdNumber = 1;
					System.out.println("Player1 connected");
				}else{
					playerIdNumber = 2;
					System.out.println("Player2 connected");
				}
				clients++;
				if (clients >= 2){
					gameStarted = true;
				}
			}else{playerIdNumber = 0;}
		}catch(Exception e){
			playerIdNumber = 0;
			e.printStackTrace();
		}
		return playerIdNumber;
	}
	
	public boolean isStarted() throws RemoteException{
		return this.gameStarted;
	}
	
	public World getWorld() throws RemoteException{
		return this.world;
	}
	
	public Creature getPlayer(int player) throws RemoteException{
		if(player == 1){
			return player1;
		}else{
			return player2;
		}
	}
	
	public Creature playerMove(int player, int x, int y){
		if(player == 1){
			player1.moveBy(x,y);
			return player1;
		}else{
			player2.moveBy(x,y);
			return player2;
		}
	}

}