package com.github.vezhlys.game.graphics;

import com.github.vezhlys.game.moves.MoveAction;

/**
 * Graphics engine interface.
 */
public interface GraphicsEngine extends AutoCloseable {

	/**
	 * Draw the view.
	 *
	 * @param view view representation of the game component
	 */
	void draw(String view);

	/**
	 * Read move input from the console.
	 *
	 * @return move action value
	 * @see MoveAction
	 */
	MoveAction readMoveAction();

	/**
	 * Get engine's graphics type.
	 *
	 * @return engine's graphics type
	 * @see GraphicsType
	 */
	GraphicsType graphicsType();
}
