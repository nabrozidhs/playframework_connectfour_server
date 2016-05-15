package services;

import data.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class GameService {

    private final Counter counter;
    private final ConcurrentHashMap<Long, BoardView> rooms = new ConcurrentHashMap<>();

    @Inject
    public GameService(Counter counter) {
        this.counter = counter;
    }

    public BoardView newGame() {
        long gameId = counter.nextCount();

        BoardView initialBoardView = new BoardView(
                gameId,
                new Board().getBoard(),
                GameState.RED_PLAYING);
        rooms.put(gameId, initialBoardView);
        return initialBoardView;
    }

    @Nullable
    public BoardView getBoardForRoom(long roomId) {
        return rooms.get(roomId);
    }

    @Nullable
    public BoardView move(long roomId, Move move) {
        BoardView boardForRoom = getBoardForRoom(roomId);

        if (boardForRoom == null) {
            return null;
        }

        if (checkValidMove(boardForRoom, move)) {
            BoardView newBoardState = insertMove(boardForRoom, move);
            rooms.put(roomId, newBoardState);
            return newBoardState;
        } else {
            return null;
        }
    }

    @Nonnull private BoardView insertMove(BoardView boardForRoom, Move move) {
        if (!boardForRoom.getGameState().stillPlaying()) {
            return boardForRoom;
        }

        CellState[][] board = boardForRoom.getBoard();

        CellState[] row = board[move.getColumn()];
        for (int y = 0; y <= row.length; y++) {
            if (row[y] == CellState.NONE) {
                row[y] = move.getPlayer() == Player.RED ?
                        CellState.RED :
                        CellState.YELLOW;
                break;
            }
        }

        return new BoardView(
                boardForRoom.getId(),
                board,
                updateGameState(boardForRoom.getGameState(), board));
    }

    private GameState updateGameState(GameState currentState, CellState[][] board) {
        Player winner = getWinner(board);

        if (winner != null) {
            return winner == Player.RED ?
                    GameState.RED_WIN :
                    GameState.YELLOW_WIN;
        } else {
            if (boardIsFull(board)) {
                return GameState.DRAW;
            } else {
                return currentState == GameState.RED_PLAYING ?
                        GameState.YELLOW_PLAYING :
                        GameState.RED_PLAYING;
            }
        }
    }

    private boolean boardIsFull(CellState[][] board) {
        for (CellState[] col : board) {
            if (col[col.length - 1] == CellState.NONE) {
                return false;
            }
        }

        return true;
    }

    private Player getWinner(CellState[][] board) {
        if (board[0][0] == CellState.YELLOW && board[1][0] == CellState.YELLOW && board[2][0] == CellState.YELLOW && board[3][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][0] == CellState.RED && board[1][0] == CellState.RED && board[2][0] == CellState.RED && board[3][0] == CellState.RED){return Player.RED;}
        if (board[1][0] == CellState.YELLOW && board[2][0] == CellState.YELLOW && board[3][0] == CellState.YELLOW && board[4][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][0] == CellState.RED && board[2][0] == CellState.RED && board[3][0] == CellState.RED && board[4][0] == CellState.RED){return Player.RED;}
        if (board[2][0] == CellState.YELLOW && board[3][0] == CellState.YELLOW && board[4][0] == CellState.YELLOW && board[5][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][0] == CellState.RED && board[3][0] == CellState.RED && board[4][0] == CellState.RED && board[5][0] == CellState.RED){return Player.RED;}
        if (board[3][0] == CellState.YELLOW && board[4][0] == CellState.YELLOW && board[5][0] == CellState.YELLOW && board[6][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][0] == CellState.RED && board[4][0] == CellState.RED && board[5][0] == CellState.RED && board[6][0] == CellState.RED){return Player.RED;}
        if (board[0][1] == CellState.YELLOW && board[1][1] == CellState.YELLOW && board[2][1] == CellState.YELLOW && board[3][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][1] == CellState.RED && board[1][1] == CellState.RED && board[2][1] == CellState.RED && board[3][1] == CellState.RED){return Player.RED;}
        if (board[1][1] == CellState.YELLOW && board[2][1] == CellState.YELLOW && board[3][1] == CellState.YELLOW && board[4][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][1] == CellState.RED && board[2][1] == CellState.RED && board[3][1] == CellState.RED && board[4][1] == CellState.RED){return Player.RED;}
        if (board[2][1] == CellState.YELLOW && board[3][1] == CellState.YELLOW && board[4][1] == CellState.YELLOW && board[5][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][1] == CellState.RED && board[3][1] == CellState.RED && board[4][1] == CellState.RED && board[5][1] == CellState.RED){return Player.RED;}
        if (board[3][1] == CellState.YELLOW && board[4][1] == CellState.YELLOW && board[5][1] == CellState.YELLOW && board[6][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][1] == CellState.RED && board[4][1] == CellState.RED && board[5][1] == CellState.RED && board[6][1] == CellState.RED){return Player.RED;}
        if (board[0][2] == CellState.YELLOW && board[1][2] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[3][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][2] == CellState.RED && board[1][2] == CellState.RED && board[2][2] == CellState.RED && board[3][2] == CellState.RED){return Player.RED;}
        if (board[1][2] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[4][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][2] == CellState.RED && board[2][2] == CellState.RED && board[3][2] == CellState.RED && board[4][2] == CellState.RED){return Player.RED;}
        if (board[2][2] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[5][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][2] == CellState.RED && board[3][2] == CellState.RED && board[4][2] == CellState.RED && board[5][2] == CellState.RED){return Player.RED;}
        if (board[3][2] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[5][2] == CellState.YELLOW && board[6][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][2] == CellState.RED && board[4][2] == CellState.RED && board[5][2] == CellState.RED && board[6][2] == CellState.RED){return Player.RED;}
        if (board[0][3] == CellState.YELLOW && board[1][3] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[3][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][3] == CellState.RED && board[1][3] == CellState.RED && board[2][3] == CellState.RED && board[3][3] == CellState.RED){return Player.RED;}
        if (board[1][3] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[4][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][3] == CellState.RED && board[2][3] == CellState.RED && board[3][3] == CellState.RED && board[4][3] == CellState.RED){return Player.RED;}
        if (board[2][3] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[5][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][3] == CellState.RED && board[3][3] == CellState.RED && board[4][3] == CellState.RED && board[5][3] == CellState.RED){return Player.RED;}
        if (board[3][3] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[5][3] == CellState.YELLOW && board[6][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][3] == CellState.RED && board[4][3] == CellState.RED && board[5][3] == CellState.RED && board[6][3] == CellState.RED){return Player.RED;}
        if (board[0][4] == CellState.YELLOW && board[1][4] == CellState.YELLOW && board[2][4] == CellState.YELLOW && board[3][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][4] == CellState.RED && board[1][4] == CellState.RED && board[2][4] == CellState.RED && board[3][4] == CellState.RED){return Player.RED;}
        if (board[1][4] == CellState.YELLOW && board[2][4] == CellState.YELLOW && board[3][4] == CellState.YELLOW && board[4][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][4] == CellState.RED && board[2][4] == CellState.RED && board[3][4] == CellState.RED && board[4][4] == CellState.RED){return Player.RED;}
        if (board[2][4] == CellState.YELLOW && board[3][4] == CellState.YELLOW && board[4][4] == CellState.YELLOW && board[5][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][4] == CellState.RED && board[3][4] == CellState.RED && board[4][4] == CellState.RED && board[5][4] == CellState.RED){return Player.RED;}
        if (board[3][4] == CellState.YELLOW && board[4][4] == CellState.YELLOW && board[5][4] == CellState.YELLOW && board[6][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][4] == CellState.RED && board[4][4] == CellState.RED && board[5][4] == CellState.RED && board[6][4] == CellState.RED){return Player.RED;}
        if (board[0][5] == CellState.YELLOW && board[1][5] == CellState.YELLOW && board[2][5] == CellState.YELLOW && board[3][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][5] == CellState.RED && board[1][5] == CellState.RED && board[2][5] == CellState.RED && board[3][5] == CellState.RED){return Player.RED;}
        if (board[1][5] == CellState.YELLOW && board[2][5] == CellState.YELLOW && board[3][5] == CellState.YELLOW && board[4][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][5] == CellState.RED && board[2][5] == CellState.RED && board[3][5] == CellState.RED && board[4][5] == CellState.RED){return Player.RED;}
        if (board[2][5] == CellState.YELLOW && board[3][5] == CellState.YELLOW && board[4][5] == CellState.YELLOW && board[5][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][5] == CellState.RED && board[3][5] == CellState.RED && board[4][5] == CellState.RED && board[5][5] == CellState.RED){return Player.RED;}
        if (board[3][5] == CellState.YELLOW && board[4][5] == CellState.YELLOW && board[5][5] == CellState.YELLOW && board[6][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][5] == CellState.RED && board[4][5] == CellState.RED && board[5][5] == CellState.RED && board[6][5] == CellState.RED){return Player.RED;}
        if (board[0][0] == CellState.YELLOW && board[0][1] == CellState.YELLOW && board[0][2] == CellState.YELLOW && board[0][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][0] == CellState.RED && board[0][1] == CellState.RED && board[0][2] == CellState.RED && board[0][3] == CellState.RED){return Player.RED;}
        if (board[0][1] == CellState.YELLOW && board[0][2] == CellState.YELLOW && board[0][3] == CellState.YELLOW && board[0][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][1] == CellState.RED && board[0][2] == CellState.RED && board[0][3] == CellState.RED && board[0][4] == CellState.RED){return Player.RED;}
        if (board[0][2] == CellState.YELLOW && board[0][3] == CellState.YELLOW && board[0][4] == CellState.YELLOW && board[0][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][2] == CellState.RED && board[0][3] == CellState.RED && board[0][4] == CellState.RED && board[0][5] == CellState.RED){return Player.RED;}
        if (board[1][0] == CellState.YELLOW && board[1][1] == CellState.YELLOW && board[1][2] == CellState.YELLOW && board[1][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][0] == CellState.RED && board[1][1] == CellState.RED && board[1][2] == CellState.RED && board[1][3] == CellState.RED){return Player.RED;}
        if (board[1][1] == CellState.YELLOW && board[1][2] == CellState.YELLOW && board[1][3] == CellState.YELLOW && board[1][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][1] == CellState.RED && board[1][2] == CellState.RED && board[1][3] == CellState.RED && board[1][4] == CellState.RED){return Player.RED;}
        if (board[1][2] == CellState.YELLOW && board[1][3] == CellState.YELLOW && board[1][4] == CellState.YELLOW && board[1][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][2] == CellState.RED && board[1][3] == CellState.RED && board[1][4] == CellState.RED && board[1][5] == CellState.RED){return Player.RED;}
        if (board[2][0] == CellState.YELLOW && board[2][1] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[2][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][0] == CellState.RED && board[2][1] == CellState.RED && board[2][2] == CellState.RED && board[2][3] == CellState.RED){return Player.RED;}
        if (board[2][1] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[2][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][1] == CellState.RED && board[2][2] == CellState.RED && board[2][3] == CellState.RED && board[2][4] == CellState.RED){return Player.RED;}
        if (board[2][2] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[2][4] == CellState.YELLOW && board[2][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][2] == CellState.RED && board[2][3] == CellState.RED && board[2][4] == CellState.RED && board[2][5] == CellState.RED){return Player.RED;}
        if (board[3][0] == CellState.YELLOW && board[3][1] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[3][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][0] == CellState.RED && board[3][1] == CellState.RED && board[3][2] == CellState.RED && board[3][3] == CellState.RED){return Player.RED;}
        if (board[3][1] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[3][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][1] == CellState.RED && board[3][2] == CellState.RED && board[3][3] == CellState.RED && board[3][4] == CellState.RED){return Player.RED;}
        if (board[3][2] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[3][4] == CellState.YELLOW && board[3][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][2] == CellState.RED && board[3][3] == CellState.RED && board[3][4] == CellState.RED && board[3][5] == CellState.RED){return Player.RED;}
        if (board[4][0] == CellState.YELLOW && board[4][1] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[4][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[4][0] == CellState.RED && board[4][1] == CellState.RED && board[4][2] == CellState.RED && board[4][3] == CellState.RED){return Player.RED;}
        if (board[4][1] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[4][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[4][1] == CellState.RED && board[4][2] == CellState.RED && board[4][3] == CellState.RED && board[4][4] == CellState.RED){return Player.RED;}
        if (board[4][2] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[4][4] == CellState.YELLOW && board[4][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[4][2] == CellState.RED && board[4][3] == CellState.RED && board[4][4] == CellState.RED && board[4][5] == CellState.RED){return Player.RED;}
        if (board[5][0] == CellState.YELLOW && board[5][1] == CellState.YELLOW && board[5][2] == CellState.YELLOW && board[5][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[5][0] == CellState.RED && board[5][1] == CellState.RED && board[5][2] == CellState.RED && board[5][3] == CellState.RED){return Player.RED;}
        if (board[5][1] == CellState.YELLOW && board[5][2] == CellState.YELLOW && board[5][3] == CellState.YELLOW && board[5][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[5][1] == CellState.RED && board[5][2] == CellState.RED && board[5][3] == CellState.RED && board[5][4] == CellState.RED){return Player.RED;}
        if (board[5][2] == CellState.YELLOW && board[5][3] == CellState.YELLOW && board[5][4] == CellState.YELLOW && board[5][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[5][2] == CellState.RED && board[5][3] == CellState.RED && board[5][4] == CellState.RED && board[5][5] == CellState.RED){return Player.RED;}
        if (board[6][0] == CellState.YELLOW && board[6][1] == CellState.YELLOW && board[6][2] == CellState.YELLOW && board[6][3] == CellState.YELLOW){return Player.YELLOW;}
        if (board[6][0] == CellState.RED && board[6][1] == CellState.RED && board[6][2] == CellState.RED && board[6][3] == CellState.RED){return Player.RED;}
        if (board[6][1] == CellState.YELLOW && board[6][2] == CellState.YELLOW && board[6][3] == CellState.YELLOW && board[6][4] == CellState.YELLOW){return Player.YELLOW;}
        if (board[6][1] == CellState.RED && board[6][2] == CellState.RED && board[6][3] == CellState.RED && board[6][4] == CellState.RED){return Player.RED;}
        if (board[6][2] == CellState.YELLOW && board[6][3] == CellState.YELLOW && board[6][4] == CellState.YELLOW && board[6][5] == CellState.YELLOW){return Player.YELLOW;}
        if (board[6][2] == CellState.RED && board[6][3] == CellState.RED && board[6][4] == CellState.RED && board[6][5] == CellState.RED){return Player.RED;}
        if (board[0][3] == CellState.YELLOW && board[1][2] == CellState.YELLOW && board[2][1] == CellState.YELLOW && board[3][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][3] == CellState.RED && board[1][2] == CellState.RED && board[2][1] == CellState.RED && board[3][0] == CellState.RED){return Player.RED;}
        if (board[3][3] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[1][1] == CellState.YELLOW && board[0][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][3] == CellState.RED && board[2][2] == CellState.RED && board[1][1] == CellState.RED && board[0][0] == CellState.RED){return Player.RED;}
        if (board[0][4] == CellState.YELLOW && board[1][3] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[3][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][4] == CellState.RED && board[1][3] == CellState.RED && board[2][2] == CellState.RED && board[3][1] == CellState.RED){return Player.RED;}
        if (board[3][4] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[1][2] == CellState.YELLOW && board[0][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][4] == CellState.RED && board[2][3] == CellState.RED && board[1][2] == CellState.RED && board[0][1] == CellState.RED){return Player.RED;}
        if (board[0][5] == CellState.YELLOW && board[1][4] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[3][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[0][5] == CellState.RED && board[1][4] == CellState.RED && board[2][3] == CellState.RED && board[3][2] == CellState.RED){return Player.RED;}
        if (board[3][5] == CellState.YELLOW && board[2][4] == CellState.YELLOW && board[1][3] == CellState.YELLOW && board[0][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][5] == CellState.RED && board[2][4] == CellState.RED && board[1][3] == CellState.RED && board[0][2] == CellState.RED){return Player.RED;}
        if (board[1][3] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[3][1] == CellState.YELLOW && board[4][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][3] == CellState.RED && board[2][2] == CellState.RED && board[3][1] == CellState.RED && board[4][0] == CellState.RED){return Player.RED;}
        if (board[4][3] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[2][1] == CellState.YELLOW && board[1][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[4][3] == CellState.RED && board[3][2] == CellState.RED && board[2][1] == CellState.RED && board[1][0] == CellState.RED){return Player.RED;}
        if (board[1][4] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[4][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][4] == CellState.RED && board[2][3] == CellState.RED && board[3][2] == CellState.RED && board[4][1] == CellState.RED){return Player.RED;}
        if (board[4][4] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[2][2] == CellState.YELLOW && board[1][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[4][4] == CellState.RED && board[3][3] == CellState.RED && board[2][2] == CellState.RED && board[1][1] == CellState.RED){return Player.RED;}
        if (board[1][5] == CellState.YELLOW && board[2][4] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[4][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[1][5] == CellState.RED && board[2][4] == CellState.RED && board[3][3] == CellState.RED && board[4][2] == CellState.RED){return Player.RED;}
        if (board[4][5] == CellState.YELLOW && board[3][4] == CellState.YELLOW && board[2][3] == CellState.YELLOW && board[1][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[4][5] == CellState.RED && board[3][4] == CellState.RED && board[2][3] == CellState.RED && board[1][2] == CellState.RED){return Player.RED;}
        if (board[2][3] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[4][1] == CellState.YELLOW && board[5][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][3] == CellState.RED && board[3][2] == CellState.RED && board[4][1] == CellState.RED && board[5][0] == CellState.RED){return Player.RED;}
        if (board[5][3] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[3][1] == CellState.YELLOW && board[2][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[5][3] == CellState.RED && board[4][2] == CellState.RED && board[3][1] == CellState.RED && board[2][0] == CellState.RED){return Player.RED;}
        if (board[2][4] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[5][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][4] == CellState.RED && board[3][3] == CellState.RED && board[4][2] == CellState.RED && board[5][1] == CellState.RED){return Player.RED;}
        if (board[5][4] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[3][2] == CellState.YELLOW && board[2][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[5][4] == CellState.RED && board[4][3] == CellState.RED && board[3][2] == CellState.RED && board[2][1] == CellState.RED){return Player.RED;}
        if (board[2][5] == CellState.YELLOW && board[3][4] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[5][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[2][5] == CellState.RED && board[3][4] == CellState.RED && board[4][3] == CellState.RED && board[5][2] == CellState.RED){return Player.RED;}
        if (board[5][5] == CellState.YELLOW && board[4][4] == CellState.YELLOW && board[3][3] == CellState.YELLOW && board[2][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[5][5] == CellState.RED && board[4][4] == CellState.RED && board[3][3] == CellState.RED && board[2][2] == CellState.RED){return Player.RED;}
        if (board[3][3] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[5][1] == CellState.YELLOW && board[6][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][3] == CellState.RED && board[4][2] == CellState.RED && board[5][1] == CellState.RED && board[6][0] == CellState.RED){return Player.RED;}
        if (board[6][3] == CellState.YELLOW && board[5][2] == CellState.YELLOW && board[4][1] == CellState.YELLOW && board[3][0] == CellState.YELLOW){return Player.YELLOW;}
        if (board[6][3] == CellState.RED && board[5][2] == CellState.RED && board[4][1] == CellState.RED && board[3][0] == CellState.RED){return Player.RED;}
        if (board[3][4] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[5][2] == CellState.YELLOW && board[6][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][4] == CellState.RED && board[4][3] == CellState.RED && board[5][2] == CellState.RED && board[6][1] == CellState.RED){return Player.RED;}
        if (board[6][4] == CellState.YELLOW && board[5][3] == CellState.YELLOW && board[4][2] == CellState.YELLOW && board[3][1] == CellState.YELLOW){return Player.YELLOW;}
        if (board[6][4] == CellState.RED && board[5][3] == CellState.RED && board[4][2] == CellState.RED && board[3][1] == CellState.RED){return Player.RED;}
        if (board[3][5] == CellState.YELLOW && board[4][4] == CellState.YELLOW && board[5][3] == CellState.YELLOW && board[6][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[3][5] == CellState.RED && board[4][4] == CellState.RED && board[5][3] == CellState.RED && board[6][2] == CellState.RED){return Player.RED;}
        if (board[6][5] == CellState.YELLOW && board[5][4] == CellState.YELLOW && board[4][3] == CellState.YELLOW && board[3][2] == CellState.YELLOW){return Player.YELLOW;}
        if (board[6][5] == CellState.RED && board[5][4] == CellState.RED && board[4][3] == CellState.RED && board[3][2] == CellState.RED){return Player.RED;}
        return null;
    } 

    private boolean checkValidMove(BoardView boardView,
                                   Move move) {
        CellState[][] board = boardView.getBoard();
        return playerTurnFromGameState(boardView.getGameState()) == move.getPlayer() &&
                move.getColumn() < board.length &&
                move.getColumn() >= 0 &&
                board[move.getColumn()][board[move.getColumn()].length - 1] == CellState.NONE;
    }

    private Player playerTurnFromGameState(GameState gameState) {
        return gameState == GameState.RED_PLAYING ?
                Player.RED :
                Player.YELLOW;
    }
}
