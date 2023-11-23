package command.output;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ShowPlaylistsCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    ArrayList<LinkedHashMap<String, Object>> result;

    public ShowPlaylistsCommandOutput(String user, Integer timestamp, ArrayList<LinkedHashMap<String, Object>> data) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = data;
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

    public ArrayList<LinkedHashMap<String, Object>> getResult() {
        return result;
    }

    public void setResult(ArrayList<LinkedHashMap<String, Object>> res) {
        result = res;
    }
}
