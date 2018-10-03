package com.github.vezhlys.gamecomponents.tiles.impl;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.view.cli.TileView;
import com.github.vezhlys.gamecomponents.view.cli.impl.SimpleTileView;

/**
 * Empty tile.
 *
 */
public final class EmptyTile implements Tile {
	private static final String EMPTY_VIEW = "  ";

	@Override
	public TileView view(final GraphicsType type) {
		switch (type) {
		case CLI:
		default:
			return new SimpleTileView(EMPTY_VIEW);
		}
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public int compareTo(final Tile tile) {
		if (tile instanceof EmptyTile) {
			return 0;
		}
		return 1;
	}

	@Override
	public int hashCode() {
		return EMPTY_VIEW.hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof EmptyTile) {
			return true;
		}
		return false;
	}
}
