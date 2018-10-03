package com.github.vezhlys.game.graphics.console;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyInt;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.game.exceptions.GameException;
import com.github.vezhlys.game.graphics.GraphicsEngine;
import com.github.vezhlys.game.graphics.GraphicsType;
import com.github.vezhlys.game.moves.MoveAction;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;

@RunWith(MockitoJUnitRunner.class)
public class CLIGraphicsEngineTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@Mock
	private TerminalFactory terminalFactory;
	@Mock
	private Terminal terminal;
	private CLIGraphicsEngine engine;

	@Before
	public void setup() throws IOException {
		when(terminalFactory.createTerminal()).thenReturn(terminal);
		engine = new CLIGraphicsEngine(terminalFactory);
	}

	@Test
	public void whenTerminalCreationFailsThenThrowGameException() throws Exception {
		when(terminalFactory.createTerminal()).thenThrow(new IOException());
		exception.expect(GameException.class);
		exception.expectMessage("Failed to create a terminal.");
		try (GraphicsEngine engine = new CLIGraphicsEngine(terminalFactory)) {
		}
	}
	
	@Test
	public void whenDrawThenPutStringAndPerformScreenOperations() throws Exception {
		final TextGraphics textGraphics = mock(TextGraphics.class);
		when(terminal.newTextGraphics()).thenReturn(textGraphics);
		engine.draw("View" + System.lineSeparator() 
			+ "View" + System.lineSeparator() + "View");
		verify(terminal, times(1)).clearScreen();
		verify(terminal, times(1)).setCursorVisible(false);
		verify(textGraphics, times(3)).putString(eq(0), anyInt(), eq("View"));
		verify(terminal, times(1)).flush();
	}
	
	@Test
	public void whenTerminalFlushFailsThenThrowGameException() throws Exception {
		final TextGraphics textGraphics = mock(TextGraphics.class);
		when(terminal.newTextGraphics()).thenReturn(textGraphics);
		doThrow(new IOException()).when(terminal).flush();
		exception.expect(GameException.class);
		exception.expectMessage("Failed on terminal operations.");
		engine.draw("View");
	}
	
	@Test
	public void whenTerminalNewGraphicsFailsThenThrowGameException() throws Exception {
		when(terminal.newTextGraphics()).thenThrow(IOException.class);
		exception.expect(GameException.class);
		exception.expectMessage("Failed on terminal operations.");
		engine.draw("View");
	}
	
	@Test
	public void whenTerminalClearScreenFailsThenThrowGameException() throws Exception {
		doThrow(new IOException()).when(terminal).clearScreen();
		exception.expect(GameException.class);
		exception.expectMessage("Failed on terminal operations.");
		engine.draw("View");
	}
	
	@Test
	public void whenTerminalHideCursorFailsThenThrowGameException() throws Exception {
		doThrow(new IOException()).when(terminal).setCursorVisible(false);
		exception.expect(GameException.class);
		exception.expectMessage("Failed on terminal operations.");
		engine.draw("View");
	}
	
	@Test
	public void whenUnsupportedKeyStrokeThenLoopUntilSupportedIsReceived() throws IOException {
		final KeyStroke keyStroke = mock(KeyStroke.class);
		when(terminal.readInput()).thenReturn(keyStroke);
		when(keyStroke.getKeyType()).thenReturn(KeyType.Backspace, KeyType.ArrowDown);
		final MoveAction moveAction = engine.readMoveAction();
		
		assertThat(moveAction, is(MoveAction.DOWN));
	}
	
	@Test
	public void whenReadInputFailsThenThrowGameException() throws IOException {
		when(terminal.readInput()).thenThrow(IOException.class);
		exception.expect(GameException.class);
		exception.expectMessage("Failed to read key stroke.");
		engine.readMoveAction();
	}
	
	@Test
	public void whenKeyStrokeIsAnArrowReturnAppropriateMoveAction() throws IOException {
		final KeyStroke keyStroke = mock(KeyStroke.class);
		when(terminal.readInput()).thenReturn(keyStroke);
		when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
		final MoveAction moveAction = engine.readMoveAction();
		assertThat(moveAction, is(MoveAction.DOWN));
	}
	
	@Test
	public void whenCloseThenCloseTerminal() throws Exception {
		engine.close();
		verify(terminal, times(1)).close();
	}
	
	@Test
	public void thenGraphicsTypeIsRequestedThenReturnCLI() {
		final GraphicsType type = engine.graphicsType();
		assertThat(type, is(GraphicsType.CLI));
	}

}
