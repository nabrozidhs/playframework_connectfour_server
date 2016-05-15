package data;

public class Board {

    private final CellState[][] board = new CellState[7][6];

    public Board() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = CellState.NONE;
            }
        }
    }

    public CellState[][] getBoard() {
        return board.clone();
    }
}
