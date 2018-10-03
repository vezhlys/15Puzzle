package com.github.vezhlys.gamecomponents.tiles.impl;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.view.cli.impl.SimpleTileView;

public class EmptyTileTest {

	@Test
	public void whenConvertedToStringThenEmptyStringIsReturned() {
		final EmptyTile tile = new EmptyTile();
		assertThat(tile.toString(), is(""));
	}
	
	@Test
	public void whenSortedWithOtherTilesThenItIsAlwaysHigher() {
		final EmptyTile tile = new EmptyTile();
		assertThat(tile.compareTo(mock(Tile.class)), is(1));
	}
	
	@Test
	public void whenSortedWithEmptyTilesThenItIsEqual() {
		final EmptyTile tile = new EmptyTile();
		assertThat(tile.compareTo(new EmptyTile()), is(0));
	}
	
	@Test
	public void whenViewThenTileViewIsReturned() {
		final EmptyTile tile = new EmptyTile();
		assertThat(tile.view(GraphicsType.CLI), is(instanceOf(SimpleTileView.class)));
	}
	
	@Test
	public void allEmptyTilesAreEqual() {
		final EmptyTile tile = new EmptyTile();
		final EmptyTile tile2 = new EmptyTile();
		assertThat(tile, is(tile2));
		assertThat(tile.hashCode(), is(tile2.hashCode()));
	}
	
	@Test
	public void emptyTilesNotEqualWithOtherTile() {
		final EmptyTile tile = new EmptyTile();
		final Tile tile2 = mock(Tile.class);
		assertThat(tile, is(not(tile2)));
		assertThat(tile.equals(null), is(false));
	}

}
