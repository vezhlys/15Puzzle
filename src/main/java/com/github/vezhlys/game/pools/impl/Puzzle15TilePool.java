package com.github.vezhlys.game.pools.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.pools.TilePool;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.tiles.impl.EmptyTile;
import com.github.vezhlys.gamecomponents.tiles.impl.NumberTile;

/**
 * 15 puzzle tiles pool. Non thread safe.
 */
public final class Puzzle15TilePool implements TilePool {
	/**
	 * Pool size is fixed according to game rules.
	 */
	private static final int POOL_SIZE = 15;

	@Override
	public List<Tile> shuffledTiles() {
		final List<Tile> tiles = generatedPool();
		shuffle(tiles);
		tiles.add(new EmptyTile());
		return tiles;
	}

	@Override
	public boolean winningOrder(final List<Tile> tiles) {
		if (tiles.size() <= POOL_SIZE) {
			throw new GameException("List of game tiles is too small.");
		}
		final List<Tile> sortedTiles = generatedPool();
		return IntStream.range(0, POOL_SIZE).allMatch(i -> sortedTiles.get(i).compareTo(tiles.get(i)) == 0);
	}

	/**
	 * Shuffle tiles list. It will generate a random number as a control one to
	 * prevent tile representing the number to stay in it's own position.
	 *
	 * @param tiles generated list of tiles
	 */
	private void shuffle(final List<Tile> tiles) {
		final int controlValue = new Random().nextInt(POOL_SIZE);
		final Tile random = new NumberTile((byte) (controlValue + 1));
		do {
			Collections.shuffle(tiles);
		} while (!random.equals(tiles.get(controlValue)));
	}

	/**
	 * Generates number tiles from 1 to 15.
	 *
	 * @return ordered number tiles from 1 to 15.
	 */
	private List<Tile> generatedPool() {
		return IntStream.rangeClosed(1, POOL_SIZE)
				.mapToObj(Integer::toString)
				.map(Byte::valueOf)
				.map(NumberTile::new)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}
