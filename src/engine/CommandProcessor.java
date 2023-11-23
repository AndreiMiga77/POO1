package engine;

import command.Command;
import command.output.CommandOutput;
import library.Library;

public class CommandProcessor {
    private static CommandProcessor instance;

    public static void createCommandProcessor() {
        instance = new CommandProcessor();
    }
    public static CommandProcessor getInstance() {
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
