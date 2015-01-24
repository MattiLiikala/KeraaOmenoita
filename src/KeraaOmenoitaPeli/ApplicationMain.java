package KeraaOmenoitaPeli;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import KeraaOmenoitaPeli.screens.KeraaOmenoitaScreen;
import KeraaOmenoitaPeli.screens.Screen;
 
public class ApplicationMain extends JFrame implements KeyListener {
	//sarjanumero
	private static final long serialVersionUID = 1060623638149583738L;
	//ATTRIBUUTIT
	private AsciiPanel terminal;
	private Screen screen;
	
	//KONSTRUKTORI
	public ApplicationMain() throws Exception{
		super();
		terminal = new AsciiPanel();
		add(terminal);
		pack();
		screen = new KeraaOmenoitaScreen();
		addKeyListener(this);
		repaint();
	}
	
	/**
	 * metodi joka maalaa jatkuvasti ruudulle outputin
	 */
	@Override
	public void repaint(){
		terminal.clear();
		try {
			screen.displayOutput(terminal);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.repaint();
	}
	
	/**
	 * metodi joka vastaa napin painalluksiin
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		try {
			screen = screen.respondToUserInput(e);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		repaint();
	}
	
	/**
	 * napin vapautus
	 */
	@Override
	public void keyReleased(KeyEvent e) { }
	
	/**
	 * napin painallus
	 */
	@Override
	public void keyTyped(KeyEvent e) { }
	
	/**
	 * main metodi
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationMain app = new ApplicationMain();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
