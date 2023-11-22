package command.output;

public class LikeCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private String message;
    public LikeCommandOutput(String user, Integer timestamp, String message) {
        this.user = user;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
