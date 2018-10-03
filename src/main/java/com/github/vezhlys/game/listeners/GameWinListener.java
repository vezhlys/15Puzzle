package com.github.vezhlys.game.listeners;

import com.github.vezhlys.gamecomponents.view.GameComponentView;

/**
 * Listener for game winning notification.
 *
 * @param <T> game component view return type
 */
@FunctionalInterface
public interface GameWinListener<T> {

	/**
	 * On game win action.
	 *
	 * @param gameWinView game win view representation
	 */
	void onGameWin(GameComponentView<T> gameWinView);
}
