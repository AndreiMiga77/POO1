package library;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import fileio.input.PodcastInput;
import fileio.input.EpisodeInput;

public class Podcast implements Playable {
    private String name;
    private String owner;

    private ArrayList<PodcastEpisode> episodes;

    public Podcast(PodcastInput input) {
        name = input.getName();
        owner = input.getOwner();

        episodes = new ArrayList<>();
        for (EpisodeInput episode : input.getEpisodes()) {
            episodes.add(new PodcastEpisode(episode));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<PodcastEpisode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }
}
