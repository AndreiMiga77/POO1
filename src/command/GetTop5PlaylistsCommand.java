package command;

import command.output.CommandOutput;
import command.output.GetTop5PlaylistsCommandOutput;
import command.output.GetTop5SongsCommandOutput;
import library.Library;
import library.Playlist;
import library.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetTop5PlaylistsCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        ArrayList<Playlist> playlists = library.getPublicPlaylists();
        ArrayList<String> playlistNames = new ArrayList<>(5);
        playlists.sort((p1, p2) -> {
            if (p1.getFollowers() > p2.getFollowers()) {
                return -1;
            } else if (p1.getFollowers() < p2.getFollowers()) {
                return 1;
            } else {
                if (p1.getCreationTime() < p2.getCreationTime()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < Math.min(5, playlists.size()); i++) {
            playlistNames.add(playlists.get(i).getName());
        }
        return new GetTop5PlaylistsCommandOutput(getTimestamp(), playlistNames);
    }
}