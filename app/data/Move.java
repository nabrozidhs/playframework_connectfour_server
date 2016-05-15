package data;

public class Move {

    private final Player player;
    private final int column;

    // Used by jackson deserialiser.
    @SuppressWarnings("unused")
    private Move() {
        player = null;
        column = -1;
    }

    public Move(Player player, int column) {
        this.player = player;
        this.column = column;
    }

    public Player getPlayer() {
        return player;
    }

    public int getColumn() {
        return column;
    }
}
