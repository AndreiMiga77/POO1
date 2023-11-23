package engine;

import library.Playable;
import library.Playlist;

import java.util.HashMap;

public class Player {
    private Playable current;
    private int currentTrackId;
    private int currentTime;
    private boolean paused;
    private boolean shuffled;
    private HashMap<Playable, Integer> timestampsRemembered;

    public Player() {
        paused = true;
        timestampsRemembered = new HashMap<>();
    }

    public void tickTime(int dif) {
        if (!isLoaded())
            return;
        if (isPaused())
            return;
        if (current.getNumTracks() <= 0)
            return;
        int newTime = currentTime + dif;
        if (newTime >= current.getTrack(currentTrackId).getDuration()) {
            if (current.getNumTracks() == currentTrackId + 1) {
                unload();
            } else {
                dif -= getTimeRemaining();
                currentTrackId++;
                currentTime = 0;
                if (current.getNumTracks() == currentTrackId) {
                    unload();
                    return;
                }
                while (dif >= current.getTrack(currentTrackId).getDuration()) {
                    dif -= current.getTrack(currentTrackId).getDuration();
                    currentTrackId++;
                    if (current.getNumTracks() == currentTrackId) {
                        unload();
                        return;
                    }
                }
                currentTime = dif;
            }
        } else {
            currentTime = newTime;
        }
    }

    public boolean isLoaded() {
        return current != null;
    }

    public void load(Playable track) {
        if (isLoaded())
            unload();
        current = track;
        if (track.remembersTimestamp()) {
            if (timestampsRemembered.containsKey(track)) {
                int rememberedTime = timestampsRemembered.get(track);
                int totalTime = 0;
                for (int i = 0; i < track.getNumTracks(); i++) {
                    if (rememberedTime < totalTime + track.getTrack(i).getDuration()) {
                        currentTime = rememberedTime - totalTime;
                        currentTrackId = i;
                        paused = false;
                        return;
                    }
                    totalTime += track.getTrack(i).getDuration();
                }
            }
        }
        currentTime = 0;
        currentTrackId = 0;
        paused = false;
    }

    public void unload() {
        if (current == null)
            return;
        if (current.remembersTimestamp()) {
            int prevTime = 0;
            for (int i = 0; i < currentTrackId; i++)
                prevTime += current.getTrack(i).getDuration();
            timestampsRemembered.put(current, prevTime + currentTime);
        }
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
        if (current == null)
            return null;
        if (current.getNumTracks() <= 0)
            return null;
        if (currentTrackId < 0)
            return null;
        return current.getTrack(currentTrackId);
    }

    public Playable getCurrent() {
        return current;
    }

    public int getTimeRemaining() {
        if (current == null)
            return 0;
        if (current.getNumTracks() <= 0)
            return 0;
        return current.getTrack(currentTrackId).getDuration() - currentTime;
    }
}
