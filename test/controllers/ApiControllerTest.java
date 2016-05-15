package controllers;

import data.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.libs.Json;
import play.mvc.Result;
import services.GameService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

    @Mock
    GameService gameService;

    @InjectMocks ApiController apiController;


    @Test
    public void newGame() throws Exception {
        BoardView boardView = new BoardView(
                0L,
                new CellState[0][0],
                GameState.YELLOW_PLAYING
        );
        when(gameService.newGame()).thenReturn(boardView);

        Result result = apiController.newGame();

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertThat(contentAsString(result), is(equalTo(Json.toJson(boardView).toString())));
    }

    @Test
    public void getBoard() throws Exception {
        long gameId = 1L;

        BoardView boardView = new BoardView(
                gameId,
                new CellState[0][0],
                GameState.YELLOW_PLAYING
        );
        when(gameService.getBoardForRoom(eq(gameId))).thenReturn(boardView);

        Result result = apiController.getBoard(gameId);

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertThat(contentAsString(result), is(equalTo(Json.toJson(boardView).toString())));
    }

    @Test
    public void getBoard_doesNotExist() throws Exception {
        when(gameService.getBoardForRoom(anyLong())).thenReturn(null);

        Result result = apiController.getBoard(1L);

        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void playerMove() throws Exception {
        int gameId = 1;
        Player currentPlayer = Player.YELLOW;

        BoardView boardView = new BoardView(
                gameId,
                new CellState[0][0],
                GameState.RED_PLAYING
        );

        when(gameService.move(gameId, new Move(currentPlayer, 3)))
                .thenReturn(boardView);

        Result result = apiController.playerMove(gameId);

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertThat(contentAsString(result), is(equalTo(Json.toJson(boardView).toString())));

    }
}