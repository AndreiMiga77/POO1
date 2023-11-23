package library;

import java.util.*;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;

public class Library {
    private static Library instance;

    public static void createLibrary(LibraryInput libInput) {
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

    public ArrayList<Playlist> getPublicPlaylists() {
        ArrayList<Playlist> playlists = new ArrayList<>();
        for (User u : users) {
            playlists.addAll(u.getPlaylists());
        }
        playlists.removeIf(Playlist::isPrivate);
        return playlists;
    }

    public void tickTime(int dif) {
        for (User u : users) {
            u.getPlayer().tickTime(dif);
        }
    }

    public User findUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    public ArrayList<Song> findSongsByFilter(Map<String, Object> filters) {
        ArrayList<Song> filteredSongs = new ArrayList<>(songs);
        if (filters.containsKey("name")) {
            String name = (String)filters.get("name");
            filteredSongs.removeIf(song -> !song.getName().startsWith(name));
        }
        if (filters.containsKey("album")) {
            String album = (String)filters.get("album");
            filteredSongs.removeIf(song -> !song.getAlbum().equals(album));
        }
        if (filters.containsKey("tags")) {
            List<String> tags = (List<String>)filters.get("tags");
            filteredSongs.removeIf(song -> !new HashSet<>(song.getTags()).containsAll(tags));
        }
        if (filters.containsKey("lyrics")) {
            String lyric = (String)filters.get("lyrics");
            filteredSongs.removeIf(song -> !song.getLyrics().toLowerCase().contains(lyric.toLowerCase()));
        }
        if (filters.containsKey("genre")) {
            String genre = (String)filters.get("genre");
            filteredSongs.removeIf(song -> song.getGenre().compareToIgnoreCase(genre) != 0);
        }
        if (filters.containsKey("releaseYear")) {
            String yearCondition = (String)filters.get("releaseYear");
            int year = Integer.parseInt(yearCondition.substring(1));
            if (yearCondition.charAt(0) == '<') {
                filteredSongs.removeIf(song -> song.getReleaseYear() >= year);
            } else {
                filteredSongs.removeIf(song -> song.getReleaseYear() <= year);
            }
        }
        if (filters.containsKey("artist")) {
            String artist = (String)filters.get("artist");
            filteredSongs.removeIf(song -> !song.getArtist().equals(artist));
        }
        return filteredSongs;
    }

    public ArrayList<Podcast> findPodcastsByFilter(Map<String, Object> filters) {
        ArrayList<Podcast> filteredPodcasts = new ArrayList<>(podcasts);
        if (filters.containsKey("name")) {
            String name = (String)filters.get("name");
            filteredPodcasts.removeIf(podcast -> !podcast.getName().startsWith(name));
        }
        if (filters.containsKey("owner")) {
            String owner = (String)filters.get("owner");
            filteredPodcasts.removeIf(podcast -> !podcast.getOwner().equals(owner));
        }
        return filteredPodcasts;
    }

    public ArrayList<Playlist> findPlaylistsByFilter(Map<String, Object> filters, User user) {
        ArrayList<Playlist> filteredPlaylists = new ArrayList<>();

        for (User u : users) {
            if (filters.containsKey("owner") && !filters.get("owner").equals(u.getUsername()))
                continue;
            ArrayList<Playlist> userPlaylists = new ArrayList<>(u.getPlaylists());
            if (!u.equals(user)) {
                userPlaylists.removeIf(Playlist::isPrivate);
            }
            if (filters.containsKey("name")) {
                String name = (String)filters.get("name");
                userPlaylists.removeIf(playlist -> !playlist.getName().startsWith(name));
            }
            filteredPlaylists.addAll(userPlaylists);
        }
//
//        if (filters.containsKey("owner")) {
//            String owner = (String)filters.get("owner");
//            ArrayList<Playlist> userPlaylist = new ArrayList<>(findUser(owner).getPlaylists());
//            userPlaylist.removeIf(Playlist::isPrivate);
//            if (filters.containsKey("name")) {
//                String name = (String)filters.get("name");
//                userPlaylist.removeIf(playlist -> !playlist.getName().startsWith(name));
//            }
//            filteredPlaylists.addAll(userPlaylist);
//        } else {
//            for (User u : users) {
//                ArrayList<Playlist> userPlaylist = new ArrayList<>(u.getPlaylists());
//                userPlaylist.removeIf(Playlist::isPrivate);
//                if (filters.containsKey("name")) {
//                    String name = (String)filters.get("name");
//                    userPlaylist.removeIf(playlist -> !playlist.getName().startsWith(name));
//                }
//                filteredPlaylists.addAll(userPlaylist);
//            }
//        }
        return filteredPlaylists;
    }
}
