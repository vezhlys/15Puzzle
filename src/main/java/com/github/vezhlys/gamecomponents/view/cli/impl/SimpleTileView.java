package com.github.vezhlys.gamecomponents.view.cli.impl;

import com.github.vezhlys.gamecomponents.view.cli.TileView;

/**
 * CLI tile view representation.
 *
 */
public final class SimpleTileView implements TileView {
	private final String view;

	/**
	 * Constructor.
	 *
	 * @param view tile view representation
	 */
	public SimpleTileView(final String view) {
		this.view = view;
	}

	@Override
	public String view() {
		return String.format("(%s)", view);
	}

}
