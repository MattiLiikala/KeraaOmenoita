package KeraaOmenoitaPeli;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import KeraaOmenoitaPeli.screens.Screen;

public interface ServerInt extends Remote {
	void createWorld() throws RemoteException;
	void createCreatures(CreatureFactory creatureFactory) throws RemoteException;
	int joinGame(Screen KeraaOmenoitaScreen) throws RemoteException;
	public boolean isStarted() throws RemoteException;
	public World getWorld() throws RemoteException;
	public Creature getPlayer(int player) throws RemoteException;
	public Creature playerMove(int player, int x, int y) throws RemoteException;
	public int getPlayer1Score()throws RemoteException;
	public int getPlayer2Score() throws RemoteException;
	
}
