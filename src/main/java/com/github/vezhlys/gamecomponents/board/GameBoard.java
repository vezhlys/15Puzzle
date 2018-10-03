package com.github.vezhlys.gamecomponents.board;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.game.messengers.GameWinMessenger;
import com.github.vezhlys.game.moves.MoveAction;
import com.github.vezhlys.gamecomponents.GameComponent;
import com.github.vezhlys.gamecomponents.view.GameComponentView;

/**
 * Game board.
 *
 */
public interface GameBoard extends GameComponent, GameWinMessenger {

	/**
	 * Textual representation of the game board for graphics engine.
	 *
	 * @param type graphics type
	 * @return Game board's view representation.
	 */
	GameComponentView<String> view(GraphicsType type);

	/**
	 * Move tile to empty space.
	 *
	 * @param moveAction move action
	 * @return true if move actually happened, false otherwise
	 * @see MoveAction
	 */
	boolean moved(MoveAction moveAction);
}
