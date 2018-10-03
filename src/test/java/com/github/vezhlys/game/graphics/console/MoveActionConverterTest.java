package com.github.vezhlys.game.graphics.console;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.vezhlys.game.moves.MoveAction;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

@RunWith(MockitoJUnitRunner.class)
public class MoveActionConverterTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Mock
	private KeyStroke keyStroke;
	@InjectMocks
	private MoveActionConverter converter;

	@Test
	public void whenKeyStrokeArrowDownReturnDOWN() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(true));
		assertThat(action.get(), is(MoveAction.DOWN));
	}

	@Test
	public void whenKeyStrokeArrowUpReturnUP() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(true));
		assertThat(action.get(), is(MoveAction.UP));
	}

	@Test
	public void whenKeyStrokeArrowLeftReturnLEFT() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(true));
		assertThat(action.get(), is(MoveAction.LEFT));
	}

	@Test
	public void whenKeyStrokeArrowRightReturnRIGHT() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(true));
		assertThat(action.get(), is(MoveAction.RIGHT));
	}

	@Test
	public void whenKeyStrokeEscapeRightReturnQUIT() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.Escape);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(true));
		assertThat(action.get(), is(MoveAction.QUIT));
	}

	@Test
	public void whenKeyStrokeEOFThrowsGameException() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.EOF);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(true));
		assertThat(action.get(), is(MoveAction.QUIT));
	}

	@Test
	public void whenOtherKeyStrokeReturnEmptyOptional() {
		when(keyStroke.getKeyType()).thenReturn(KeyType.Backspace);
		final Optional<MoveAction> action = converter.trasformedAction();
		assertThat(action.isPresent(), is(false));
	}

}
