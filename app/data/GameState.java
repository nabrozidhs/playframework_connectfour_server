package data;

public enum GameState {
    DRAW,
    RED_WIN,
    YELLOW_WIN,
    RED_PLAYING,
    YELLOW_PLAYING;

    public boolean stillPlaying() {
        return this == RED_PLAYING || this == YELLOW_PLAYING;
    }
}
