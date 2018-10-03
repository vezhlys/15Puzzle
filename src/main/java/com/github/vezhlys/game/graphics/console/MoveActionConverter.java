package com.github.vezhlys.game.graphics.console;

import java.util.Optional;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.moves.MoveAction;
import com.googlecode.lanterna.input.KeyStroke;

/**
 * Key stroke to move action converter. TODO transform to service.
 */
final class MoveActionConverter {
	private final KeyStroke keyStroke;

	/**
	 * Constructor.
	 *
	 * @param keyStroke key stroke to convert
	 */
	MoveActionConverter(final KeyStroke keyStroke) {
		this.keyStroke = keyStroke;
	}

	/**
	 * Convert ArrowLeft, ArrowRight, ArrowUp and ArrowDown to corresponding game
	 * move action.
	 *
	 * @return game action value
	 * @throws GameException if EOF key type is received
	 */
	Optional<MoveAction> trasformedAction() throws GameException {
		switch (keyStroke.getKeyType()) {
		case ArrowLeft:
			return Optional.of(MoveAction.LEFT);
		case ArrowRight:
			return Optional.of(MoveAction.RIGHT);
		case ArrowUp:
			return Optional.of(MoveAction.UP);
		case ArrowDown:
			return Optional.of(MoveAction.DOWN);
		case Escape:
		case EOF:
			return Optional.of(MoveAction.QUIT);
		default:
			return Optional.empty();
		}
	}
}
