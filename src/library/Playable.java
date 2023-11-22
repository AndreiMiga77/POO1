package library;

public interface Playable {
    String getName();
    int getDuration();
    int getNumTracks();
    int getTrackDuration(int i);
    boolean allowsShuffling();
    boolean remembersTimestamp();
}
