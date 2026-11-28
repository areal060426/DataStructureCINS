// (2) DATA: song data, ratings, comments, sorting, file handling
/* load ng artist and song info from "catalog.txt"
 * load ng ratings and comments from "ratings.txt"
 * save ng new ratings and comments
 * calculate ng ave rating ng songs and artist
 * sort ng artists and songs
 * kinukuha top-rated songs
 */

package FinalsProject_PlakaRDS;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;


public class Data {
	
	public static final String CATALOG = "catalog.txt";
	public static final String RATINGS = "ratings.txt";
	
	public static class Review {
		public final int stars;
		public final String text;
		
		public Review(int stars, String text) {
			this.stars = stars;
			this.text = text;
		}
	}
	
	public static class Song {
		public final String title, artist;
		public int sumRate = 0;
		public int numRate = 0;
		public final int[] starCount = new int[6];
		public final List<Review> comments = new ArrayList<>();
		
		public Song(String title, String artist) {
			this.title = title;
			this.artist = artist;
		}
		public void addRate(int stars) {
			sumRate += stars;
			numRate++; 
			starCount[stars]++;
		}
				// avg = sum of ratings / number of ratings
		public double avg() {
			return numRate == 0 ? 0 : (double) sumRate / numRate;
		}
		public String avgText() {
			return numRate == 0 ? "No ratings yet."
					: String.format("%s %.1f / 5 (%d)", Theme.STAR, avg(), numRate);
		}
		@Override public String toString() {
			return title;
		}
	}
	// LinkedHashMap: store key-value pairs while maintaining insertion order
	// in here: Key = Artist name, Value = List of songs ng artist
	// HashMap: store key-value pairs, insertion order not guaranteed
	// in here: Key = Artist name, Value = album, artistPic, albumPic
	public static final Map<String, List<Song>> catalog = new LinkedHashMap<>();
	public static final Map<String, String> album = new HashMap<>();
	public static final Map<String, String> artistPic = new HashMap<>();
	public static final Map<String, String> albumPic = new HashMap<>();
	
	public static int artistSort = 0, songSort = 0;
	
	public static int artistSum(String a) {
		int s = 0;
		for (Song x : catalog.get(a)) s += x.sumRate; 
		return s;
	}
	public static int artistCount(String a) {
		int n = 0;
		for (Song x : catalog.get(a)) n += x.numRate;
		return n;
	}
	public static double artistAvg(String a) {
		int n = artistCount(a);
		return n == 0 ? 0 : (double) artistSum(a) / n;
	}
	public static String artistAvgText(String a) {
		int n = artistCount(a);
		return n == 0 ? "No ratings yet."
				: String.format("%s %.1f / 5 (%d rating%s)", Theme.STAR, 
						artistAvg(a), n, n == 1 ? "" : "s");
	}
				// artistOrder(): sorting rules sa artists
				// Comparator<>: para mag-define ng rules kung paanu icocompare at isosort objects
	public static Comparator<String> artistOrder(int mode) {
		Comparator<String> byName = String.CASE_INSENSITIVE_ORDER; // ignore cases
		if (mode == 0)
			return byName;
		Comparator<String> byAvg = (a, b) -> {
			boolean ra = artistCount(a) > 0, rb = artistCount(b) > 0;
			// gets the total num of ratings by artists and checks if it's greater than zero
			if (ra != rb)
				return ra ? -1 : 1;
			// if ra is true, return -1 otherwise 1
			// sa sorting, -1 places it before the second, otherwise yes
			int c = Double.compare(artistAvg(a), artistAvg(b));
			return mode == 1 ? -c : c;
		};
		return byAvg.thenComparing(byName);
	}
								// songOrder(): sorting rules sa songs
	public static Comparator<Song> songOrder(int mode) {
		Comparator<Song> byName = (a, b) -> a.title.compareToIgnoreCase(b.title);
		if (mode == 0)
			return byName;
		Comparator<Song> byAvg = (a, b) -> {
			boolean ra = a.numRate > 0, rb = b.numRate > 0;
			if (ra != rb)
				return ra ? -1 : 1;
			int c = Double.compare(a.avg(), b.avg());
			return mode == 1 ? -c : c;
		};
		return byAvg.thenComparing(byName);
	}
		// topRated(): kinukuha top-rated songs
		/* ArrayList<>(): store ng collection of elements, pwede dagdagan and bawasan
		 * para din hindi na need mag-declare ng fixed num of songs or revieww */
	public static List<Song> topRated() {
		List<Song> all = new ArrayList<>(); 
		for (List<Song> l : catalog.values())
			for (Song s : l)
				if (s.numRate > 0)
					all.add(s);
		all.sort((a, b) -> {
			int c = Double.compare(b.avg(), a.avg());
			return c != 0 ? c : Integer.compare(b.numRate, a.numRate);
		});
		return all;
	}
				// loadCatalog(): reads the catalog file
	public static void loadCatalog() {
		Path p = Paths.get(CATALOG);
		if (!Files.exists(p))
			return;
		try {
			for (String line : Files.readAllLines(p, StandardCharsets.UTF_8)) {
				line = line.trim();
				if (line.startsWith("\uFEFF")) line = line.substring(1);
				if (line.isEmpty() || line.startsWith("#") || !line.contains("|"))
					continue;
				String[] parts = line.split("\\|", 5);
				String artist = parts[0].trim();
				if (parts.length == 5) {
					artistPic.put(artist, parts[1].trim());
					album.put(artist, parts[2].trim());
					albumPic.put(artist, parts[3].trim());
				} else if (parts.length == 4) {
					album.put(artist, parts[1].trim());
					albumPic.put(artist, parts[2].trim());
				} else if (parts.length == 3) {
					album.put(artist, parts[1].trim());
				}
				List<Song> songs = new ArrayList<>();
				for (String t : parts[parts.length - 1].split(";"))
					if (!t.trim().isEmpty())
						songs.add(new Song(t.trim(), artist));
				catalog.put(artist, songs);
			}
		} catch (IOException e) {
			Theme.info(null, "Could not read " + CATALOG + ": " + e.getMessage());
		}
	}
					// loadRatings(): reads the saved ratings
	public static void loadRatings() {
		Path p = Paths.get(RATINGS);
		if (!Files.exists(p))
			return;
		try { 
			for (String line : Files.readAllLines(p, StandardCharsets.UTF_8)) {
				String[] f = line.split("\t", 4);
				if (f.length < 3)
					continue;
				Song s = find(f[0], f[1]);
				if (s == null)
					continue;
				int st;
				try {
					st = Integer.parseInt(f[2]);
					if (st < 1 || st > 5)
						continue;
				} catch (NumberFormatException e) {
					continue;
				}
				s.addRate(st);
				if (f.length == 4 && !f[3].isEmpty())
						s.comments.add(new Review(st, f[3].replace("\\n", "\n")));
			}
		} catch (IOException e) {}
	}
					// appendRating(): add ng new rating sa file
	public static void appendRating(Song s, int stars, String comment) {
		String row = s.artist + "\t" + s.title + "\t" + stars + "\t" +
					comment.replace("\t", " ").replace("\n", "\\n") + "\n";
		try {
			Files.write(Paths.get(RATINGS), row.getBytes(StandardCharsets.UTF_8),
			StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			Theme.info(null, "Rating counted this session, \nbut could not be saved to disk.");
		}
	}
					// find(): hanap ng specific song, sa search din yan
	public static Song find(String artist, String title) {
		List<Song> l = catalog.get(artist);
		if (l == null)
			return null;
		for (Song s : l)
			if (s.title.equals(title))
				return s;
		return null;
	}
}