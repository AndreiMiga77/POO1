package engine;

import library.Playable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public final class Player {
    public enum RepeatState {
        NO_REPEAT,
        REPEAT_ALL,
        REPEAT_CURRENT,
        REPEAT_ONCE,
    }

    private Playable current;
    private int currentTrackIndex;
    private int currentTime;
    private boolean paused;
    private boolean shuffled;
    private HashMap<Playable, Integer> timestampsRemembered;
    private RepeatState repeatState;
    private ArrayList<Integer> trackIndexList;

    public Player() {
        paused = true;
        repeatState = RepeatState.NO_REPEAT;
        timestampsRemembered = new HashMap<>();
    }

    public void tickTime(final int difTime) {
        int dif = difTime;
        if (!isLoaded()) {
            return;
        }
        if (isPaused()) {
            return;
        }
        if (current.getNumTracks() <= 0) {
            return;
        }
        int newTime = currentTime + dif;
        int currentTrackId = trackIndexList.get(currentTrackIndex);
        if (currentTime + dif < current.getTrack(currentTrackId).getDuration()) {
            currentTime += dif;
        } else {
            dif -= current.getTrack(currentTrackId).getDuration() - currentTime;
            if (repeatState == RepeatState.REPEAT_CURRENT) {
                currentTime = 0;
                tickTime(dif);
                return;
            }
            for (int i = currentTrackIndex + 1; i < current.getNumTracks(); i++) {
                int trackId = trackIndexList.get(i);
                if (dif < current.getTrack(trackId).getDuration()) {
                    currentTime = dif;
                    currentTrackIndex = i;
                    return;
                }
                dif -= current.getTrack(i).getDuration();
            }
            if (repeatState == RepeatState.NO_REPEAT) {
                unload();
            } else if (repeatState == RepeatState.REPEAT_ALL) {
                currentTime = 0;
                currentTrackIndex = 0;
                if (dif > 0) {
                    tickTime(dif);
                }
            } else if (repeatState == RepeatState.REPEAT_ONCE) {
                currentTime = 0;
                currentTrackIndex = 0;
                repeatState = RepeatState.NO_REPEAT;
                if (dif > 0) {
                    tickTime(dif);
                }
            }
        }
    }

    public boolean isLoaded() {
        return current != null;
    }

    public void load(final Playable track) {
        if (isLoaded()) {
            unload();
        }
        shuffled = false;
        paused = false;
        current = track;
        trackIndexList = new ArrayList<>(track.getNumTracks());
        for (int i = 0; i < track.getNumTracks(); i++) {
            trackIndexList.add(i);
        }
        if (track.remembersTimestamp()) {
            if (timestampsRemembered.containsKey(track)) {
                int rememberedTime = timestampsRemembered.get(track);
                int totalTime = 0;
                for (int i = 0; i < track.getNumTracks(); i++) {
                    if (rememberedTime < totalTime + track.getTrack(i).getDuration()) {
                        currentTime = rememberedTime - totalTime;
                        currentTrackIndex = i;
                        return;
                    }
                    totalTime += track.getTrack(i).getDuration();
                }
            }
        }
        currentTime = 0;
        currentTrackIndex = 0;
    }

    public void unload() {
        if (current == null) {
            return;
        }
        int currentTrackId = trackIndexList.get(currentTrackIndex);
        if (current.remembersTimestamp()) {
            int prevTime = 0;
            for (int i = 0; i < currentTrackId; i++) {
                prevTime += current.getTrack(i).getDuration();
            }
            timestampsRemembered.put(current, prevTime + currentTime);
        }
        current = null;
        paused = true;
        shuffled = false;
        currentTime = 0;
        repeatState = RepeatState.NO_REPEAT;
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

    public void skip() {
        paused = false;
        tickTime(getTimeRemaining());
    }

    public void rewind() {
        paused = false;
        if (currentTime == 0 && currentTrackIndex > 0) {
            currentTrackIndex--;
        }
        currentTime = 0;
    }

    public void seekForward(int time) {
        if (getTimeRemaining() < time) {
            skip();
        } else {
            currentTime += time;
        }
    }

    public void seekBackward(int time) {
        if (currentTime <= time) {
            currentTime = 0;
        } else {
            currentTime -= time;
        }
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void shuffleSource(long seed) {
        Collections.shuffle(trackIndexList, new Random(seed));
        for (int i = 0; i < trackIndexList.size(); i++) {
            if (trackIndexList.get(i) == currentTrackIndex) {
                currentTrackIndex = i;
                break;
            }
        }
        shuffled = true;
    }

    public void unshuffleSource() {
        currentTrackIndex = trackIndexList.get(currentTrackIndex);
        for (int i = 0; i < trackIndexList.size(); i++) {
            trackIndexList.set(i, i);
        }
        shuffled = false;
    }

    public Playable getCurrentTrack() {
        if (current == null)
            return null;
        if (current.getNumTracks() <= 0)
            return null;
        if (currentTrackIndex < 0)
            return null;
        return current.getTrack(trackIndexList.get(currentTrackIndex));
    }

    public Playable getCurrent() {
        return current;
    }

    public int getTimeRemaining() {
        if (current == null)
            return 0;
        if (current.getNumTracks() <= 0)
            return 0;
        if (currentTrackIndex < 0)
            return 0;
        int currentTrackId = trackIndexList.get(currentTrackIndex);
        return current.getTrack(currentTrackId).getDuration() - currentTime;
    }

    public RepeatState getRepeatState() {
        return repeatState;
    }

    public void setRepeatState(RepeatState rep) {
        repeatState = rep;
    }
}
