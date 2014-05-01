import com.sun.istack.internal.NotNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static java.lang.Math.max;

/**
 * http://algospot.com/judge/problem/read/BOARDCOVER
 * Created by hojin.ghim on 3/15/14.
 */
public class Main {
    static boolean debug = true;
    private final boolean[][] slots;

    public Main(boolean[][] slots) {
        this.slots = surroundBoard(slots);
    }

    public int countLayoutCases() {
        List<Point> coverablePoints = coverablePoints(slots);
        if (coverablePoints.isEmpty()) return 0;
        if (debug) {
            System.err.println(String.format("width=%d,height=%d now", slots[0].length-1, slots.length-1));
            for (Point p: coverablePoints) System.err.println(p.toString());
        }
        return countLayoutCasesOnSubBoard(slots, coverablePoints);
    }

    private int countLayoutCasesOnSubBoard(boolean[][] coveredBoard, List<Point> points) {
        if (points.isEmpty()) return allCovered(coveredBoard)? 1: 0;

        Point head = points.get(0);
        List<Point> tail = points.subList(1, points.size());
        int sum = 0;
        for (Main.CoverPart part: L_PARTS) {
            if (debug) {
                System.err.println(String.format("x=%d,y=%d now", head.x, head.y));
                printBoard(coveredBoard);
                System.err.println(String.format("x=%d,y=%d after: %b", head.x, head.y,
                        part.coverable(coveredBoard, head)));
                printIfCovered(coveredBoard, head, part);
            }
            if (!part.coverable(coveredBoard, head)) continue;
            part.cover(coveredBoard, head);
            sum += countLayoutCasesOnSubBoard(coveredBoard, tail);
            part.uncover(coveredBoard, head);
        }

        if (isPointCovered(coveredBoard, head)) {
            sum += countLayoutCasesOnSubBoard(coveredBoard, tail);
        }

        return sum;
    }

    private boolean isPointCovered(boolean[][] coveredBoard, Point p) {
        return !coveredBoard[p.y][p.x];
    }

    static private boolean[][] surroundBoard(boolean[][] srcBoard) {
        boolean[][] targetBoard = new boolean[srcBoard.length+1][];
        for (int i = 0; i < srcBoard.length; i++) {
            targetBoard[i] = Arrays.copyOf(srcBoard[i], srcBoard[i].length+1);
        }
        targetBoard[targetBoard.length-1] = Arrays.copyOf(new boolean[]{}, srcBoard[0].length+1);
        return targetBoard;
    }

    static private List<Point> coverablePoints(boolean[][] slots) {
        Set<Point> pointSet = new LinkedHashSet<>();
        for (int i = 0; i < slots.length - 1; i++) {
            for (int j = 0; j < slots[i].length - 1; j++) {
                if (!slots[i][j]) continue;
                pointSet.add(new Point(j, i));
                pointSet.add(new Point(max(j - 1, 0), i));
                pointSet.add(new Point(j, max(i - 1, 0)));
                pointSet.add(new Point(max(j - 1, 0), max(i - 1, 0)));
            }
        }
        final ArrayList<Point> pointsList = new ArrayList<>(pointSet);
        pointsList.sort(null);
        return pointsList;
    }

    private boolean allCovered(boolean[][] coveredBoard) {
        for (boolean[] aCoveredBoard : coveredBoard) {
            for (boolean anACoveredBoard : aCoveredBoard) {
                if (anACoveredBoard) return false;
            }
        }
        return true;
    }

    static private void printBoard(boolean[][] coveredBoard) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] aCoveredBoard : coveredBoard) {
            for (boolean anACoveredBoard : aCoveredBoard) {
                sb.append(anACoveredBoard ? "." : "#");
            }
            sb.append(System.getProperty("line.separator"));
        }
        System.err.println(sb.toString());
    }

    static private void printIfCovered(boolean[][] srcBoard, Point p, CoverPart part) {
        boolean[][] board = surroundBoard(srcBoard);
        part.cover(board, p);
        printBoard(board);
    }

    static class Point implements Comparable<Point> {
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            return x == point.x && y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public int compareTo(@NotNull Point o) {
            // NOTE board size should not be over 31
            return hashCode() - o.hashCode();
        }
    }

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

        public boolean coverable(boolean[][] board, Point p) {
            for (int[] point: points) {
                if (!board[p.y+point[1]][p.x+point[0]]) return false;
            }
            return true;
        }

        public void cover(boolean[][] board, Point p) {
            for (int[] point: points) {
                board[p.y+point[1]][p.x+point[0]] = false;
            }
        }

        public void uncover(boolean[][] board, Point p) {
            for (int[] point: points) {
                board[p.y+point[1]][p.x+point[0]] = true;
            }
        }
    }

    public static void main(String[] args) {
        Main.debug = false;
        main(System.in, System.out);
    }

    public static void main(InputStream inputStream, PrintStream printStream) {
        Scanner scanner = new Scanner(inputStream);
        final int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {
            final int height = scanner.nextInt();
            final int width = scanner.nextInt();
            scanner.nextLine();
            final boolean[][] slots = new boolean[height][width];
            for (int j = 0; j < height; j++) {
                char[] cells = scanner.nextLine().toCharArray();
                for (int k = 0; k < width; k++) {
                    slots[j][k] = '.' == cells[k];
                }
            }
            final Main main = new Main(slots);
            printStream.println(main.countLayoutCases());
        }
    }
}
