package ac_library;

public class Matrix {
    private final long[] data;
    private final int height,  width;

    public Matrix(int h, int w, long e) {
        data = new long[h * w];
        height = h;
        width = w;
        if(e == 0) return;
        java.util.Arrays.fill(data, e);
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
        height = h;
        width = w;
        data = new long[h * w];
        for(int i = 0;i < h; ++i){
            System.arraycopy(a[i], 0, data, i*w, w);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long get(int i, int j) {
        assert 0 <= i && i < height;
        assert 0 <= j && j < width;
        return data[i * width + j];
    }

    public void set(int i, int j, long val) {
        assert 0 <= i && i < height;
        assert 0 <= j && j < width;
        data[i * width + j] = val;
    }

    public Matrix copy() {
        Matrix ret = new Matrix(height, width);
        System.arraycopy(data, 0, ret.data, 0, data.length);
        return ret;
    }

    public java.awt.Dimension shape() {
        return new java.awt.Dimension(width, height);
    }

    public void modAsg(final int mod) {
        assert 0 < mod;
        final int n = height*width;
        for(int i = 0;i < n; ++i){
            data[i] %= mod;
            if(data[i] < 0){
                data[i] += mod;
            }
        }
    }

    public Matrix mul(final Matrix other) {
        if(width != other.height) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(height, other.width);
        for(int i = 0;i < height; ++i){
            for(int k = 0;k < width; ++k){
                final long self_ik = data[i * width + k];
                for(int j = 0;j < other.width; ++j){
                    ret.data[i * ret.width + j] += self_ik * other.data[k * other.width + j];
                }
            }
        }
        return ret;
    }
    
    // !注意!：自身とotherはmodが取られている前提
    public Matrix mulMod(final Matrix other, final int mod) {
        if(width != other.height) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(height, other.width);
        for(int i = 0;i < height; ++i){
            for(int k = 0;k < width; ++k){
                final long self_ik = data[i * width + k];
                for(int j = 0;j < other.width; ++j){
                    ret.data[i * ret.width + j] += self_ik * other.data[k * other.width + j] % mod;
                    if(ret.data[i * ret.width + j] >= mod) {
                        ret.data[i * ret.width + j] -= mod;
                    }
                }
            }
        }
        return ret;
    }

    // !注意!：自身とotherはmodが取られている前提
    public Matrix mulMod(final Matrix other, final ModIntFactory factory) {
        if(width != other.height) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(height, other.width);
        final long MOD = factory.getMod();
        for(int i = 0;i < height; ++i){
            for(int k = 0;k < width; ++k){
                final ModIntFactory.ModInt self_ik = factory.raw(data[i * width + k]);
                for(int j = 0;j < other.width; ++j){
                    final ModIntFactory.ModInt other_kj = factory.raw(other.data[k * other.width + j]);
                    ret.data[i * ret.width + j] += self_ik.mul(other_kj).value();
                    if(ret.data[i * ret.width + j] >= MOD) {
                        ret.data[i * ret.width + j] -= MOD;
                    }
                }
            }
        }
        return ret;
    }

    public Matrix pow(long n) {
        if(height != width) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(height);
        for(int i = 0;i < height; ++i){
            ret.data[i * width + i] = 1;
        }
        Matrix a = copy();
        while(n > 0){
            if((n & 1) == 1) ret = ret.mul(a);
            a = a.mul(a);
            n >>= 1;
        }
        return ret;
    }

    // !注意!：自身はmodが取られている前提
    public Matrix powMod(long n, final int mod) {
        if(height != width) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(height);
        for(int i = 0;i < height; ++i){
            ret.data[i * width + i] = 1;
        }
        Matrix a = copy();
        while(n > 0){
            if((n & 1) == 1) ret = ret.mulMod(a, mod);
            a = a.mulMod(a, mod);
            n >>= 1;
        }
        return ret;
    }

    // !注意!：自身はmodが取られている前提
    public Matrix powMod(long n, ModIntFactory mod) {
        if(height != width) throw new RuntimeException("invalid shape");
        Matrix ret = new Matrix(height);
        for(int i = 0;i < height; ++i){
            ret.data[i * width + i] = 1;
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

