package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist implements Playable {
    private String name;
    private ArrayList<Song> songs;
    private boolean isPrivate;
    private int followers;

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setVisibility(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public int getFollowers() {
        return followers;
    }

    public void addFollower() {
        followers++;
    }

    public void removeFollower() {
        followers--;
    }

    @Override
    public int getDuration() {
        int duration = 0;
        for (Song song : songs) {
            duration += song.getDuration();
        }
        return duration;
    }

    @Override
    public int getNumTracks() {
        return songs.size();
    }

    @Override
    public Playable getTrack(int i) {
        return songs.get(i);
    }

    @Override
    public boolean allowsLike() {
        return false;
    }

    @Override
    public boolean allowsFollow() {
        return true;
    }

    @Override
    public boolean allowsShuffling() {
        return true;
    }

    @Override
    public boolean remembersTimestamp() {
        return false;
    }
}
