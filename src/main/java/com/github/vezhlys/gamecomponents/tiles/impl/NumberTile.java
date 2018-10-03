package com.github.vezhlys.gamecomponents.tiles.impl;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.view.cli.TileView;
import com.github.vezhlys.gamecomponents.view.cli.impl.SimpleTileView;

/**
 * Tile with number representation.
 *
 */
public final class NumberTile implements Tile {
	private final byte number;

	/**
	 * Constructor.
	 *
	 * @param number tile's number representation
	 */
	public NumberTile(final byte number) {
		this.number = number;
	}

	@Override
	public TileView view(final GraphicsType type) {
		switch (type) {
		case CLI:
		default:
			return new SimpleTileView(String.format("%02d", number));
		}
	}

	@Override
	public String toString() {
		return Byte.valueOf(number).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof NumberTile)) {
			return false;
		}
		NumberTile other = (NumberTile) obj;
		if (number != other.number) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(final Tile tile) {
		if (tile instanceof NumberTile) {
			return this.number - ((NumberTile) tile).number;
		}
		return -1;
	}
}
