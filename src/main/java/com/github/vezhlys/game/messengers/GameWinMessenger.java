package com.github.vezhlys.game.messengers;

/**
 * Sends a game winning message.
 *
 */
@FunctionalInterface
public interface GameWinMessenger {

	/**
	 * Sends win message in listeners.
	 */
	void win();
}
