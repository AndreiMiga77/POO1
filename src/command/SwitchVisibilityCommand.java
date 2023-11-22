package command;

import command.output.CommandOutput;

public class SwitchVisibilityCommand extends Command {
    private Integer playlistId;

    public Integer getPlaylistId() {
        return playlistId;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}