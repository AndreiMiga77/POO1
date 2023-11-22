package library;

import java.util.*;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;

public class Library {
    private static Library instance;

    public static void createLibrary(LibraryInput libInput) {
        if (instance == null)
            instance = new Library(libInput);
    }

    public static Library getInstance() {
        return instance;
    }

    private ArrayList<User> users;
    private ArrayList<Song> songs;

    private ArrayList<Podcast> podcasts;

    private Library(LibraryInput libInput) {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        for (UserInput user : libInput.getUsers()) {
            users.add(new User(user));
        }
        for (SongInput song : libInput.getSongs()) {
            songs.add(new Song(song));
        }
        for (PodcastInput podcast : libInput.getPodcasts()) {
            podcasts.add(new Podcast(podcast));
        }
        instance = this;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }

    public User findUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    public ArrayList<Song> findSongsByFilter(Map<String, Object> filters) {
        ArrayList<Song> filtered_songs = new ArrayList<>(songs);
        if (filters.containsKey("name")) {
            String name = (String)filters.get("name");
            filtered_songs.removeIf(song -> !song.getName().startsWith(name));
        }
        if (filters.containsKey("album")) {
            String album = (String)filters.get("album");
            filtered_songs.removeIf(song -> !song.getAlbum().equals(album));
        }
        if (filters.containsKey("tags")) {
            List<String> tags = (List<String>)filters.get("tags");
            filtered_songs.removeIf(song -> !new HashSet<>(song.getTags()).containsAll(tags));
        }
        if (filters.containsKey("lyrics")) {
            String lyric = (String)filters.get("lyrics");
            filtered_songs.removeIf(song -> !song.getLyrics().contains(lyric));
        }
        if (filters.containsKey("genre")) {
            String genre = (String)filters.get("genre");
            filtered_songs.removeIf(song -> song.getGenre().compareToIgnoreCase(genre) != 0);
        }
        if (filters.containsKey("releaseYear")) {
            String yearCondition = (String)filters.get("releaseYear");
            int year = Integer.parseInt(yearCondition.substring(1));
            if (yearCondition.charAt(0) == '<') {
                filtered_songs.removeIf(song -> song.getReleaseYear() >= year);
            } else {
                filtered_songs.removeIf(song -> song.getReleaseYear() <= year);
            }
        }
        if (filters.containsKey("artist")) {
            String artist = (String)filters.get("artist");
            filtered_songs.removeIf(song -> !song.getArtist().equals(artist));
        }
        return filtered_songs;
    }

    public ArrayList<Podcast> findPodcastsByFilter(Map<String, Object> filters) {
        ArrayList<Podcast> filtered_podcasts = new ArrayList<>(podcasts);
        if (filters.containsKey("name")) {
            String name = (String)filters.get("name");
            filtered_podcasts.removeIf(podcast -> !podcast.getName().startsWith(name));
        }
        if (filters.containsKey("owner")) {
            String owner = (String)filters.get("owner");
            filtered_podcasts.removeIf(podcast -> !podcast.getOwner().equals(owner));
        }
        return filtered_podcasts;
    }
}
