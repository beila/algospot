import java.util.Arrays;

/**
 * http://algospot.com/judge/problem/read/BOARDCOVER
 * Created by hojin.ghim on 3/15/14.
 */
public class Main {
    private final boolean[][] board;

    public Main(boolean[][] board) {
        this.board = board;
    }

    public int countLayoutCases() {
        boolean[][] coveredBoard = cloneBoard(board);
        int sum = 0;
        for (CoverPart part: L_PARTS) {
            if (!part.coverable(coveredBoard, 0, 0)) continue;
            part.cover(coveredBoard, 0, 0);
            ++sum;
        }
        if (!allCovered(coveredBoard)) return 0;
        return sum;
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
    }
}
