import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * http://algospot.com/judge/problem/read/BOGGLE
 */
public class Main {
    private static final int BOARD_SIZE = 5;
    private static final Collection<Place> ALL_PLACES;
    static {
        Collection<Place> allPlaces = new ArrayList<>();
        for (int i = 1; i <= BOARD_SIZE; ++i)
            for (int j = 1; j <= BOARD_SIZE; ++j)
                allPlaces.add(new Place(i, j));
        ALL_PLACES = Collections.unmodifiableCollection(allPlaces);
    }

    char[][] board = new char[BOARD_SIZE+2][BOARD_SIZE+2];
    { for (char[] b: board) Arrays.fill(b, '*'); }

    public Main(String boggle) throws IOException {
        try (BufferedReader d = new BufferedReader(new StringReader(boggle))) {
            for (int i = 1; i <= BOARD_SIZE; ++i) {
                String line = d.readLine();
                for (int j = 1; j <= BOARD_SIZE; ++j)
                    board[i][j] = line.charAt(j-1);
            }
        }
    }
    
    public Main(String[] boggle) {
        for (int i = 1; i <= BOARD_SIZE ; ++i) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                board[i][j] = boggle[i-1].charAt(j-1);
            }
        }
    }

    public boolean findString(String s) {
        return findStringFromPlaces(ALL_PLACES, s, s);
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
        Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        final int x;
        final int y;

        public Collection<Place> neighbors() {
            //noinspection PointlessArithmeticExpression
            return Arrays.asList(
                    new Place(x-1,y-1),
                    new Place(x-0,y-1),
                    new Place(x+1,y-1),
                    new Place(x-1,y-0),
                    new Place(x+1,y-0),
                    new Place(x-1,y+1),
                    new Place(x-0,y+1),
                    new Place(x+1,y+1)
            );
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
