package command.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowPreferredSongsCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private ArrayList<String> result;

    public ShowPreferredSongsCommandOutput(String user, Integer timestamp, List<String> songs) {
        this.user = user;
        this.timestamp = timestamp;
        this.result = new ArrayList<>(songs);
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

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> res) {
        result = res;
    }
}
