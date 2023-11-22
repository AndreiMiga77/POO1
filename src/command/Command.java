package command;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command"
)
@JsonSubTypes({
        @Type(value = SearchCommand.class, name = "search"),
        @Type(value = SelectCommand.class, name = "select"),
})
public abstract class Command {
    private String command;
    private String username;
    private Integer timestamp;

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
}
