package ac_library;

public final class ContestScanner {
    private final java.io.InputStream in;
    private final byte[] buffer;
    private static final long MUL_LIMIT = -Long.MIN_VALUE / 10;
    private int ptr = 0;
    private int buflen = 0;

    public ContestScanner(java.io.InputStream in) {
        this.in = in;
        this.buffer = new byte[1 << 14];
    }

    public ContestScanner(java.io.File file) throws java.io.FileNotFoundException {
        this(new java.io.FileInputStream(file));
    }

    public ContestScanner() {
        this(System.in);
    }

    private boolean hasNextByte() {
        if (ptr < buflen) return true;
        ptr = 0;
        try {
            buflen = in.read(buffer);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return buflen > 0;
    }

    private byte readByte() {
        if (!hasNextByte()) throw new java.util.NoSuchElementException();
        return buffer[ptr++];
    }

    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    private static boolean isNumber(int c) {
        return 48 <= c && c <= 57;
    }

    public final boolean hasNext() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ++ptr;
        return hasNextByte();
    }

    public final String next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        byte b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            if (!hasNextByte()) break;
            b = readByte();
        }
        return sb.toString();
    }

    public final long nextLong1() {
        long n = this.nextLong();
        if (n == Long.MIN_VALUE) throw new NumberFormatException();
        return --n;
    }

    public final long nextLong() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        byte b = readByte();
        final boolean minus = b == '-';
        if (minus) {
            b = readByte();
        }

        long n = 0;
        while (isPrintableChar(b)) {
            if (!isNumber(b) || n < MUL_LIMIT) throw new NumberFormatException(); // 10倍オーバーフローチェック
            n *= 10;
            final long digit = b - '0';
            if (n < Long.MIN_VALUE + digit) throw new NumberFormatException(); // 減算オーバーフローチェック
            n -= digit;
            if (!hasNextByte()) break;
            b = readByte();
        }
        if (minus) {
            return n;
        } else {
            if (n == Long.MIN_VALUE) throw new NumberFormatException();
            return -n;
        }
    }

    public final int nextInt1() {
        long n = nextLong1();
        if (n < Integer.MIN_VALUE || n > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) n;
    }

    public final int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }

    public final double nextDouble() {
        return Double.parseDouble(next());
    }

    public final long[] nextLongArray(int length) {
        long[] array = new long[length];
        for (int i = 0; i < length; ++i) array[i] = this.nextLong();
        return array;
    }

    public final long[] nextLongArray1(int length) {
        long[] array = new long[length];
        for (int i = 0; i < length; ++i) array[i] = this.nextLong1();
        return array;
    }

    public final long[] nextLongArray(int length, java.util.function.LongUnaryOperator map) {
        long[] array = new long[length];
        for (int i = 0; i < length; ++i) array[i] = map.applyAsLong(this.nextLong());
        return array;
    }

    public final int[] nextIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; ++i) array[i] = this.nextInt();
        return array;
    }

    public final int[] nextIntArray1(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; ++i) array[i] = this.nextInt1();
        return array;
    }

    public final int[] nextIntArray(int length, java.util.function.IntUnaryOperator map) {
        int[] array = new int[length];
        for (int i = 0; i < length; ++i) array[i] = map.applyAsInt(this.nextInt());
        return array;
    }

    public final double[] nextDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; ++i) array[i] = this.nextDouble();
        return array;
    }

    public final double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map) {
        double[] array = new double[length];
        for (int i = 0; i < length; ++i) array[i] = map.applyAsDouble(this.nextDouble());
        return array;
    }

    public final long[][] nextLongMatrix(int height, int width) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextLong();
            }
        return mat;
    }

    public final long[][] nextLongMatrix1(int height, int width) {
        long[][] mat = new long[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                mat[i][j] = this.nextLong1();
            }
        }
        return mat;
    }

    public final int[][] nextIntMatrix(int height, int width) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextInt();
            }
        return mat;
    }

    public final int[][] nextIntMatrix1(int height, int width) {
        int[][] mat = new int[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                mat[i][j] = this.nextInt1();
            }
        }
        return mat;
    }

    public final double[][] nextDoubleMatrix(int height, int width) {
        double[][] mat = new double[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                mat[h][w] = this.nextDouble();
            }
        return mat;
    }

    public final char[][] nextCharMatrix(int height, int width) {
        char[][] mat = new char[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++) {
                mat[h][w] = s.charAt(w);
            }
        }
        return mat;
    }
}
