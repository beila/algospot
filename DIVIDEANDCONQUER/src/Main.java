import java.util.Arrays;

/**
 * Chapter 7
 * Created by hojin.ghim on 2014-07-12.
 */
public class Main {
    static public void main(String[] args) {
    }

    static int fastSum(int n) {
        if (1 == n) return 1;
        if (1 == n % 2) return fastSum(n - 1) + n;
        return n * n / 4 + 2 * fastSum(n / 2);
    }

    static class SquareMatrix {
        @Override
        public String toString() {
            return "SquareMatrix{" +
                    "v=" + Arrays.deepToString(v) +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SquareMatrix oo = (SquareMatrix) o;

            for (int i = 0; i < v.length; ++i) {
                for (int j = 0; j < v[i].length; ++j) {
                    if (v[i][j] != oo.v[i][j]) return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        SquareMatrix(double[][] v) {
            this.v = v;
        }

        double[][] v;

        public static SquareMatrix power(SquareMatrix a, int i) {
            if (1 == i) return a;
            if (1 == i % 2) return multiply(power(a, i - 1), a);
            SquareMatrix a2 = power(a, i / 2);
            return multiply(a2, a2);
        }

        private static SquareMatrix multiply(SquareMatrix a, SquareMatrix b) {
            return new SquareMatrix(new double[][]{
                    {a.v[0][0] * b.v[0][0] + a.v[0][1] * b.v[1][0], a.v[0][0] * b.v[0][1] + a.v[0][1] * b.v[1][1]},
                    {a.v[1][0] * b.v[0][0] + a.v[1][1] * b.v[1][0], a.v[1][0] * b.v[0][1] + a.v[1][1] * b.v[1][1]}
            });
        }
    }
}
