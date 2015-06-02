import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ls {
    String run(int W, String[] words) {
        Arrays.sort(words);
        int[] wordsSize = Stream.of(words).mapToInt(String::length).toArray();

        int columnBound = (W - 1) / 3 + 1;
        int columns;
        for (columns = columnBound; columns > 0; --columns) {
            int[] columnSize = columnSize(wordsSize, columns);
            if (maxWidth(columnSize) < W) break;
        }
        ++columns;

        int lines = lines(words.length, columns);
        // TODO build string from found columns

        String line = String.join("__", words);
        return addPadding(line, W);
    }

    private int maxWidth(int[] columnSize) {
        return IntStream.of(columnSize).map(c -> c + 2).sum() - 2;
    }

    private int[] columnSize(int[] wordsSize, int columns) {
        int lines = lines(wordsSize.length, columns);
        return IntStream.range(0, columns).map(i ->
            IntStream.range(i * lines, i * lines + wordsSize.length).map(w -> wordsSize[w]).max().getAsInt()
        ).toArray();
    }

    private int lines(int words, int columns) {
        return (int) Math.ceil((double) words /(double) columns);
    }

    private String addPadding(String line, int w) {
        StringBuilder sb = new StringBuilder(line);
        while (sb.length() < w) {
            sb.append('_');
        }
        return sb.toString();
    }
}
