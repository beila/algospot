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

    // TODO no default constructor
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        main(args, System.in, System.out);
    }

    public static void main(@SuppressWarnings("UnusedParameters") String[] args, InputStream in, PrintStream out) throws IOException {
        try (BufferedReader d = new BufferedReader(new InputStreamReader(in))) {
            String line = d.readLine();
            final int round = Integer.valueOf(line);
            for (int i = 0; i < round; ++i) {
                final Main main = new Main();
                line = d.readLine();
                out.println(main.mainOne(line));
            }
        }
    }

    public String mainOne(String arg) {
        return "Hello, " + arg + "!";
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
}
