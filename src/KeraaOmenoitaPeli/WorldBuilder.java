package KeraaOmenoitaPeli;

import java.rmi.RemoteException;

public class WorldBuilder {
	//ATTRIBUUTIT
	private int width;
	private int height;
	private Tile[][] tiles;
	
	//KONSTRUKTORI
	public WorldBuilder(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
	}
	
	/**
	 * rakentaa maailman
	 * @return world (maailma)
	 * @throws RemoteException 
	 */
	public World build() throws RemoteException {
		return new World(tiles);
	}
	
	/**
	 * luo maailmalle lattian
	 * @return lattia
	 */
	private WorldBuilder generateFloor() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = Tile.FLOOR;
			}
		}
		return this;
	}
	
	/**
	 * maailman luontimetodi
	 * @return maailma
	 */
	public WorldBuilder makeWorld() {
		return generateFloor();
	}
}