package command.output;

import command.output.CommandOutput;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SearchCommandOutput extends CommandOutput {
    private ArrayList<String> results;
    public SearchCommandOutput(String username, Integer timestamp, String message, List<String> results) {
        super(username, timestamp, message);
        this.results = new ArrayList<>(results);
    }

    public List<String> getResults() {
        return Collections.unmodifiableList(results);
    }
}
