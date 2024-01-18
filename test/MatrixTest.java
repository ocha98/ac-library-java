import org.junit.Assert;
import org.junit.Test;

import ac_library.Matrix;
import ac_library.ModIntFactory;

public class MatrixTest {
    @Test
    public void TestMul1() {
        Matrix a = new Matrix(3);
        Matrix b = new Matrix(3);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a.set(i, j, i * 3 + j + 1);
                b.set(i, j, i * 3 + j + 3*3 + 1);
            }
        }

        Matrix ans_ab = a.mul(b);
        Assert.assertEquals(84, ans_ab.get(0, 0));
        Assert.assertEquals(90, ans_ab.get(0, 1));
        Assert.assertEquals(96, ans_ab.get(0, 2));
        Assert.assertEquals(201, ans_ab.get(1, 0));
        Assert.assertEquals(216, ans_ab.get(1, 1));
        Assert.assertEquals(231, ans_ab.get(1, 2));
        Assert.assertEquals(318, ans_ab.get(2, 0));
        Assert.assertEquals(342, ans_ab.get(2, 1));
        Assert.assertEquals(366, ans_ab.get(2, 2));
    }

    @Test
    public void TestMul2() {
        Matrix a = new Matrix(3, 2);
        Matrix b = new Matrix(2, 3);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 2; ++j){
                a.set(i, j, i * 2 + j + 1);
            }
        }
        for(int i = 0;i < 2; ++i){
            for(int j = 0;j < 3; ++j){
                b.set(i, j, i * 3 + j + 3*2 + 1);
            }
        }

        Matrix ans_ab = a.mul(b);
        Assert.assertEquals( 27, ans_ab.get(0, 0));
        Assert.assertEquals( 30, ans_ab.get(0, 1));
        Assert.assertEquals( 33, ans_ab.get(0, 2));
        Assert.assertEquals( 61, ans_ab.get(1, 0));
        Assert.assertEquals( 68, ans_ab.get(1, 1));
        Assert.assertEquals( 75, ans_ab.get(1, 2));
        Assert.assertEquals( 95, ans_ab.get(2, 0));
        Assert.assertEquals( 106, ans_ab.get(2, 1));
        Assert.assertEquals( 117, ans_ab.get(2, 2));
    }

    @Test
    public void TestMulMOD1() {
        final int MOD = 998244353;
        Matrix a = new Matrix(3);
        Matrix b = new Matrix(3);
        for(int i = 0;i < 3; ++i) {
            for(int j = 0;j < 3; ++j) {
                long val_a = 100000*i + 99999*j;
                long val_b = 888888*i + 12345*j;
                a.set(i, j, val_a);
                b.set(i, j, val_b);
            }
        }

        Matrix ans = a.mulMod(b, MOD);
        Assert.assertEquals( 220818475, ans.get(0, 0));
        Assert.assertEquals( 929548381, ans.get(0, 1));
        Assert.assertEquals( 640033934, ans.get(0, 2));
        Assert.assertEquals( 355976224, ans.get(1, 0));
        Assert.assertEquals( 775228718, ans.get(1, 1));
        Assert.assertEquals( 196236859, ans.get(1, 2));
        Assert.assertEquals( 491133973, ans.get(2, 0));
        Assert.assertEquals( 620909055, ans.get(2, 1));
        Assert.assertEquals( 750684137, ans.get(2, 2));
    }

    @Test
    public void TestMulMOD2() {
        final int MOD = 998244353;
        Matrix a = new Matrix(3, 2);
        Matrix b = new Matrix(2, 3);
        for(int i = 0;i < 3; ++i) {
            for(int j = 0;j < 2; ++j) {
                long val = 100000*i + 99999*j;
                a.set(i, j, val);
            }
        }
        for(int i = 0;i < 2; ++i){
            for(int j = 0;j < 3; ++j){
                long val = 123456789*i + 87654321*j;
                b.set(i, j, val);
            }
        }

        Matrix ans = a.mulMod(b, MOD);
        Assert.assertEquals( 267529660, ans.get(0, 0));
        Assert.assertEquals( 28311646, ans.get(0, 1));
        Assert.assertEquals( 787337985, ans.get(0, 2));
        Assert.assertEquals( 658516109, ans.get(1, 0));
        Assert.assertEquals( 116170709, ans.get(1, 1));
        Assert.assertEquals( 572069662, ans.get(1, 2));
        Assert.assertEquals( 51258205, ans.get(2, 0));
        Assert.assertEquals( 204029772, ans.get(2, 1));
        Assert.assertEquals( 356801339, ans.get(2, 2));
    }

    @Test
    public void TestMulMOD1_by_factory() {
        final ModIntFactory factory = new ModIntFactory(998244353);
        Matrix a = new Matrix(3);
        Matrix b = new Matrix(3);
        for(int i = 0;i < 3; ++i) {
            for(int j = 0;j < 3; ++j) {
                long val_a = 100000*i + 99999*j;
                long val_b = 888888*i + 12345*j;
                a.set(i, j, val_a);
                b.set(i, j, val_b);
            }
        }

        Matrix ans = a.mulMod(b, factory);
        Assert.assertEquals( 220818475, ans.get(0, 0));
        Assert.assertEquals( 929548381, ans.get(0, 1));
        Assert.assertEquals( 640033934, ans.get(0, 2));
        Assert.assertEquals( 355976224, ans.get(1, 0));
        Assert.assertEquals( 775228718, ans.get(1, 1));
        Assert.assertEquals( 196236859, ans.get(1, 2));
        Assert.assertEquals( 491133973, ans.get(2, 0));
        Assert.assertEquals( 620909055, ans.get(2, 1));
        Assert.assertEquals( 750684137, ans.get(2, 2));
    }

    @Test
    public void TestMulMOD2_by_factory() {
        final ModIntFactory factory = new ModIntFactory(998244353);
        Matrix a = new Matrix(3, 2);
        Matrix b = new Matrix(2, 3);
        for(int i = 0;i < 3; ++i) {
            for(int j = 0;j < 2; ++j) {
                long val = 100000*i + 99999*j;
                a.set(i, j, val);
            }
        }
        for(int i = 0;i < 2; ++i){
            for(int j = 0;j < 3; ++j){
                long val = 123456789*i + 87654321*j;
                b.set(i, j, val);
            }
        }

        Matrix ans = a.mulMod(b, factory);
        Assert.assertEquals( 267529660, ans.get(0, 0));
        Assert.assertEquals( 28311646, ans.get(0, 1));
        Assert.assertEquals( 787337985, ans.get(0, 2));
        Assert.assertEquals( 658516109, ans.get(1, 0));
        Assert.assertEquals( 116170709, ans.get(1, 1));
        Assert.assertEquals( 572069662, ans.get(1, 2));
        Assert.assertEquals( 51258205, ans.get(2, 0));
        Assert.assertEquals( 204029772, ans.get(2, 1));
        Assert.assertEquals( 356801339, ans.get(2, 2));
    }

    @Test
    public void TestPow1() {
        Matrix a = new Matrix(3, 3);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a.set(i, j, i * 3 + j + 1);
            }
        }

        Matrix ans = a.pow(10);
        Assert.assertEquals(  132476037840L, ans.get(0, 0));
        Assert.assertEquals(  162775103256L, ans.get(0, 1));
        Assert.assertEquals(  193074168672L, ans.get(0, 2));
        Assert.assertEquals(  300005963406L, ans.get(1, 0));
        Assert.assertEquals(  368621393481L, ans.get(1, 1));
        Assert.assertEquals(  437236823556L, ans.get(1, 2));
        Assert.assertEquals(  467535888972L, ans.get(2, 0));
        Assert.assertEquals(  574467683706L, ans.get(2, 1));
        Assert.assertEquals(  681399478440L, ans.get( 2, 2));
    }

    @Test
    public void TestPow2() {
        Matrix a = new Matrix(3, 3);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a.set(i, j, i * 3 + j + 1);
            }
        }

        Matrix ans = a.pow(0);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                Assert.assertEquals(i == j ? 1 : 0, ans.get(i, j));
            }
        }
    }

    @Test
    public void TestPowMOD() {
        final int MOD = 998244353;
        Matrix a = new Matrix(3, 3);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a.set(i, j, i * 3 + j + 1);
            }
        }

        Matrix ans = a.powMod(100000, MOD);
        Assert.assertEquals(  227207790, ans.get(0, 0));
        Assert.assertEquals(  935897169, ans.get(0, 1));
        Assert.assertEquals(  646342195, ans.get(0, 2));
        Assert.assertEquals(  744359294, ans.get(1, 0));
        Assert.assertEquals(  149468180, ans.get(1, 1));
        Assert.assertEquals(  552821419, ans.get(1, 2));
        Assert.assertEquals(  263266445, ans.get(2, 0));
        Assert.assertEquals(  361283544, ans.get(2, 1));
        Assert.assertEquals(  459300643, ans.get( 2, 2));
    }

    @Test
    public void TestPowMOD_by_factory() {
        ModIntFactory factory = new ModIntFactory(998244353);
        Matrix a = new Matrix(3, 3);
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a.set(i, j, i * 3 + j + 1);
            }
        }

        Matrix ans = a.powMod(100000, factory);
        Assert.assertEquals(  227207790, ans.get(0, 0));
        Assert.assertEquals(  935897169, ans.get(0, 1));
        Assert.assertEquals(  646342195, ans.get(0, 2));
        Assert.assertEquals(  744359294, ans.get(1, 0));
        Assert.assertEquals(  149468180, ans.get(1, 1));
        Assert.assertEquals(  552821419, ans.get(1, 2));
        Assert.assertEquals(  263266445, ans.get(2, 0));
        Assert.assertEquals(  361283544, ans.get(2, 1));
        Assert.assertEquals(  459300643, ans.get( 2, 2));
    }

    @Test
    public void TestCopy() {
        Matrix a = new Matrix(2, 2);
        for(int i = 0;i < 2; ++i){
            for(int j = 0;j < 2; ++j){
                a.set(i, j, i*2 + j);
            }
        }

        Matrix b = a.copy();
        for(int i = 0;i < 2; ++i){
            for(int j = 0;j < 2; ++j){
                Assert.assertEquals(a.get(i, j), b.get(i, j));
            }
        }

        a.set(0, 0, 100);
        Assert.assertEquals(100, a.get(0, 0));
        Assert.assertEquals(0, b.get(0, 0));

        b.set(1, 1, 200);
        Assert.assertEquals(200, b.get(1, 1));
        Assert.assertEquals(2*1 + 1, a.get(1, 1));
    }
}
