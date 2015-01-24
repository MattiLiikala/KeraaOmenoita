package KeraaOmenoitaPeli.screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import asciiPanel.AsciiPanel;

import java.rmi.Remote;
import java.rmi.RemoteException;
//interface näytöille
public interface Screen extends Serializable{
	public void displayOutput(AsciiPanel terminal) throws RemoteException;
	
	public Screen respondToUserInput(KeyEvent key) throws RemoteException;

	void tryConnecting() throws Exception;
	
}
