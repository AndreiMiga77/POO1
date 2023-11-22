package library;

import fileio.input.EpisodeInput;

public class PodcastEpisode {
    private String name;
    private int duration;
    private String description;

    public PodcastEpisode(EpisodeInput input) {
        name = input.getName();
        duration = input.getDuration();
        description = input.getDescription();
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}