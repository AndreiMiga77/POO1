package library;

public interface Playable {
    String getName();
    int getDuration();
    int getNumTracks();
    Playable getTrack(int i);
    boolean allowsLike();
    boolean allowsFollow();
    boolean allowsShuffling();
    boolean remembersTimestamp();
    boolean isPlaylistRepeatable();
}
