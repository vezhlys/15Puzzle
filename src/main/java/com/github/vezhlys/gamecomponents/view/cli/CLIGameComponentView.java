package com.github.vezhlys.gamecomponents.view.cli;

import com.github.vezhlys.gamecomponents.view.GameComponentView;

/**
 * CLI game component view.
 *
 */
public interface CLIGameComponentView extends GameComponentView<String> {

	/**
	 * Return graphical representation of the the game component.
	 *
	 * @param gameComponent
	 * @return textual representation of the game component
	 */
	String view();

}
