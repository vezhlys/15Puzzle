package com.github.vezhlys.game.pools;

import java.util.List;

import com.github.vezhlys.gamecomponents.tiles.Tile;

/**
 * Pool of tiles for the game.
 *
 */
public interface TilePool {

	/**
	 * Shuffled game tiles.
	 *
	 * @return Unmodifiable list of shuffled game tiles.
	 */
	List<Tile> shuffledTiles();

	/**
	 * Checks if tiles are in winning order.
	 *
	 * @param tiles list of tiles to check
	 * @return true if in winning order, false otherwise
	 */
	boolean winningOrder(List<Tile> tiles);
}
