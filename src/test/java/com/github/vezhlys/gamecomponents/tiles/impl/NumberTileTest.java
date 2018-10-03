package com.github.vezhlys.gamecomponents.tiles.impl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.view.cli.impl.SimpleTileView;

public class NumberTileTest {

	@Test
	public void whenConvertedToStringThenNumricalRepresentationIsReturned() {
		final byte number = 1;
		final NumberTile tile = new NumberTile(number);
		assertThat(tile.toString(), is(String.valueOf(number)));
	}

	@Test
	public void whenSortedThenElementsAreSortedByNumber() {
		final NumberTile tile = new NumberTile(Byte.valueOf("5"));
		final NumberTile tile2 = new NumberTile(Byte.valueOf("10"));
		final NumberTile tile3 = new NumberTile(Byte.valueOf("15"));
		final List<Tile> tiles = Arrays.asList(tile2, tile, tile3);
		final List<Tile> sortedTiles = Arrays.asList(tile, tile2, tile3);
		assertThat(tiles, is(not(sortedTiles)));
		Collections.sort(tiles);
		assertThat(tiles, is(sortedTiles));
	}
	
	@Test
	public void whenSortedWithOtherTilesThenNumberTileHasLowerRank() {
		final NumberTile tile = new NumberTile(Byte.valueOf("5"));
		final Tile tile2 = mock(Tile.class);
		final List<Tile> tiles = Arrays.asList(tile2, tile);
		final List<Tile> sortedTiles = Arrays.asList(tile, tile2);
		assertThat(tiles, is(not(sortedTiles)));
		Collections.sort(tiles);
		assertThat(tiles, is(sortedTiles));
	}

	@Test
	public void whenViewThenTileViewIsReturned() {
		final byte number = 1;
		final NumberTile tile = new NumberTile(number);
		assertThat(tile.view(GraphicsType.CLI), is(instanceOf(SimpleTileView.class)));
	}

}
