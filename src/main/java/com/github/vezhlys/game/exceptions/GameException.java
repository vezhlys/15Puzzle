package com.github.vezhlys.game.exceptions;

/**
 * Game exceptions wrapper.
 */
public class GameException extends RuntimeException {
	private static final long serialVersionUID = -2661679020769085926L;

	/**
	 * Constructor.
	 *
	 * @param message error message
	 */
	public GameException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message error message
	 * @param cause   wrapped exception
	 */
	public GameException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
