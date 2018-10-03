package com.github.vezhlys.game.pools.impl;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.tiles.impl.EmptyTile;
import com.github.vezhlys.gamecomponents.tiles.impl.NumberTile;

public class Puzzle15TilePoolTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	private static final int POOL_SIZE = 15;
	private Puzzle15TilePool puzzle15TilePool;

	@Before
	public void setup() {
		puzzle15TilePool = new Puzzle15TilePool();
	}

	@Test
	public void whenTilesRequestedThen15NumberTilesAreReceived() {
		final List<Tile> tiles = puzzle15TilePool.shuffledTiles();
		assertThat(tiles.size(), is(POOL_SIZE + 1));
		assertThat(tiles.get(POOL_SIZE), is(instanceOf(EmptyTile.class)));
		IntStream.range(0, POOL_SIZE).mapToObj(tiles::get).forEach(tile -> {
			assertThat(tile, is(instanceOf(NumberTile.class)));
		});
	}

	@Test
	public void whenTilesRequestedThenAllAreUnique() {
		final List<Tile> tiles = puzzle15TilePool.shuffledTiles();
		assertThat(new TreeSet<>(tiles).size(), is(POOL_SIZE + 1));
	}

	@Test
	public void whenInWiningOrderThenReturnTrue() {
		final List<Tile> tiles = new Puzzle15TilePool().shuffledTiles();
		final List<Tile> winOrderedTiles = tiles.stream().sorted().collect(Collectors.toList());
		assertThat(winOrderedTiles.size(), is(POOL_SIZE + 1));
		assertThat(puzzle15TilePool.winningOrder(winOrderedTiles), is(true));
	}
	
	@Test
	public void whenInLoosingOrderThenReturnFalse() {
		final List<Tile> tiles = new Puzzle15TilePool().shuffledTiles();
		assertThat(puzzle15TilePool.winningOrder(tiles), is(false));
	}
	
	@Test
	public void whenTooSmallListIsSuppliedThrowGameException() {
		exception.expect(GameException.class);
		final List<Tile> tiles = new ArrayList<>();
		puzzle15TilePool.winningOrder(tiles);
	}

}
