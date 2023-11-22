package library;

import fileio.input.UserInput;

public class User {
    private String username;
    private int age;
    private String city;

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
}
