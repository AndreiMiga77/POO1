package command;

import command.Command;
import command.output.CommandOutput;

public class AddRemoveInPlaylistCommand extends Command {
    private Integer playlistId;

    public Integer getPlaylistId() {
        return playlistId;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}