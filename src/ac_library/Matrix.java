package ac_library;
import java.awt.Dimension;
import java.util.Arrays;

public class Matrix {
    private long[] data;
    private Dimension dim;

    public Matrix(int h, int w, long e) {
        data = new long[h * w];
        dim = new Dimension(w, h);
        if(e == 0) return;
        Arrays.fill(data, e);
    }

    public Matrix(int h, int w) {
        this(h, w, 0);
    }

    public Matrix(int n, long e) {
        this(n, n, e);
    }

    public Matrix(int n) {
        this(n, n, 0);
    }

    public Matrix(final long[][] a) {
        final int h = a.length;
        final int w = a[0].length;
        dim = new Dimension(h, w);
        data = new long[h * w];
        for(int i = 0;i < h; ++i){
            for(int j = 0;j < w; ++j){
                data[i * w + j] = a[i][j];
            }
        }
    }

    public int get_h() {
        return dim.height;
    }

    public int get_w() {
        return dim.width;
    }

    public long get(int i, int j) {
        assert(0 <= i && i < dim.height);
        assert(0 <= j && j < dim.width);
        return data[i * dim.width + j];
    }

    public void set(int i, int j, long val) {
        assert(0 <= i && i < dim.height);
        assert(0 <= j && j < dim.width);
        data[i * dim.width + j] = val;
    }

    public Matrix copy() {
        Matrix ret = new Matrix(dim.height, dim.width);
        System.arraycopy(data, 0, ret.data, 0, data.length);
        return ret;
    }

    public Dimension shape() {
        return new Dimension(dim);
    }

    public Matrix mul(final Matrix other) {
        if(dim.width != other.dim.height) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(dim.height, other.dim.width);
        for(int i = 0;i < dim.height; ++i){
            for(int k = 0;k < dim.width; ++k){
                for(int j = 0;j < other.dim.width; ++j){
                    ret.data[i * ret.dim.width + j] += data[i * dim.width + k] * other.data[k * other.dim.width + j];
                }
            }
        }
        return ret;
    }
    
    public Matrix mulMod(final Matrix other, final int mod) {
        if(dim.width != other.dim.height) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(dim.height, other.dim.width);
        for(int i = 0;i < dim.height; ++i){
            for(int k = 0;k < dim.width; ++k){
                final long self_ik = data[i * dim.width + k] % mod;
                for(int j = 0;j < other.dim.width; ++j){
                    ret.data[i * ret.dim.width + j] += self_ik * (other.data[k * other.dim.width + j]%mod) % mod;
                    if(ret.data[i * ret.dim.width + j] >= mod) {
                        ret.data[i * ret.dim.width + j] -= mod;
                    }
                }
            }
        }
        return ret;
    }

    public Matrix mulMod(final Matrix other, final ModIntFactory factory) {
        if(dim.width != other.dim.height) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(dim.height, other.dim.width);
        for(int i = 0;i < dim.height; ++i){
            for(int k = 0;k < dim.width; ++k){
                final ModIntFactory.ModInt self_ik = factory.create(data[i * dim.width + k]);
                for(int j = 0;j < other.dim.width; ++j){
                    final ModIntFactory.ModInt other_kj = factory.create(other.data[k * other.dim.width + j]);
                    ret.data[i * ret.dim.width + j] += self_ik.mul(other_kj).value();
                    if(ret.data[i * ret.dim.width + j] >= factory.getMod()) {
                        ret.data[i * ret.dim.width + j] -= factory.getMod();
                    }
                }
            }
        }
        return ret;
    }

    public Matrix pow(long n) {
        if(dim.height != dim.width) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(dim.height);
        for(int i = 0;i < dim.height; ++i){
            ret.data[i * dim.width + i] = 1;
        }
        Matrix a = copy();
        while(n > 0){
            if((n & 1) == 1) ret = ret.mul(a);
            a = a.mul(a);
            n >>= 1;
        }
        return ret;
    }

    public Matrix powMod(long n, final int mod) {
        if(dim.height != dim.width) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(dim.height);
        for(int i = 0;i < dim.height; ++i){
            ret.data[i * dim.width + i] = 1;
        }
        Matrix a = copy();
        while(n > 0){
            if((n & 1) == 1) ret = ret.mulMod(a, mod);
            a = a.mulMod(a, mod);
            n >>= 1;
        }
        return ret;
    }

    public Matrix powMod(long n, ModIntFactory mod) {
        if(dim.height != dim.width) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(dim.height);
        for(int i = 0;i < dim.height; ++i){
            ret.data[i * dim.width + i] = 1;
        }
        Matrix a = copy();
        while(n > 0){
            if((n & 1) == 1) ret = ret.mulMod(a, mod);
            a = a.mulMod(a, mod);
            n >>= 1;
        }
        return ret;
    }
}