package com.github.vezhlys.gamecomponents.board;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.view.cli.CLIGameComponentView;

/**
 * CLI game board.
 *
 */
public interface CLIGameBoard extends GameBoard {

	/**
	 * CLI Game component's view representation for graphics engine.
	 *
	 * @param type graphics type
	 * @return CLI Game component's view.
	 */
	CLIGameComponentView view(GraphicsType type);

}
