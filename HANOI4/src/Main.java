import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

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
            @SuppressWarnings("UnusedDeclaration") final int totalDisc = scanner.nextInt();  // N <= 12
            Main main = new Main();
            for (int j = 0; j < 4; j++) {
                final int numDisc = scanner.nextInt();
                for (int k = 0; k < numDisc; k++) {
                    main.poles[j].addDisc(Disc.get(scanner.nextInt() - 1));
                }
            }
            main.print(System.err);
            printStream.println("result");
        }
    }

    Pole[] poles = new Pole[] {new Pole(0), new Pole(1), new Pole(2), new Pole(3)};

    private void print(PrintStream ps) {
        if (!Main.debug) return;
        Arrays.stream(poles).forEach(ps::println);
    }

    private static class Pole {
        private final int id;

        boolean[] possession = new boolean[Disc.values().length];
        public Pole(int id) {
            this.id = id;
        }

        public void addDisc(Disc disc) {
            // TODO constraint
            possession[disc.ordinal()] = true;
        }

        @Override
        public String toString() {
            return "Pole{" + "id=" + id + ", possession="
                    + IntStream.range(0, possession.length)
                    .filter(i -> possession[i])
                    .map(i -> i + 1)
                    .mapToObj(Integer::toString)
                    .reduce((s, s2) -> s + ", " + s2).get()
                    + '}';
        }

    }

    private enum Disc {
        D01, D02, D03, D04, D05, D06, D07, D08, D09, D10, D11, D12;

        public static Disc get(int i) {
            return Disc.values()[i];
        }
    }
}
