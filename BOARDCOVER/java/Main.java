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
        int sum = 0;
        for (CoverPart cover: L_PARTS) {
            sum += cover.coverable(board, 0, 0) ? 1 : 0;
        }
        return sum;
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
    }
}
