package command;

public class SelectCommand extends Command {
    private Integer itemNumber;

    public SelectCommand() {
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public CommandOutput execute() {
        return null;
    }
}
