import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://algospot.com/judge/problem/read/BOARDCOVER
 * Created by hojin.ghim on 3/15/14.
 */
public class Main {
    private final boolean[][] slots;

    public Main(boolean[][] slots) {
        this.slots = slots;
    }
    public int countLayoutCases() {
        boolean[][] coveredBoard = cloneBoard(slots);
        return countLayoutCasesOnSubBoard(coveredBoard, 0, 0);
    }

    private int countLayoutCasesOnSubBoard(boolean[][] coveredBoard, int x, int y) {
        int sum = 0;
        for (Main.CoverPart part: L_PARTS) {
            if (!part.coverable(coveredBoard, x, y)) continue;
            part.cover(coveredBoard, x, y);
            if (isEnd(x, y)) sum += allCovered(coveredBoard)? 1: 0;
            else sum += countLayoutCasesOnSubBoard(coveredBoard, nextX(x), nextY(y));
            part.uncover(coveredBoard, x, y);
        }
        return sum;
    }

    private boolean isEnd(int x, int y) {
        return nextX(x) == 0 && nextY(y) == 0;
    }

    private int nextX(int x) {
        return (x + 1) % (slots.length - PART_WIDTH + 1);
    }

    private int nextY(int y) {
        return (y + 1) % (slots[0].length - PART_HEIGHT + 1);
    }

    private boolean[][] cloneBoard(boolean[][] srcBoard) {
        boolean[][] targetBoard = new boolean[srcBoard.length][];
        for (int i = 0; i < srcBoard.length; i++) {
            targetBoard[i] = Arrays.copyOf(srcBoard[i], srcBoard[i].length);
        }
        return targetBoard;
    }

    private boolean allCovered(boolean[][] coveredBoard) {
        for (boolean[] aCoveredBoard : coveredBoard) {
            for (boolean anACoveredBoard : aCoveredBoard) {
                if (anACoveredBoard) return false;
            }
        }
        return true;
    }

    static int PART_WIDTH = 2;
    static int PART_HEIGHT = 2;
    static CoverPart[] L_PARTS = new CoverPart[] {
            new CoverPart(/*new int[]{0,0},*/ new int[]{0,1}, new int[]{1,0}, new int[]{1,1}),
            new CoverPart(new int[]{0,0}, /*new int[]{0,1},*/ new int[]{1,0}, new int[]{1,1}),
            new CoverPart(new int[]{0,0}, new int[]{0,1}, /*new int[]{1,0},*/ new int[]{1,1}),
            new CoverPart(new int[]{0,0}, new int[]{0,1}, new int[]{1,0}/*, new int[]{1,1}*/),
    };

    static class CoverPart {
        final int[][] points;

        CoverPart(int[]... points) {
            this.points = points;
        }

        public boolean coverable(boolean[][] board, int x, int y) {
            for (int[] point: points) {
                if (!board[x+point[0]][y+point[1]]) return false;
            }
            return true;
        }

        public void cover(boolean[][] board, int x, int y) {
            for (int[] point: points) {
                board[x+point[0]][y+point[1]] = false;
            }
        }

        public void uncover(boolean[][] board, int x, int y) {
            for (int[] point: points) {
                board[x+point[0]][y+point[1]] = true;
            }
        }
    }

    public static void main(String[] args) {
        main(System.in, System.out);
    }

    public static void main(InputStream inputStream, PrintStream printStream) {
        Scanner scanner = new Scanner(inputStream);
        final int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {
            final int height = scanner.nextInt();
            final int width = scanner.nextInt();
            scanner.nextLine();
            final boolean[][] slots = new boolean[width][height];
            for (int j = 0; j < height; j++) {
                char[] cells = scanner.nextLine().toCharArray();
                for (int k = 0; k < width; k++) {
                    slots[k][j] = '.' == cells[k];
                }
            }
            final Main main = new Main(slots);
            printStream.println(main.countLayoutCases());
        }
    }
}
