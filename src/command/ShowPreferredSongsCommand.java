package command;

import command.output.CommandOutput;
import command.output.ShowPreferredSongsCommandOutput;
import engine.Player;
import library.Library;
import library.Playlist;
import library.Song;
import library.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShowPreferredSongsCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        ArrayList<String> list = new ArrayList<>();
        for (Song song : user.getLikedSongs()) {
            list.add(song.getName());
        }
        return new ShowPreferredSongsCommandOutput(getUsername(), getTimestamp(), list);
    }
}