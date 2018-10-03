package com.github.vezhlys.gamecomponents.board.impl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.game.listeners.GameWinListener;
import com.github.vezhlys.game.moves.MoveAction;
import com.github.vezhlys.game.pools.TilePool;
import com.github.vezhlys.gamecomponents.tiles.Tile;
import com.github.vezhlys.gamecomponents.tiles.impl.EmptyTile;
import com.github.vezhlys.gamecomponents.view.GameComponentView;
import com.github.vezhlys.gamecomponents.view.cli.CLIGameComponentView;
import com.github.vezhlys.gamecomponents.view.cli.impl.GameBoardView;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCLIGameBoardTest {
	private final int NUMERIC_TILES = 15;
	private final int MOVES_IN_LINE = 3;
	@Mock
	private TilePool tilePool;
	@Mock
	private Tile tile;
	@Mock
	private GameWinListener<String> gameWinListener;
	private List<Tile> tiles;
	private DefaultCLIGameBoard gameBoard;

	@Before
	public void setup() {
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < NUMERIC_TILES; i++) {
			tiles.add(tile);
		}
		tiles.add(new EmptyTile());
		when(tilePool.shuffledTiles()).thenReturn(tiles);
		gameBoard = new DefaultCLIGameBoard(tilePool, gameWinListener);
	}

	@Test
	public void whenGameTypeCLIThenCreateCLIGameBoardView() {
		final CLIGameComponentView gameBoardView = gameBoard.view(GraphicsType.CLI);
		verify(tile, atLeastOnce()).view(GraphicsType.CLI);
		assertThat(gameBoardView, is(instanceOf(GameBoardView.class)));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void whenGameIsWonThenSendAWinMessage() {
		gameBoard.win();
		ArgumentCaptor<GameComponentView<String>> captor = ArgumentCaptor.forClass(GameComponentView.class);
		verify(gameWinListener, times(1)).onGameWin(captor.capture());
		final String winView = captor.getValue().view();
		assertThat(winView.contains("ESC"), is(true));
	}

	@Test
	public void whenMoveToWinThenCallWinAndReturnFalse() {
		when(tilePool.winningOrder(tiles)).thenReturn(true);
		final boolean moved = gameBoard.moved(MoveAction.UP);
		verify(gameWinListener, times(1)).onGameWin(ArgumentMatchers.<GameComponentView<String>>any());
		assertThat(moved, is(false));
	}
	
	@Test
	public void whenUnknownMoveThenReturnFalse() {
		when(tilePool.winningOrder(tiles)).thenReturn(false);
		final boolean moved = gameBoard.moved(MoveAction.QUIT);
		assertThat(moved, is(false));
	}
	
	@Test
	public void whenVerticalMoveIsValidThenReturnTrue() {
		when(tilePool.winningOrder(tiles)).thenReturn(false);
		final boolean movedDown = gameBoard.moved(MoveAction.DOWN);
		assertThat(movedDown, is(true));
		final boolean movedUp = gameBoard.moved(MoveAction.UP);
		assertThat(movedUp, is(true));
	}
	
	@Test
	public void whenVerticalMoveIsInvalidThenReturnFalse() {
		when(tilePool.winningOrder(tiles)).thenReturn(false);
		final boolean moved = gameBoard.moved(MoveAction.UP);
		assertThat(moved, is(false));
	}
	
	@Test
	public void whenHorizontalMoveIsValidThenReturnTrue() {
		when(tilePool.winningOrder(tiles)).thenReturn(false);
		final boolean movedRight = gameBoard.moved(MoveAction.RIGHT);
		assertThat(movedRight, is(true));
		final boolean movedLeft = gameBoard.moved(MoveAction.LEFT);
		assertThat(movedLeft, is(true));
	}
	
	@Test
	public void whenHorizontalMoveIsInvalidThenReturnFalse() {
		when(tilePool.winningOrder(tiles)).thenReturn(false);
		for (int i = 0; i < MOVES_IN_LINE; i++) {
			assertThat(gameBoard.moved(MoveAction.RIGHT), is(true));
		}
		final boolean movedRight = gameBoard.moved(MoveAction.RIGHT);
		assertThat(movedRight, is(false));
	}
	
	@Test
	public void whenVerticalMoveReachesTopOfTheBoardThenReturnFalse() {
		for (int i = 0; i < MOVES_IN_LINE; i++) {
			assertThat(gameBoard.moved(MoveAction.DOWN), is(true));
		}
		final boolean movedDown = gameBoard.moved(MoveAction.DOWN);
		assertThat(movedDown, is(false));
	}

}
