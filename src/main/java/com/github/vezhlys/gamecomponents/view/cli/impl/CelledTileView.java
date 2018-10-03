package com.github.vezhlys.gamecomponents.view.cli.impl;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.vezhlys.gamecomponents.view.cli.TileView;

/**
 * Celled tile CLI view.
 *
 */
public final class CelledTileView implements TileView {
	private static final String DASH = "\u2014";
	private static final int CELL_VALUE_LENGTH = 4;

	private final TileView tileView;

	/**
	 * Constructor.
	 *
	 * @param tileView tile view
	 */
	public CelledTileView(final TileView tileView) {
		this.tileView = tileView;
	}

	@Override
	public String view() {
		return String.format(" %s \n|%s|\n %s ", repeat(DASH),
				tileView.view(), repeat(DASH));
	}

	/**
	 * Repeat symbol 4 times.
	 *
	 * @param value value to repeat
	 * @return string value repeated 4 times
	 */
	private String repeat(final String value) {
		return IntStream.range(0, CELL_VALUE_LENGTH)
				.mapToObj(i -> value)
				.collect(Collectors.joining());
	}

}
