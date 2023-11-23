package command;

import command.output.CommandOutput;
import command.output.GetTop5SongsCommandOutput;
import library.Library;
import library.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetTop5SongsCommand extends Command {
    @Override
    public CommandOutput execute() {
        Library library = Library.getInstance();
        List<Song> songs = library.getSongs();
        ArrayList<String> songNames = new ArrayList<>();

        if (songs.size() <= 5) {
            for (Song song : songs) {
                songNames.add(song.getName());
            }
            return new GetTop5SongsCommandOutput(getTimestamp(), songNames);
        }

        int[] maxLikes = new int[5];
        String[] maxNames = new String[5];
        for (int i = 0; i < 5; i++) {
            maxLikes[i] = songs.get(i).getLikes();
            maxNames[i] = songs.get(i).getName();
        }
        for (int i = 5; i < songs.size(); i++) {
            Song song = songs.get(i);
            for (int j = 0; j < 5; j++) {
                if (song.getLikes() > maxLikes[j]) {
                    for (int k = 4; k > j; k--) {
                        maxLikes[k] = maxLikes[k - 1];
                        maxNames[k] = maxNames[k - 1];
                    }
                    maxLikes[j] = song.getLikes();
                    maxNames[j] = song.getName();
                    break;
                }
            }
        }
        songNames.addAll(Arrays.stream(maxNames).toList());
        return new GetTop5SongsCommandOutput(getTimestamp(), songNames);
    }
}