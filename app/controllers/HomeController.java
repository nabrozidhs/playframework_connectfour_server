package controllers;

import data.BoardView;
import play.mvc.Controller;
import play.mvc.Result;
import services.GameService;
import views.html.game;

import javax.inject.Inject;

public class HomeController extends Controller {

    private final GameService gameService;

    @Inject
    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }

    public Result watchGame(long gameId) {
        BoardView boardForRoom = gameService.getBoardForRoom(gameId);
        if (boardForRoom == null) {
            return notFound("Couldn't find game room.");
        } else {
            return ok(game.render(boardForRoom));
        }
    }
}
