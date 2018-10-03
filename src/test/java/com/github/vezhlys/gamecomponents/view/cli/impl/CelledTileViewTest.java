package com.github.vezhlys.gamecomponents.view.cli.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.gamecomponents.view.cli.TileView;

@RunWith(MockitoJUnitRunner.class)
public class CelledTileViewTest {
	@InjectMocks
	private CelledTileView celledView;
	@Mock
	private TileView tileView;

	@Test
	public void whenViewThenReturnCelledViewRepresentation() {
		when(tileView.view()).thenReturn("(10)");
		assertThat(celledView.view(), is(" \u2014\u2014\u2014\u2014 \n"
				+ "|(10)|\n"
				+ " \u2014\u2014\u2014\u2014 "));
	}

}
