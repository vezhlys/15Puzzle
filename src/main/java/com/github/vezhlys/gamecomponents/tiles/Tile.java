package com.github.vezhlys.gamecomponents.tiles;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.GameComponent;
import com.github.vezhlys.gamecomponents.view.cli.TileView;

/**
 * Tile game component.
 *
 */
public interface Tile extends GameComponent, Comparable<Tile> {
	/**
	 * Tile view representation for graphics engine.
	 *
	 * @param type graphics type
	 * @return Game component's view.
	 */
	TileView view(GraphicsType type);
}
