import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * http://algospot.com/judge/problem/read/HANOI4
 * Created by hojin.ghim on 2014-05-10.
 */
public class Main {

    private static boolean debug = true;

    public static void main(String[] args) {
        Main.debug = false;
        main(System.in, System.out);
    }

    public static void main(InputStream inputStream, PrintStream printStream) {
        Scanner scanner = new Scanner(inputStream);
        final int numCases = scanner.nextInt(); // C <= 50
        for (int i = 0; i < numCases; i++) {
            final int totalDisc = scanner.nextInt();  // N <= 12
            Main main = new Main(Disc.get(totalDisc));
            for (int j = 0; j < 4; j++) {
                final int numDisc = scanner.nextInt();
                for (int k = 0; k < numDisc; k++) {
                    assert -1 != main.poles[j].placeDisc(Disc.get(scanner.nextInt()));
                }
            }
            main.print();
            printStream.println(main.moveDiscToLastPole());
        }
    }

    private Pole lastPole;
    Pole[] poles; {
        Map<Disc, Pole> p = new HashMap<>();
        lastPole = new Pole(3, p);
        poles = new Pole[]{new Pole(0, p), new Pole(1, p), new Pole(2, p), lastPole};
    }

    private Disc largestDisc;

    public Main(Disc largestDisc) {
        this.largestDisc = largestDisc;
    }

    int moveDiscToLastPole() {
        if (allMoved()) return 0;
        return moveDiscToLastPole(largestDisc);
    }

    private int moveDiscToLastPole(Disc disc) {
        if (disc.none()) return 0;
        disc.print();
        int movement = lastPole.placeDisc(disc);
        print();
        return moveDiscToLastPole(disc.justSmaller()) + movement;
    }

    private boolean allMoved() {
        return Arrays.stream(poles).limit(poles.length - 1).allMatch(Pole::isEmpty);
    }

    private void print() {
        if (!Main.debug) return;
        Arrays.stream(poles).forEach(System.err::println);
    }

    private static class Pole {
        private final int id;
        private final Map<Disc, Pole> discPoleMap;

        public Pole(int id, Map<Disc, Pole> discPoleMap) {
            this.id = id;
            this.discPoleMap = discPoleMap;
        }

        public int placeDisc(Disc disc) {
            Disc currentMinDisc = getDiscs().min(Comparator.<Disc>naturalOrder()).orElse(Disc.D13);
            if (disc.equals(currentMinDisc)) return 0;
            if (disc.compareTo(currentMinDisc) > 0) return -1;
            discPoleMap.put(disc, this);
            return 1;
        }

        private Stream<Disc> getDiscs() {
            return discPoleMap.entrySet().stream()
                    .filter(e -> equals(e.getValue()))
                    .map(Map.Entry::getKey);
        }

        @Override
        public String toString() {
            return "Pole{" + "id=" + id + ", possession="
                    + getDiscs().sorted(Comparator.<Disc>reverseOrder())
                    .map((Function<Disc, String>) Disc::toString)
                    .reduce((s1, s2) -> s1 + ", " + s2).orElse("-")
                    + '}';
        }

        public boolean isEmpty() {
            return getDiscs().count() == 0L;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    private enum Disc {
        D00, // boundary
        D01, D02, D03, D04, D05, D06, D07, D08, D09, D10, D11, D12,
        D13; // boundary

        public static Disc get(int i) {
            assert i >= 0;
            assert i < Disc.values().length - 1;
            return Disc.values()[i];
        }

        public Disc justSmaller() {
            return Disc.get(ordinal() - 1);
        }

        public boolean none() {
            return equals(D00);
        }

        void print() {
            if (debug) System.err.println(this);
        }
    }
}
