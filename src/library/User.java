package library;

import fileio.input.UserInput;

import engine.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class User {
    private String username;
    private int age;
    private String city;
    private List<? extends Playable> lastSearch;
    private int selectedSource;
    private Player player;

    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> ownedPlaylists;
    private ArrayList<Playlist> followedPlaylists;

    public User(final UserInput input) {
        username = input.getUsername();
        age = input.getAge();
        city = input.getCity();
        selectedSource = -1;
        player = new Player();
        likedSongs = new ArrayList<>();
        ownedPlaylists = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public List<Playable> getLastSearch() {
        if (lastSearch == null) {
            return null;
        }
        return Collections.unmodifiableList(lastSearch);
    }

    public void setLastSearch(final List<? extends Playable> lastSearch) {
        this.lastSearch = lastSearch;
    }

    public int getSelectedSource() {
        return selectedSource;
    }

    public void setSelectedSource(final int selectedSource) {
        this.selectedSource = selectedSource;
    }

    public Player getPlayer() {
        return player;
    }

    public void createPlaylist(final String name, final int timestamp) {
        ownedPlaylists.add(new Playlist(name, timestamp));
    }

    public Playlist getPlaylist(final int id) {
        if (id >= ownedPlaylists.size()) {
            return null;
        }
        return ownedPlaylists.get(id);
    }

    public Playlist getPlaylistByName(final String name) {
        Stream<Playlist> stream = ownedPlaylists.stream();
        return stream.filter(playlist -> playlist.getName().equals(name)).findAny().orElse(null);
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(ownedPlaylists);
    }

    public boolean hasLikedSong(final Song song) {
        return likedSongs.contains(song);
    }

    public void likeSong(final Song song) {
        if (!likedSongs.contains(song)) {
            likedSongs.add(song);
            song.addLike();
        }
    }

    public void unlikeSong(final Song song) {
        if (likedSongs.remove(song)) {
            song.removeLike();
        }

    }

    public List<Song> getLikedSongs() {
        return Collections.unmodifiableList(likedSongs);
    }

    public boolean hasFollowedPlaylist(final Playlist playlist) {
        return followedPlaylists.contains(playlist);
    }

    public  void followPlaylist(final Playlist playlist) {
        if (!followedPlaylists.contains(playlist)) {
            followedPlaylists.add(playlist);
            playlist.addFollower();
        }
    }

    public void unfollowPlaylist(final Playlist playlist) {
        if (followedPlaylists.remove(playlist)) {
            playlist.removeFollower();
        }
    }
}
