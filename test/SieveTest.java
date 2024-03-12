import ac_library.Sieve;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SieveTest {
    boolean isPrime(long x) {
        if(x == 0 || x == 1) return false;
        if(x == 2) return true;
        if(x % 2 == 0) return false;

        for(int i = 3;i*i <= x; i += 2){
            if(x % i == 0) return false;
        }
        return true;
    }
    
    @Test
    public void testGetPrimes() {
        final int n = (int)1e6;
        Sieve sieve = new Sieve(n);
        int[] primes = sieve.getPrimes();
        for(int v: primes){
            Assert.assertTrue(isPrime(v));
        }
        Assert.assertEquals(78498, primes.length);
    }

    @Test
    public void testIsPrime() {
        final int n = (int)1e6;
        Sieve sieve = new Sieve(n);
        for(int i = 0;i <= n; ++i){
            Assert.assertEquals("i = " + i, isPrime(i), sieve.isPrime(i));
        }

    }

    ArrayList<Sieve.PE> factorize(int x) {
        ArrayList<Sieve.PE> res = new ArrayList<>();
        for(int i = 2;i*i <= x; ++i){
            if(x % i == 0){
                int e = 0;
                while(x % i == 0){
                    x /= i;
                    ++e;
                }
                res.add(new Sieve.PE(i, e));
            }
        }
        if(x > 1){
            res.add(new Sieve.PE(x, 1));
        }
        return res;
    }

    @Test
    public void testFactorize() {
        final int n = (int)1e6;
        Sieve sieve = new Sieve(n);
        Assert.assertEquals(0, sieve.factorize(0).size());
        Assert.assertEquals(0, sieve.factorize(1).size());
        for(int i = 2;i <= n; ++i){
            ArrayList<Sieve.PE> pe = sieve.factorize(i);
            ArrayList<Sieve.PE> pe2 = factorize(i);
            Assert.assertEquals(pe.size(), pe2.size());
            pe.sort((a, b) -> a.p - b.p);
            pe2.sort((a, b) -> a.p - b.p);
            
            for(int j = 0;j < pe.size(); ++j){
                Assert.assertEquals(pe.get(j).p, pe2.get(j).p);
                Assert.assertEquals(pe.get(j).e, pe2.get(j).e);
            }
        }

    }

    @Test
    public void testDivisors() {
        final int n = (int)1e6;
        Sieve sieve = new Sieve(n);
        for(int i = 1;i <= n; ++i){
            int[] divisors = sieve.divisors(i);
            ArrayList<Sieve.PE> pe = sieve.factorize(i);
            int x = 1;
            for(Sieve.PE p : pe){
                x *= p.e+1;
            }
            int m = divisors.length;
            Assert.assertEquals(x, m);
            Arrays.sort(divisors);
            for(int j = 0;j < m/2; ++j){
                Assert.assertEquals(i, divisors[j] * divisors[m-1-j]);
            }
            if(m%2 == 1){
                Assert.assertEquals(i, divisors[m/2] * divisors[m/2]);
            }
        }
    }
}