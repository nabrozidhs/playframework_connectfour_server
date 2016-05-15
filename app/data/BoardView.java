package data;

public class BoardView {

    private final CellState[][] board;
    private final GameState gameState;
    private final long id;

    public BoardView(long gameId,
                     CellState[][] board,
                     GameState gameState) {
        this.id = gameId;
        this.board = board;
        this.gameState = gameState;
    }

    public CellState[][] getBoard() {
        return board.clone();
    }

    public GameState getGameState() {
        return gameState;
    }

    public CellState[][] forUI() {
        CellState[][] uiBoard = new CellState[board[0].length][board.length];

        for (int x = 0; x < uiBoard.length; x++) {
            for (int y = 0; y < uiBoard[x].length; y++) {
                uiBoard[x][y] = board[y][board[0].length - x - 1];
            }
        }

        return uiBoard;
    }

    public long getId() {
        return id;
    }
}
