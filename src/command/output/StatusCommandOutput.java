package command.output;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class StatusCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    LinkedHashMap<String, Object> stats;
    public StatusCommandOutput(String user, Integer timestamp, LinkedHashMap<String, Object> stats) {
        this.user = user;
        this.timestamp = timestamp;
        this.stats = stats;
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

    public HashMap<String, Object> getStats() {
        return stats;
    }

    public void setStats(LinkedHashMap<String, Object> stats) {
        this.stats = stats;
    }
}
