# Proiect GlobalWaves  - Etapa 1

<div align="center"><img src="https://tenor.com/view/listening-to-music-spongebob-gif-8009182.gif" width="300px"></div>

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1)

## Code Structure

There are 2 main classes, `Library` and `CommandProcessor`, each having a single global instance (replaced every test).

### `Library`
The `Library` contains all `User`s, `Song`s and `Podcast`s on the platform. It provides various methods to easily find filtered lists of the data it contains.
It also provides a `tickTime` method, which advances the timestamp for each user's `Player`.

### `CommandProcessor`
The `CommandProcessor` contains an `execute` method, which takes a `Command` as an argument and returns a `CommandOutput`.
Each time a command is executed, the timestamp (stored inside the global instance) is updated.
The time difference is broadcast to each `Player` using `Library.tickTime()`.

### `User`
Each user on the platform has a `User` class containing data about them. Most importantly, each `User` contains a `Player`, which is used for playing audio.
A `User` also contains a list of the playlists they created, as well as lists of likes songs and followed playlists.

### `Player`
Each user has a `Player`. The player contains data about the currently loaded source, timestamp, repeat and shuffle modes as well as paused/unpaused state.
The method `tickTime` advances the time for the player and automatically unloads/repeats the currently loaded source.
The currently loaded source is stored as a `Playable`, to the source abstract.

### `Playable` interface
The `Playable` interface contains methods which are implemented by `Song`, `Playlist`, `Podcast` and `PodcastEpisode`.
These methods provide uniform access to data such as name and duration, which is implemented differently by each class.

### `Song`
A song is just a regular, single, audio track that can be played. It is the only kind of track allowed in a playlist.
Users can like/unlike songs.

### `Playlist`
A playlist is a collection of songs that can be played sequentially. Playlists can also be shuffled while being played.
Users can follow/unfollow other user's public playlists.

### `Podcast`
A podcast is a collection of episodes which can be played sequentially. Podcasts have the unique property that their seek is remembered when unloaded from the player.
This allows a user to resume playback from where it left.

### `PodcastEpisode`
Only found inside podcasts. Treated as tracks by the `Player`.

### `Command`
Each command is implemented as a child class of `Command`. The `Command` provides the username and timestamp fields, as well as an abstract `execute` method.
Each child overrides this method with the functionality it has to execute. The method is called inside `CommandProcessor`.
The `Command` class (and its children) are implemented using Jackson's `JsonTypeInfo`, so the `ObjectMapper.readValue` method can create the correct instance for each JSON object.

### `CommandOutput`
Each command has an output, which is represented by a child of the `CommandOutput` class. It is implemented using Jackson's `JsonTypeInfo`, so that ObjectMapper can easily dispatch it.
