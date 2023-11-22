package library;

import java.util.ArrayList;
import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;

public class Library {
    private ArrayList<User> users;
    private ArrayList<Song> songs;

    private ArrayList<Podcast> podcasts;

    public Library(LibraryInput libInput) {
        ArrayList<UserInput> userInputs = libInput.getUsers();
        ArrayList<SongInput> songInputs = libInput.getSongs();
        ArrayList<PodcastInput> podcastInputs = libInput.getPodcasts();

        users = new ArrayList<>();
        for (UserInput user : libInput.getUsers()) {
            users.add(new User(user));
        }

        songs = new ArrayList<>();
        for (SongInput song : libInput.getSongs()) {
            songs.add(new Song(song));
        }

        podcasts = new ArrayList<>();
        for (PodcastInput podcast : libInput.getPodcasts()) {
            podcasts.add(new Podcast(podcast));
        }
    }
}
