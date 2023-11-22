package library;

import fileio.input.UserInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private String username;
    private int age;
    private String city;
    private List<? extends Playable> lastSearch;
    private int selectedSource;

    public User(UserInput input) {
        username = input.getUsername();
        age = input.getAge();
        city = input.getCity();
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public List<Playable> getLastSearch() {
        return Collections.unmodifiableList(lastSearch);
    }

    public void setLastSearch(List<? extends Playable> lastSearch) {
        this.lastSearch = new ArrayList<>(lastSearch);
    }

    public int getSelectedSource() {
        return selectedSource;
    }

    public void setSelectedSource(int selectedSource) {
        this.selectedSource = selectedSource;
    }
}
