/**
 * https://algospot.com/judge/problem/read/JUMPGAME
 * Created by hojin.ghim on 2014-07-19.
 */
public interface Main {
    boolean jumpGame(int[][] board);

    static class RecursiveJumpGame implements Main {
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
}
