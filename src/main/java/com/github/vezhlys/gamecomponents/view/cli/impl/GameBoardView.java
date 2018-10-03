package com.github.vezhlys.gamecomponents.view.cli.impl;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.vezhlys.gamecomponents.view.cli.CLIGameComponentView;
import com.github.vezhlys.gamecomponents.view.cli.TileView;

/**
 * Game board view representation.
 *
 */
public final class GameBoardView implements CLIGameComponentView {
	private static final int CELL_WIDTH = 3;
	private static final int GAME_BOARD_LINE = 4;

	private List<TileView> cells;

	/**
	 * Constructor.
	 *
	 * @param cells list of cell views.
	 */
	public GameBoardView(final List<TileView> cells) {
		this.cells = cells;
	}

	@Override
	public String view() {
		String boardView = "";
		for (int i = 0; i < cells.size(); i = i + GAME_BOARD_LINE) {
			final List<String> boardLineSplit = IntStream.range(i, i + GAME_BOARD_LINE)
					.mapToObj(j -> cells.get(j).view().split("\n"))
					.flatMap(Arrays::stream)
					.collect(Collectors.toList());
			boardView += IntStream.range(0, CELL_WIDTH)
					.mapToObj(combineCellsByBoardLine(boardLineSplit))
					.collect(Collectors.joining());
		}
		return boardView;
	}

	/**
	 * Combine 4 cells lines to one board line.
	 *
	 * @param boardLineSplit split cell lines for board line
	 * @return IntFunction to combine split line items to one board line.
	 */
	private IntFunction<String> combineCellsByBoardLine(final List<String> boardLineSplit) {
		return cellLineIndex -> IntStream.range(0, GAME_BOARD_LINE)
				.mapToObj(cellInLineIndex -> boardLineSplit.get(cellLineIndex
						+ CELL_WIDTH * cellInLineIndex))
				.collect(Collectors.joining("", "", System.lineSeparator()));
	}

}
