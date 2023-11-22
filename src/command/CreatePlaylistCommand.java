package command;

import command.output.CommandOutput;
import command.output.CreatePlaylistCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public class CreatePlaylistCommand extends Command {
    private String playlistName;

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        String message;
        if (user.getPlaylistByName(playlistName) != null)
            message = "A playlist with the same name already exists.";
        else {
            user.createPlaylist(playlistName);
            message = "Playlist created successfully.";
        }
        return new CreatePlaylistCommandOutput(getUsername(), getTimestamp(), message);
    }
}