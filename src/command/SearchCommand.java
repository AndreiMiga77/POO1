package command;

import java.util.Map;

public class SearchCommand extends Command {
    private String type;
    private Map<String, Object> filters;

    public SearchCommand() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }
}
