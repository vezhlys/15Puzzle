package com.github.vezhlys.game.impl;

import com.github.vezhlys.game.Game;
import com.github.vezhlys.game.graphics.GraphicsEngine;
import com.github.vezhlys.game.listeners.GameWinListener;
import com.github.vezhlys.game.moves.MoveAction;
import com.github.vezhlys.gamecomponents.board.GameBoard;
import com.github.vezhlys.gamecomponents.view.GameComponentView;

/**
 * 15 Puzzle game.
 */
public final class PuzzleGame implements Game, GameWinListener<String> {
	private final GraphicsEngine graphicsEngine;

	/**
	 * Constructor.
	 *
	 * @param graphicsEngine graphics engine for the game
	 */
	public PuzzleGame(final GraphicsEngine graphicsEngine) {
		this.graphicsEngine = graphicsEngine;
	}

	@Override
	public void start(final GameBoard gameBoard) {
		MoveAction moveAction;
		boolean moved = true;
		do {
			if (moved) {
				graphicsEngine.draw(gameBoard
					.view(graphicsEngine.graphicsType()).view());
			}
			moveAction = graphicsEngine.readMoveAction();
			moved = gameBoard.moved(moveAction);
		} while (!MoveAction.QUIT.equals(moveAction));
	}

	@Override
	public void onGameWin(final GameComponentView<String> gameWinView) {
		graphicsEngine.draw(gameWinView.view());
	}
}
