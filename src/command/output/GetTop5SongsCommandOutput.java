package command.output;

import java.util.ArrayList;
import java.util.List;

public class GetTop5SongsCommandOutput extends CommandOutput {
    private Integer timestamp;
    private ArrayList<String> result;

    public GetTop5SongsCommandOutput(Integer timestamp, List<String> songs) {
        this.timestamp = timestamp;
        this.result = new ArrayList<>(songs);
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
