package com.github.vezhlys.gamecomponents.view;

/**
 *
 * Graphical representation of game component.
 *
 * @param <T> Graphical representation type.
 */
@FunctionalInterface
public interface GameComponentView<T> {

	/**
	 * Return graphical representation of the the game component.
	 *
	 * @return view representation of game component
	 */
	T view();

}
