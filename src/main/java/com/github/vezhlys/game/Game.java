package com.github.vezhlys.game;

import com.github.vezhlys.gamecomponents.board.GameBoard;

/**
 * Game interface.
 *
 */
public interface Game {

	/**
	 * Start the game.
	 *
	 * @param gameBoard game board
	 */
	void start(GameBoard gameBoard);
}
