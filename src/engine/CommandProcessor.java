package engine;

import command.Command;
import command.output.CommandOutput;

public class CommandProcessor {
    private static CommandProcessor instance;
    public static CommandProcessor getInstance() {
        // This implementation is not thread-safe
        // But can easily be made so if needed
        if (instance == null)
            instance = new CommandProcessor();
        return instance;
    }

    private CommandProcessor() {
    }

    public CommandOutput execute(Command cmd) {
        int timestamp = cmd.getTimestamp();
        // TODO: player.tickTime(timestamp);
        return cmd.execute();
    }
}
