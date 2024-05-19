package com.companyprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a map Map<String, List<String>> userMap, where the key is a username and the value is a list of user's songs.
 * Also given a map Map<String, List<String>> genreMap, where the key is a genre and the value is a list of songs
 * belonging to this genre. The task is to return a map Map<String, List<String>>, where the key is a username and the
 * value is a list of the user's favorite genres. Favorite genre is a genre with the most song.
 * 
 * Example : Input:
 * 
 * userMap = { "David": ["song1", "song2", "song3", "song4", "song8"], "Emma": ["song5", "song6", "song7"] },
 * 
 * genreMap = { "Rock": ["song1", "song3"], "Dubstep": ["song7"], "Techno": ["song2", "song4"], "Pop": ["song5",
 * "song6"], "Jazz": ["song8", "song9"] }
 * 
 * Output: { "David": ["Rock", "Techno"], "Emma": ["Pop"] }
 * 
 * Explanation: David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres. Emma has 2 Pop and 1 Dubstep
 * song. Pop is Emma's favorite genre.
 *
 * Category : Hard
 * 
 */
public class FavouriteGenre {

	public Map<String, List<String>> getUserFavouriteGenres(Map<String, List<String>> userMap,
			Map<String, List<String>> genreMap) {

		Map<String, List<String>> result = new HashMap<>();

		Map<String, String> songGenreMap = new HashMap<String, String>();
		for (Map.Entry<String, List<String>> genreMapEntry : genreMap.entrySet()) {
			for (String song : genreMapEntry.getValue()) {
				songGenreMap.put(song, genreMapEntry.getKey());
			}
		}

		// key - user value - map of genre and count per genre
		Map<String, Map<String, Integer>> userGenreMap = new HashMap<String, Map<String, Integer>>();

		for (Map.Entry<String, List<String>> userMapEntry : userMap.entrySet()) {

			String user = userMapEntry.getKey();
			int maxGenreCount = 0;
			List<String> genreList = new ArrayList<>();

			for (String song : userMapEntry.getValue()) {
				Map<String, Integer> genreCountMap = userGenreMap.getOrDefault(user, new HashMap<String, Integer>());
				String genre = songGenreMap.get(song);
				genreCountMap.put(genre, genreCountMap.getOrDefault(genre, 0) + 1);

				int genreCount = genreCountMap.get(genre);
				maxGenreCount = Math.max(genreCount, maxGenreCount);

				userGenreMap.put(user, genreCountMap);
			}

			Map<String, Integer> countMap = userGenreMap.get(user);
			for (String genre : countMap.keySet()) {
				if (countMap.get(genre) == maxGenreCount) {
					genreList.add(genre);
				}
			}

			result.put(user, genreList);
			System.out.println(userGenreMap);
		}

		return result;
	}

	public static void main(String[] args) {
		FavouriteGenre fg = new FavouriteGenre();

		Map<String, List<String>> userMap = new HashMap<String, List<String>>();
		userMap.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
		userMap.put("Emma", Arrays.asList("song5", "song6", "song7"));

		Map<String, List<String>> genreMap = new HashMap<String, List<String>>();
		genreMap.put("Rock", Arrays.asList("song1", "song3"));
		genreMap.put("Dubstep", Arrays.asList("song7"));
		genreMap.put("Techno", Arrays.asList("song2", "song4"));
		genreMap.put("Pop", Arrays.asList("song5", "song6"));
		genreMap.put("Jazz", Arrays.asList("song8", "song9"));

		System.out.println(fg.getUserFavouriteGenres(userMap, genreMap));
	}

}
