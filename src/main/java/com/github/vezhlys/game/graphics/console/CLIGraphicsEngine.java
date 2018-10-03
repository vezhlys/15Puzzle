package com.github.vezhlys.game.graphics.console;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.graphics.GraphicsEngine;
import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.game.moves.MoveAction;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;

/**
 * CLI graphics engine.
 */
public final class CLIGraphicsEngine implements GraphicsEngine {
	private final Terminal terminal;

	/**
	 * Constructor.
	 *
	 * @param terminalFactory terminal factory
	 */
	public CLIGraphicsEngine(final TerminalFactory terminalFactory) {
		try {
			this.terminal = terminalFactory.createTerminal();
		} catch (IOException e) {
			throw new GameException("Failed to create a terminal.", e);
		}
	}

	@Override
	public void draw(final String view) {
		try {
			clearScreen();
			final TextGraphics graphics = terminal.newTextGraphics();
			final List<String> lines = Arrays.asList(view.split(System.lineSeparator()));
			IntStream.range(0, lines.size()).forEach(drawLine(graphics, lines));
			terminal.flush();
		} catch (IOException e) {
			throw new GameException("Failed on terminal operations.", e);
		}
	}

	/**
	 * Consumer for drawing the cell line.
	 *
	 * @param graphics terminal graphics instance
	 * @param lines    cell split into lines
	 * @return IntConsumer
	 */
	private IntConsumer drawLine(final TextGraphics graphics, final List<String> lines) {
		return lineIndex -> {
			graphics.putString(0, lineIndex, lines.get(lineIndex));
		};
	}

	@Override
	public MoveAction readMoveAction() {
		while (true) {
			try {
				final Optional<MoveAction> moveAction = new MoveActionConverter(terminal.readInput())
						.trasformedAction();
				if (moveAction.isPresent()) {
					return moveAction.get();
				}
			} catch (IOException e) {
				throw new GameException("Failed to read key stroke.", e);
			}
		}
	}

	@Override
	public GraphicsType graphicsType() {
		return GraphicsType.CLI;
	}

	@Override
	public void close() throws IOException {
		terminal.close();
	}

	/**
	 * Clear the screen.
	 *
	 * @throws IOException If there was an underlying I/O error
	 */
	private void clearScreen() throws IOException {
		terminal.clearScreen();
		terminal.setCursorVisible(false);
	}

}
