package com.github.vezhlys.game.impl;

import com.github.vezhlys.game.Game;
import com.github.vezhlys.game.GameApp;
import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.graphics.GraphicsEngine;
import com.github.vezhlys.game.graphics.console.CLIGraphicsEngine;
import com.github.vezhlys.game.pools.impl.Puzzle15TilePool;
import com.github.vezhlys.gamecomponents.board.GameBoard;
import com.github.vezhlys.gamecomponents.board.impl.DefaultCLIGameBoard;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

/**
 * Entry point to start a 15 puzzle game.
 */
public final class Puzzle15GameApp implements GameApp {
	private final Game game;
	private final GameBoard gameBoard;

	/**
	 * Constructor.
	 *
	 * @param game game instance
	 * @param gameBoard game board instance
	 */
	private Puzzle15GameApp(final Game game, final GameBoard gameBoard) {
		this.game = game;
		this.gameBoard = gameBoard;
	}

	@Override
	public void open() {
		game.start(gameBoard);
	}

	/**
	 * Main.
	 *
	 * @param args console arguments
	 */
	public static void main(final String[] args) {
		try (GraphicsEngine graphicsEngine = new CLIGraphicsEngine(new DefaultTerminalFactory())) {
			final PuzzleGame game = new PuzzleGame(graphicsEngine);
			final GameBoard gameBoard = new DefaultCLIGameBoard(new Puzzle15TilePool(), game);
			new Puzzle15GameApp(game, gameBoard).open();
		} catch (final GameException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected exception.");
			e.printStackTrace();
		}
	}
}
