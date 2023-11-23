package command;

import command.output.CommandOutput;
import command.output.ForwardCommandOutput;
import engine.Player;
import library.Library;
import library.User;

public class ForwardCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();
        String message = null;
        if (!player.isLoaded()) {
            message = "Please load a source before attempting to forward.";
        } else if (!player.getCurrent().isSeekable()) {
            message = "The loaded source is not a podcast.";
        } else {
            player.seekForward(90);
            message = "Skipped forward successfully.";
        }
        return new ForwardCommandOutput(getUsername(), getTimestamp(), message);
    }
}