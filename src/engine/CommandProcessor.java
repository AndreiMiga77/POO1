package engine;

import command.Command;
import command.output.CommandOutput;
import library.Library;

public class CommandProcessor {
    private static CommandProcessor instance;
    public static CommandProcessor getInstance() {
        // This implementation is not thread-safe
        // But can easily be made so if needed
        if (instance == null)
            instance = new CommandProcessor();
        return instance;
    }

    private int timestamp;

    private CommandProcessor() {
    }

    public CommandOutput execute(Command cmd) {
        int timestamp = cmd.getTimestamp();
        int dif_time = timestamp - this.timestamp;
        this.timestamp = timestamp;
        Library.getInstance().tickTime(dif_time);
        return cmd.execute();
    }
}
