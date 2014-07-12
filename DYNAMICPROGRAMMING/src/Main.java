/**
 * Chapter 08 Dynamic Programming
 * Created by hojin.ghim on 2014-07-12.
 */
public class Main {
    public static int bino(int n, int r) {
//        if (r == 0 || r == n) return 1;
//        if (n == 1) return 1;
        if (r == 1) return n;
        if (n == r) return 1;
        return bino(n - 1, r - 1) + bino(n - 1, r);
    }
}
