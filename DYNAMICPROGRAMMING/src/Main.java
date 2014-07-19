/**
 * Chapter 08 Dynamic Programming
 * Created by hojin.ghim on 2014-07-12.
 */
public interface Main {
    int bino(int n, int r);

    static class RecursiveBinomial implements Main {
        @Override
        public int bino(int n, int r) {
//        if (r == 0 || r == n) return 1;
//        if (n == 1) return 1;
            if (r == 1) return n;
            if (n == r) return 1;
            return bino(n - 1, r - 1) + bino(n - 1, r);
        }
    }

    static class MemoizedBinomial implements Main {
        @Override
        public int bino(int n, int r) {
            if (isCached(n, r)) {
                return getCache(n, r);
            }
            int result = bino(n - 1, r - 1) + bino(n - 1, r);
            setCache(n, r, result);
            return result;
        }

        int[][] cache = new int[30][30]; {
            initCache();
        }

        private void initCache() {
            for (int n = 0; n < cache.length; ++n)
                for (int r = 0; r < cache[n].length; ++r)
                    cache[n][r] = -1;
            for (int n = 0; n < cache.length; ++n) {
                int r = 1;
                cache[n][r] = n;
                r = n;
                cache[n][r] = 1;
            }
        }

        private int getCache(int n, int r) {
            return cache[n][r];
        }

        private boolean isCached(int n, int r) {
            return 0 <= cache[n][r];
        }

        private void setCache(int n, int r, int value) {
            cache[n][r] = value;
        }

    }
}
