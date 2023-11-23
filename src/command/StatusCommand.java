package command;

import command.output.CommandOutput;
import command.output.StatusCommandOutput;
import engine.Player;
import library.Library;
import library.User;

import java.util.LinkedHashMap;

public class StatusCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        User user = library.findUser(getUsername());
        Player player = user.getPlayer();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (player.getCurrent() == null)
            map.put("name", "");
        else
            map.put("name", player.getCurrent().getName());
        map.put("remainedTime", player.getTimeRemaining());
        map.put("repeat", "No Repeat");
        map.put("shuffle", player.isShuffled());
        map.put("paused", player.isPaused());

        return new StatusCommandOutput(getUsername(), getTimestamp(), map);
    }
}