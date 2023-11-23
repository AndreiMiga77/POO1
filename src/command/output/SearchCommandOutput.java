package command.output;

import command.output.CommandOutput;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SearchCommandOutput extends CommandOutput {
    private String user;
    private Integer timestamp;
    private String message;
    private ArrayList<String> results;
    public SearchCommandOutput(String user, Integer timestamp, String message, List<String> results) {
        this.user = user;
        this.timestamp = timestamp;
        this.message = message;
        this.results = new ArrayList<>(results);
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

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> res) {
        results = res;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
