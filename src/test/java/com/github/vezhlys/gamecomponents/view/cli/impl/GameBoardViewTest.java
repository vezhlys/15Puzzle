package com.github.vezhlys.gamecomponents.view.cli.impl;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.gamecomponents.view.cli.TileView;


@RunWith(MockitoJUnitRunner.class)
public class GameBoardViewTest {
	private static final String LINE_SEPARATOR = System.lineSeparator();
	@Mock
	private TileView view1;
	@Mock
	private TileView view2;
	@Spy
	private ArrayList<TileView> tileViews;
	@InjectMocks
	private GameBoardView gameBoardView;
	
	@Before
	public void setup() {
		tileViews.add(view1);
		tileViews.add(view2);
		tileViews.add(view2);
		tileViews.add(view1);
	}
	
	@Test
	public void whenViewThenCombineCellsIntoRows() {
		when(view1.view()).thenReturn("A\nB\nC");
		when(view2.view()).thenReturn("D\nE\nF");
		final String gameBoard = gameBoardView.view();
		assertThat(gameBoard, is("ADDA" + LINE_SEPARATOR 
				+ "BEEB"  + LINE_SEPARATOR 
				+ "CFFC"  + LINE_SEPARATOR));
	}
}
