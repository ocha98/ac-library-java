package ac_library;
public class ContestScanner {
    private final java.io.InputStream in;
    private byte[] buffer;
    private final static long MUL_LIMIT = -Long.MIN_VALUE/10;
    private final boolean once_read;
    private int ptr = 0;
    private int buflen = 0;

    public ContestScanner(java.io.InputStream in, boolean once_read){
        this.once_read = once_read;
        if(!once_read){
            this.buffer = new byte[1<<14];
        }
        this.in = in;
    }
    public ContestScanner(java.io.File file, boolean once_read) throws java.io.FileNotFoundException {
        this(new java.io.FileInputStream(file), once_read);
    }
    public ContestScanner() {
        this(System.in, System.getProperty("ONLINE_JUDGE") == "true");
    }
    public ContestScanner(boolean once_read) {
        this(System.in, once_read);
    }
 
    private boolean hasNextByte() {
        if (ptr < buflen) return true;
        ptr = 0;
        try {
            if(once_read){
                buffer = in.readAllBytes();
                buflen = buffer.length;
            }else{
                buflen = in.read(buffer);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return buflen > 0;
    }
    private byte readByte() {
        if(!hasNextByte()) return -1;
        return buffer[ptr++];
    }
    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }
    private static boolean isNumber(int c) {
        return 48 <= c && c <= 57;
    }
    public final boolean hasNext() {
        while(hasNextByte() && !isPrintableChar(buffer[ptr]))++ptr;
        return hasNextByte();
    }

    public String next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        byte b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
 
    public final long nextLong() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        byte b = readByte();
        final boolean minus = b == '-';
        if (minus) {
            b = readByte();
        }

        long n = 0;
        while(isPrintableChar(b)) {
            if (!isNumber(b) || n < MUL_LIMIT)throw new NumberFormatException();// 10倍オーバーフローチェック
            n = ((n<<2)+n)<<1; // n*10 = (n*4 + n)*2;
            final long digit = b - '0';
            if (n < Long.MIN_VALUE + digit)throw new NumberFormatException();// 加算オーバーフローチェック
            n -= digit;
            b = readByte();
        }
        if(minus) {
            return n;
        } else {
            if(n == Long.MIN_VALUE)throw new NumberFormatException();
            return -n;
        }
    }
    public final int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() {
        return Double.parseDouble(next());
    }
 
    public long[] nextLongArray(int length){
        long[] array = new long[length];
        for(int i=0; i<length; i++) array[i] = this.nextLong();
        return array;
    }
    public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map){
        long[] array = new long[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsLong(this.nextLong());
        return array;
    }
    public int[] nextIntArray(int length){
        int[] array = new int[length];
        for(int i=0; i<length; i++) array[i] = this.nextInt();
        return array;
    }
    public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map){
        int[] array = new int[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsInt(this.nextInt());
        return array;
    }
    public double[] nextDoubleArray(int length){
        double[] array = new double[length];
        for(int i=0; i<length; i++) array[i] = this.nextDouble();
        return array;
    }
    public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map){
        double[] array = new double[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsDouble(this.nextDouble());
        return array;
    }
 
    public long[][] nextLongMatrix(int height, int width){
        long[][] mat = new long[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = this.nextLong();
        }
        return mat;
    }
    public int[][] nextIntMatrix(int height, int width){
        int[][] mat = new int[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = this.nextInt();
        }
        return mat;
    }
    public double[][] nextDoubleMatrix(int height, int width){
        double[][] mat = new double[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = this.nextDouble();
        }
        return mat;
    }
 
    public char[][] nextCharMatrix(int height, int width){
        char[][] mat = new char[height][width];
        for(int h=0; h<height; h++){
            String s = this.next();
            for(int w=0; w<width; w++){
                mat[h][w] = s.charAt(w);
            }
        }
        return mat;
    }
}
