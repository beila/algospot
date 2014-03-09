import java.io.*;
import java.util.*;

/**
 * http://algospot.com/judge/problem/read/BOGGLE
 */
public class Main {
    private static final int BOARD_SIZE = 5;

    char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public Main(String boggle) throws IOException {
        try (BufferedReader d = new BufferedReader(new StringReader(boggle))) {
            for (int i = 0; i < BOARD_SIZE; ++i) {
                String line = d.readLine();
                for (int j = 0; j < BOARD_SIZE; ++j)
                    board[i][j] = line.charAt(j);
            }
        }
    }
    
    public Main(String[] boggle) {
        for (int i = 0; i < BOARD_SIZE ; ++i) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = boggle[i].charAt(j);
            }
        }
    }

    public boolean findString(String s) {
        return findStringFromPlaces(Place.ALL_PLACES, s, s);
    }

    public boolean findStringFromPlaces(Collection<Place> places, String s, String word) {
        if (s.length() == 0) return true;
        for (Place place: places) {
            if (findStringFromAPlace(place, s, word)) return true;
        }
        return false;
    }

    public boolean findStringFromAPlace(Place place, String s, String word) {
        if (get(place) != s.charAt(0)) return false;
        Collection<Place> neighbors = place.neighbors();
        return findStringFromPlaces(neighbors, s.substring(1), word);
    }

    private char get(Place n) {
        return board[n.x][n.y];
    }

    static class Place {
        private static final List<Place> ALL_PLACES;
        static {
            ALL_PLACES = Collections.unmodifiableList(
                    buildPlaces(BOARD_SIZE, BOARD_SIZE));
        }

        Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        final int x;
        final int y;
        private Collection<Place> neighbors;

        public Collection<Place> neighbors() {
            return neighbors;
        }

        static private int buildKey(int x, int y) {
            if (x < 0 || x >= BOARD_SIZE) return -1;
            if (y < 0 || y >= BOARD_SIZE) return -1;
            return x * BOARD_SIZE + y;
        }

        @SuppressWarnings("PointlessArithmeticExpression")
        private Set<Integer> buildNeighborKeys() {
            Set<Integer> keys = new HashSet<>();
            keys.add(buildKey(x - 1, y - 1));
            keys.add(buildKey(x - 0, y - 1));
            keys.add(buildKey(x + 1, y - 1));
            keys.add(buildKey(x - 1, y - 0));
            keys.add(buildKey(x + 1, y - 0));
            keys.add(buildKey(x - 1, y + 1));
            keys.add(buildKey(x - 0, y + 1));
            keys.add(buildKey(x + 1, y + 1));
            keys.remove(-1);
            return keys;
        }

        private Collection<Place> buildNeighborsFrom(List<Place> allPlaces) {
            final Set<Integer> neighborKeys = buildNeighborKeys();
            Collection<Place> places = new ArrayList<>(neighborKeys.size());
            for (int i: neighborKeys) {
                places.add(allPlaces.get(i));
            }
            return places;
        }

        private static List<Place> buildPlaces(int xSize, int ySize) {
            final List<Place> places = new ArrayList<>(xSize*ySize);
            for (int i = 0; i < xSize; ++i)
                for (int j = 0; j < ySize; ++j)
                    places.add(buildKey(i, j), new Place(i, j));

            for (Place p: places) {
                p.neighbors = p.buildNeighborsFrom(places);
            }

            return places;
        }

    }

    public static void main(String[] args) throws IOException {
        main(args, System.in, System.out);
    }

    public static void main(@SuppressWarnings("UnusedParameters") String[] args, InputStream in, PrintStream out) throws IOException {
        try (BufferedReader d = new BufferedReader(new InputStreamReader(in))) {
            final int round = Integer.valueOf(d.readLine());
            final Main[] mains = buildMains(d, round);

            final int numStrings = Integer.valueOf(d.readLine());
            final String[] strings = buildStrings(d, numStrings);

            for (Main main: mains) {
                main.run(out, strings);
            }
        }
    }

    private static Main[] buildMains(BufferedReader d, int round) throws IOException {
        final Main[] mains = new Main[round];
        for (int i = 0; i < round; ++i) {
            String[] boardLines = new String[BOARD_SIZE];
            for (int j = 0; j < BOARD_SIZE; ++j) {
                boardLines[j] = d.readLine();
            }
            mains[i] = new Main(boardLines);
        }
        return mains;
    }

    private static String[] buildStrings(BufferedReader d, int numStrings) throws IOException {
        String[] strings = new String[numStrings];
        for (int i = 0; i < numStrings; ++i) {
            strings[i] = d.readLine();
        }
        return strings;
    }

    private void run(PrintStream out, String[] strings) {
        for (String s: strings) {
            out.println(s + (findString(s)? " YES": " NO"));
        }
    }
}
