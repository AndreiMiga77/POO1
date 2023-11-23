package library;

import fileio.input.UserInput;

import engine.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String username;
    private int age;
    private String city;
    private List<? extends Playable> lastSearch;
    private int selectedSource;
    private Player player;

    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> ownedPlaylists;
    private ArrayList<Playlist> followedPlaylists;

    public User(UserInput input) {
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
        return Collections.unmodifiableList(lastSearch);
    }

    public void setLastSearch(List<? extends Playable> lastSearch) {
        this.lastSearch = new ArrayList<>(lastSearch);
    }

    public int getSelectedSource() {
        return selectedSource;
    }

    public void setSelectedSource(int selectedSource) {
        this.selectedSource = selectedSource;
    }

    public Player getPlayer() {
        return player;
    }

    public void createPlaylist(String name) {
        ownedPlaylists.add(new Playlist(name));
    }

    public Playlist getPlaylist(int id) {
        if (id >= ownedPlaylists.size())
            return null;
        return ownedPlaylists.get(id);
    }

    public Playlist getPlaylistByName(String name) {
        return ownedPlaylists.stream().filter(playlist -> playlist.getName().equals(name)).findAny().orElse(null);
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(ownedPlaylists);
    }

    public boolean hasLikedSong(Song song) {
        return likedSongs.contains(song);
    }

    public void likeSong(Song song) {
        if (!likedSongs.contains(song))
            likedSongs.add(song);
    }

    public void unlikeSong(Song song) {
        likedSongs.remove(song);
    }

    public List<Song> getLikedSongs() {
        return Collections.unmodifiableList(likedSongs);
    }
}
