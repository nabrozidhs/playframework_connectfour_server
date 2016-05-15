package services;

import data.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    Counter counter;

    @InjectMocks
    GameService gameService;

    @Test
    public void gameNew() {
        when(counter.nextCount()).thenReturn(0L);

        BoardView boardView = gameService.newGame();

        assertThat(boardView.getId(), is(0L));
    }

    @Test
    public void getBoard() {
        when(counter.nextCount()).thenReturn(0L);

        BoardView boardGame = gameService.newGame();

        assertThat(gameService.getBoardForRoom(0L),
                is(sameInstance(boardGame)));
    }

    @Test
    public void move() {
        when(counter.nextCount()).thenReturn(0L);

        gameService.newGame();

        int columdIndex = 0;
        BoardView boardView = gameService.move(0L, new Move(Player.RED, columdIndex));
        assertThat(boardView.getBoard()[columdIndex],
                is(new CellState[]{CellState.RED, CellState.NONE, CellState.NONE, CellState.NONE, CellState.NONE, CellState.NONE}));

        boardView = gameService.move(0L, new Move(Player.YELLOW, columdIndex));
        assertThat(boardView.getBoard()[columdIndex],
                is(new CellState[]{CellState.RED, CellState.YELLOW, CellState.NONE, CellState.NONE, CellState.NONE, CellState.NONE}));
    }

    @Test
    public void winCondition() {
        when(counter.nextCount()).thenReturn(0L);

        gameService.newGame();

        gameService.move(0L, new Move(Player.RED, 0));
        gameService.move(0L, new Move(Player.YELLOW, 0));
        gameService.move(0L, new Move(Player.RED, 1));
        gameService.move(0L, new Move(Player.YELLOW, 0));
        gameService.move(0L, new Move(Player.RED, 2));
        gameService.move(0L, new Move(Player.YELLOW, 0));
        BoardView board = gameService.move(0L, new Move(Player.RED, 3));

        assertThat(board.getGameState(), is(GameState.RED_WIN));
    }
}
