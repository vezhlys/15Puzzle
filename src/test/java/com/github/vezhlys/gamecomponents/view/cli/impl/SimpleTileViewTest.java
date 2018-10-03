package com.github.vezhlys.gamecomponents.view.cli.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleTileViewTest {

	@Test
	public void whenViewThenReturnSimpleViewRepresentation() {
		SimpleTileView tileView = new SimpleTileView("10");
		assertThat(tileView.view(), is("(10)"));
	}

}
