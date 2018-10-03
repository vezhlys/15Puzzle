package com.github.vezhlys.gamecomponents;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.gamecomponents.view.GameComponentView;

/**
 * General game component.
 *
 */
@FunctionalInterface
public interface GameComponent {

	/**
	 * Game component's view representation for graphics engine.
	 *
	 * @param type graphics type
	 * @return Game component's view.
	 */
	GameComponentView<?> view(GraphicsType type);
}
