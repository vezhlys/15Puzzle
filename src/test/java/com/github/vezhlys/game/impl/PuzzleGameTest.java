package com.github.vezhlys.game.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.game.graphics.GraphicsEngine;
import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.game.impl.PuzzleGame;
import com.github.vezhlys.game.moves.MoveAction;
import com.github.vezhlys.gamecomponents.board.GameBoard;
import com.github.vezhlys.gamecomponents.view.GameComponentView;
import com.github.vezhlys.gamecomponents.view.cli.CLIGameComponentView;

@RunWith(MockitoJUnitRunner.class)
public class PuzzleGameTest {

	@InjectMocks
	private PuzzleGame puzzleGame;

	@Mock
	private GraphicsEngine graphicsEngine;
	@Mock
	private GameBoard gameBoard;
	@Mock(extraInterfaces = CLIGameComponentView.class)
	private GameComponentView<String> componentView;
	
	@Before
	public void setup() {
		when(graphicsEngine.graphicsType()).thenReturn(GraphicsType.CLI);
		when(gameBoard.view(GraphicsType.CLI)).thenReturn(componentView);
		when(componentView.view()).thenReturn("BoardView");
	}

	@Test
	public void whenGameIsStartedThenBoardIsShownAndWaitingForMoveAction() { 
		when(graphicsEngine.readMoveAction()).thenReturn(MoveAction.QUIT);
		when(componentView.view()).thenReturn("BoardView");
		ArgumentCaptor<String> viewCaptor = ArgumentCaptor.forClass(String.class);
		puzzleGame.start(gameBoard);
		verify(gameBoard, times(1)).view(graphicsEngine.graphicsType());
		verify(componentView, times(1)).view();
		verify(graphicsEngine, times(1)).draw(viewCaptor.capture());
		verify(graphicsEngine, times(1)).readMoveAction();
		assertThat(viewCaptor.getValue(), is("BoardView"));
	}
	
	@Test
	public void whenMoveActionIsNotQuitWhiteForNewAction() { 
		when(graphicsEngine.readMoveAction()).thenReturn(MoveAction.DOWN, 
				MoveAction.UP,
				MoveAction.LEFT,
				MoveAction.RIGHT,
				MoveAction.QUIT);
		puzzleGame.start(gameBoard);
		verify(graphicsEngine, times(5)).readMoveAction();
	}
	
	@Test
	public void whenGameWinNotificationReceivedDrawWinMessageAndClose() throws Exception {
		when(componentView.view()).thenReturn("Win");
		puzzleGame.onGameWin(componentView);
		verify(graphicsEngine, times(1)).draw("Win");
	}

}
