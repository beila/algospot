import java.io.*;

/**
 * http://algospot.com/judge/problem/read/HELLOWORLD
 */
public class Main {

    public static void main(String[] args) throws IOException {
        main(args, System.in, System.out);
    }

    public static void main(@SuppressWarnings("UnusedParameters") String[] args, InputStream in, PrintStream out) throws IOException {
        final Main main = new Main();
        try (BufferedReader d = new BufferedReader(new InputStreamReader(in))) {
            String line = d.readLine();
            final int round = Integer.valueOf(line);
            for (int i = 0; i < round; ++i) {
                line = d.readLine();
                out.println(main.mainOne(line));
            }
        }
    }

    public String mainOne(String arg) {
        return "Hello, " + arg + "!";
    }
}
