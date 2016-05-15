package controllers;

import data.BoardView;
import data.Move;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.GameService;

import javax.inject.Inject;

public class ApiController extends Controller {

    private final GameService gameService;

    @Inject
    public ApiController(GameService gameService) {
        this.gameService = gameService;
    }

    public Result newGame() {
        return ok(Json.toJson(gameService.newGame()));
    }

    public Result getBoard(long gameId) {
        BoardView board = gameService.getBoardForRoom(gameId);

        return board == null ?
                notFound("Couldn't find game room.") :
                ok(Json.toJson(board));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result playerMove(long gameId) {
        Move move = Json.fromJson(request().body().asJson(), Move.class);

        BoardView boardView = gameService.move(gameId, move);
        return (boardView == null) ?
                badRequest() :
                ok(Json.toJson(boardView));
    }
}
