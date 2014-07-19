import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/JUMPGAME
 * Created by hojin.ghim on 2014-07-19.
 */
public abstract class Main {
    abstract boolean jumpGame(int[][] board);

    static public void main(String[] args) {
        main(new MemoizedJumpGame(), System.in, System.out);
    }

    static void main(Main main, InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        int count = scanner.nextInt();
        for (int testCase = 0; testCase < count; ++testCase) {
            int size = scanner.nextInt();
            int[][] board = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            boolean result = main.jumpGame(board);
            out.println(result? "YES": "NO");
        }
    }

    static class RecursiveJumpGame extends Main {
        @Override
        public boolean jumpGame(int[][] board) {
            return jumpGame(board, 0, 0);
        }

        boolean jumpGame(int[][] board, int i, int j) {
            if (i >= board.length || j >= board[0].length) return false;
            if (i == board.length - 1 && j == board[0].length - 1) return true;

            int steps = board[i][j];
            return jumpGame(board, i, j + steps) || jumpGame(board, i + steps, j);
        }

    }

    static class MemoizedJumpGame extends Main {
        private static final int U = 0;
        private static final int F = -1;
        private static final int T = 1;

        @Override
        public boolean jumpGame(int[][] board) {
            initCache(board);
            return jumpGame(0, 0);
        }

        boolean jumpGame(int i, int j) {
            if (isTerminal(board, i, j)) return getVerdict(board, i, j);

            if (!haveCache(i, j)) {
                int steps = board[i][j];
                boolean result = jumpGame(i + steps, j) || jumpGame(i, j + steps);
                setCache(i, j, result);
            }
            return getCache(i, j);
        }

        boolean getVerdict(int[][] board, int i, int j) {
            return !(i >= board.length || j >= board[0].length) && i == board.length - 1 && j == board[0].length - 1;
        }

        boolean isTerminal(int[][] board, int i, int j) {
            return i >= board.length || j >= board[0].length || (i == board.length - 1 && j == board[0].length - 1);
        }

        private int[][] board;
        private int[][] cache;

        private void initCache(int[][] board) {
            this.board = board;
            this.cache = new int[board.length][board[0].length];
            Arrays.stream(cache).forEach(row -> Arrays.fill(row, U));
        }

        private void setCache(int i, int j, boolean value) {
            cache[i][j] = value? T: F;
        }

        private boolean getCache(int i, int j) {
            return T == cache[i][j];
        }

        private boolean haveCache(int i, int j) {
            return U != cache[i][j];
        }

    }
}
