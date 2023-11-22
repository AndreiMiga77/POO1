package engine;

import library.Playable;

public class Player {
    private Playable current;
    private int currentTrackId;
    private int currentTime;
    private boolean paused;
    private boolean shuffled;

    public Player() {
        paused = true;
    }

    public void tickTime(int dif) {
        if (!isLoaded())
            return;
        if (isPaused())
            return;
        if (current.getNumTracks() <= 0)
            return;
        int newTime = currentTime + dif;
        if (newTime >= current.getTrackDuration(currentTrackId)) {
            if (current.getNumTracks() == currentTrackId + 1)
                unload();
            else {
                currentTrackId++;
                currentTime = 0;
            }
        } else {
            currentTime = newTime;
        }
    }

    public boolean isLoaded() {
        return current != null;
    }

    public void load(Playable track) {
        current = track;
        currentTime = 0;
        currentTrackId = 0;
        paused = false;
    }

    public void unload() {
        current = null;
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public void resume() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public Playable getCurrentTrack() {
        return current;
    }

    public int getTimeRemaining() {
        if (current == null)
            return 0;
        return current.getTrackDuration(currentTrackId) - currentTime;
    }
}
