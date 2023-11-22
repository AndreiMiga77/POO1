package command;

import command.output.CommandOutput;

public class CreatePlaylistCommand extends Command {
    private String playlistName;

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}