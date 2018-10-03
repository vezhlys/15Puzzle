package com.github.vezhlys.gamecomponents.board.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.game.listeners.GameWinListener;
import com.github.vezhlys.game.moves.MoveAction;
import com.github.vezhlys.game.pools.TilePool;
import com.github.vezhlys.gamecomponents.board.GameBoard;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.tiles.impl.EmptyTile;
import com.github.vezhlys.gamecomponents.view.cli.CLIGameComponentView;
import com.github.vezhlys.gamecomponents.view.cli.impl.CelledTileView;
import com.github.vezhlys.gamecomponents.view.cli.impl.GameBoardView;

/**
 * CLI game board implementation.
 */
public final class DefaultCLIGameBoard implements GameBoard {
	private static final EmptyTile EMPTY_TILE = new EmptyTile();
	private static final int VERTICAL_MOVE = 4;
	private static final int HORIZONTAL_MOVE = 1;
	private final TilePool tilePool;
	private final List<Tile> tiles;
	private final GameWinListener<String> gameWinListener;

	/**
	 * Constructor.
	 *
	 * @param tilePool        Tile pool implementation.
	 * @param gameWinListener game win listener.
	 */
	public DefaultCLIGameBoard(final TilePool tilePool, final GameWinListener<String> gameWinListener) {
		this.tilePool = tilePool;
		this.tiles = tilePool.shuffledTiles();
		this.gameWinListener = gameWinListener;
	}

	@Override
	public CLIGameComponentView view(final GraphicsType type) {
		switch (type) {
		case CLI:
			return new GameBoardView(
					tiles.stream()
						.map(tile -> tile.view(type))
						.map(CelledTileView::new)
						.collect(Collectors.toList()));
		default:
			throw new GameException("Unsupported graphics type: " + type);
		}
	}

	@Override
	public void win() {
		gameWinListener.onGameWin(new CLIGameComponentView() {
			@Override
			public String view() {
				return "Congratulations. Click ESC to go back to console.";
			}
		});
	}

	@Override
	public boolean moved(final MoveAction moveAction) {
		if (winOrder()) {
			return false;
		}
		final int delta = calculateDelta(moveAction);
		final int emptyTileIndex = findEmptyTileIndex();
		final int swapIndex = emptyTileIndex + delta;
		if (hasDelta(delta) && swapIndexInRange(swapIndex)
				&& (isVerticalMove(delta)
						|| validHorizontalMove(emptyTileIndex, swapIndex))) {
			return swap(emptyTileIndex, swapIndex);
		}
		return false;
	}

	/**
	 * Calculates move delta.
	 *
	 * @param moveAction move action
	 * @return line size for vertical move, one for horizontal, zero otherwise.
	 */
	private int calculateDelta(final MoveAction moveAction) {
		switch (moveAction) {
		case DOWN:
			return -VERTICAL_MOVE;
		case UP:
			return VERTICAL_MOVE;
		case LEFT:
			return HORIZONTAL_MOVE;
		case RIGHT:
			return  -HORIZONTAL_MOVE;
		default:
			return 0;
		}
	}

	/**
	 * Checks if there was any move delta.
	 *
	 * @param delta delta value
	 * @return true if more than zero, false otherwise.
	 */
	private boolean hasDelta(final int delta) {
		return delta != 0;
	}

	/**
	 * Checks if moveIndex is a vertical move.
	 *
	 * @param delta empty tile delta from current index
	 * @return true if delta is more than one, false otherwise
	 */
	private boolean isVerticalMove(final int delta) {
		return Math.abs(delta) > 1;
	}

	/**
	 * Checks if swapIndex is not out range of the tile pool.
	 *
	 * @param swapIndex swap index
	 * @return true if in range, false otherwise
	 */
	private boolean swapIndexInRange(final int swapIndex) {
		return swapIndex >= 0 && swapIndex < tiles.size();
	}

	/**
	 * Checks if horizontal move delta is in the same row.
	 *
	 * @param emptyTileIndex empty tile index
	 * @param swapIndex swap index
	 * @return true if division by line length is the same number, false otherwise
	 */
	private boolean validHorizontalMove(final int emptyTileIndex, final int swapIndex) {
		return (swapIndex / VERTICAL_MOVE == emptyTileIndex / VERTICAL_MOVE);
	}

	/**
	 * Finds the current empty tile index in the tiles.
	 *
	 * @return Empty tile index in the list.
	 */
	private int findEmptyTileIndex() {
		return tiles.indexOf(EMPTY_TILE);
	}

	/**
	 * Swaps an empty tile with the numeric one.
	 *
	 * @param emptyTileIndex empty tile index
	 * @param swapIndex      index of numeric tile
	 * @return true if no win after swap, false otherwise.
	 */
	private boolean swap(final int emptyTileIndex, final int swapIndex) {
		tiles.set(emptyTileIndex, tiles.get(swapIndex));
		tiles.set(swapIndex, EMPTY_TILE);
		return !winOrder();
	}

	/**
	 * Check if tiles are in win order and calls win if yes.
	 *
	 * @return true if in win order, false otherwise.
	 */
	private boolean winOrder() {
		final boolean win = tilePool.winningOrder(tiles);
		if (win) {
			win();
		}
		return win;
	}
}
