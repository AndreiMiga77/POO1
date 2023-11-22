package command;

import command.output.CommandOutput;

public class ShuffleCommand extends Command {
    private Integer seed;

    public Integer getSeed() {
        return seed;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}