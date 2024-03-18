package ac_library;

public final class Sieve {
    public static class PE {
        public final int p, e;
        public PE(int p, int e) {
            this.p = p;
            this.e = e;
        }
    }

    private final int[] min_factor;
    private final int n;
    
    public Sieve(int n) {
        this.n = n;
        min_factor = new int[n+1];
        min_factor[1] = 1;

        for (int i = 2;i <= n; ++i) {
            if (min_factor[i] != 0) continue;
            min_factor[i] = i;
            final int m = n/i;
            for (int j = i;j <= m; ++j) {
                if (min_factor[i*j] == 0) {
                    min_factor[i*j] = i;
                }
            }
        }
    }

    public int[] getPrimes() {
        if (n < 2) return new int[0];
        int cnt = 1;
        for (int i = 3;i <= n; i += 2) {
            if(this.min_factor[i] == i)++cnt;
        }

        final int[] primes = new int[cnt];
        primes[0] = 2;
        int idx = 1;
        for (int i = 3;i <= n; i += 2) {
            if (this.min_factor[i] == i) primes[idx++] = i;
        }

        return primes;
    }

    public boolean isPrime(int x) {
        AssertUtil.check(0 <= x && x <= n);
        if (x < 2) return false;
        return min_factor[x] == x;
    }

    public java.util.ArrayList<PE> factorize(int x) {
        AssertUtil.check(0 <= x && x <= n);
        java.util.ArrayList<PE> res = new java.util.ArrayList<>();
        while (x > 1) {
            final int p = this.min_factor[x];
            int e = 0;
            while (this.min_factor[x] == p) {
                x /= p;
                ++e;
            }
            res.add(new PE(p, e));
        }
        return res;
    }
    
    public int[] divisors(int x) {
        AssertUtil.check(1 <= x && x <= n);
        java.util.ArrayList<PE> factors = this.factorize(x);
        int n = 1;
        for (PE pe: factors) {
            n *= pe.e + 1;
        }
        final int[] res = new int[n];
        res[0] = 1;
        int len = 1;
        for (PE pe: factors) {
            final int s = len;
            final int p = pe.p;
            final int e = pe.e;
            int pow = 1;
            for (int i = 0;i < e; ++i) {
                pow *= p;
                for (int j = 0;j < s; ++j) {
                    res[len++] = res[j]*pow;
                }
            }
        }

        return res;   
    }
}
