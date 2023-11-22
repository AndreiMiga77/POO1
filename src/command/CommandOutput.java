package command;

public class CommandOutput {
    private String command;
    private String username;
    private Integer timestamp;
    private String message;

    public CommandOutput(String command, String username, Integer timestamp, String message) {
        this.command = command;
        this.username = username;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
