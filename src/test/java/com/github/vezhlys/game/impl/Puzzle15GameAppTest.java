package com.github.vezhlys.game.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.game.Game;
import com.github.vezhlys.gamecomponents.board.GameBoard;

@RunWith(MockitoJUnitRunner.class)
public class Puzzle15GameAppTest {
	
	@InjectMocks
	private Puzzle15GameApp puzzle15GameApp;
	@Mock
	private Game game;
	@Mock
	private GameBoard gameBoard;

	@Test
	public void test() {
		puzzle15GameApp.open();
		verify(game, times(1)).start(gameBoard);
	}

}
