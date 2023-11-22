package command;

import command.output.CommandOutput;
import command.output.SearchCommandOutput;
import library.Library;
import library.Podcast;
import library.Song;
import library.User;

import java.util.ArrayList;
import java.util.Map;

public class SearchCommand extends Command {
    private String type;
    private Map<String, Object> filters;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        ArrayList<String> names = new ArrayList<>(5);
        String message = null;
        if (type.equals("song")) {
            ArrayList<Song> songs = library.findSongsByFilter(filters);
            int search_size = Math.min(songs.size(), 5);
            for (Song song : songs.subList(0, search_size))
                names.add(song.getName());
            user.setLastSearch(songs.subList(0, search_size));
            message = "Search returned " + search_size + " results";
        } else if (type.equals("podcast")) {
            ArrayList<Podcast> podcasts = library.findPodcastsByFilter(filters);
            int search_size = Math.min(podcasts.size(), 5);
            for (Podcast podcast : podcasts.subList(0, search_size))
                names.add(podcast.getName());
            user.setLastSearch(podcasts.subList(0, search_size));
            message = "Search returned " + search_size + " results";
        }
        return new SearchCommandOutput(getUsername(), getTimestamp(), message, names);
    }
}
