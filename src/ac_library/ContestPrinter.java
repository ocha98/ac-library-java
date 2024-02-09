package ac_library;
public class ContestPrinter implements AutoCloseable {
    private final java.io.OutputStream out;
    private final int buflen = 1<<18;
    private int pos = 0;
    private final byte[] buf = new byte[buflen];
    private static final byte[] Digits = {  '0','0', '0','1', '0','2', '0','3', '0','4', '0','5', '0','6', '0','7', '0','8', '0','9', '1','0', '1','1', '1','2', '1','3', '1','4', '1','5', '1','6', '1','7', '1','8', '1','9', 
                                    '2','0', '2','1', '2','2', '2','3', '2','4', '2','5', '2','6', '2','7', '2','8', '2','9', '3','0', '3','1', '3','2', '3','3', '3','4', '3','5', '3','6', '3','7', '3','8', '3','9',
                                    '4','0', '4','1', '4','2', '4','3', '4','4', '4','5', '4','6', '4','7', '4','8', '4','9', '5','0', '5','1', '5','2', '5','3', '5','4', '5','5', '5','6', '5','7', '5','8', '5','9', 
                                    '6','0', '6','1', '6','2', '6','3', '6','4', '6','5', '6','6', '6','7', '6','8', '6','9', '7','0', '7','1', '7','2', '7','3', '7','4', '7','5', '7','6', '7','7', '7','8', '7','9', 
                                    '8','0', '8','1', '8','2', '8','3', '8','4', '8','5', '8','6', '8','7', '8','8', '8','9', '9','0', '9','1', '9','2', '9','3', '9','4', '9','5', '9','6', '9','7', '9','8', '9','9'
                                };
    private final byte[] numbuf = new byte[25];

    public ContestPrinter() {
        this.out = System.out;
    }
    public ContestPrinter(java.io.OutputStream out) {
        this.out = out;
    }

    private void write(byte[] bytes) {
        this.write(bytes, 0, bytes.length);
    }

    private void write(byte[] bytes, int begin, int len) {
        if(pos + len > buflen) {
            try {
                this.out.write(buf, 0, pos);
                this.out.write(bytes, begin, len);
                pos = 0;
            } catch (java.io.IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            System.arraycopy(bytes, begin, buf, pos, len);
            pos += len;
        }
    }

    private void write(byte b) {
        if(pos == buflen) {
            this.flush();
        }
        this.buf[pos++] = b;
    }

    private static String dtos(double x, int n) {
        StringBuilder sb = new StringBuilder();
        if(x < 0){
            sb.append('-');
            x = -x;
        }
        x += Math.pow(10, -n)/2;
        sb.append((long)x);
        sb.append(".");
        x -= (long)x;
        for(int i = 0;i < n;i++){
            x *= 10;
            sb.append((int)x);
            x -= (int)x;
        }
        return sb.toString();
    }

    // calculate the size required to represent a given number in a String 
    private static int calcNumStringSize(long x) {
        final long v = x > 0 ? -x : x; // Positive numbers don't over flow into negative number
        final int needSign = x < 0 ? 1 : 0;
        long pow10 = -10;
        for(int i = 1;i < 19; ++i){
            if(v > pow10)return i + needSign;
            pow10 *= 10L;
        }
        return 19 + needSign;
    }

    private void writeAsAsciiBytes(long x) {
        final int digits = calcNumStringSize(x);
        if(x < 0){
            numbuf[0] = '-';
        }else{
            x = -x;
        }
        int pos = digits;
        while (x <= -100) {
            final long q = x/100;
            final int r = (int)( q*100 - x ) << 1;
            x = q;
            --pos;
            numbuf[pos] = Digits[r+1];
            --pos;
            numbuf[pos] = Digits[r];
        }
        final int idx = (int)-x << 1; 
        if(x <= -10){
            --pos;
            numbuf[pos] = Digits[idx+1];
            --pos;
            numbuf[pos] = Digits[idx];
        }else{
            --pos;
            numbuf[pos] = Digits[idx+1];
        }

        this.write(numbuf, 0, digits);
    }

    // methods print
    public final void print(String s) {
        this.write(s.getBytes());
    }
    public final void print(boolean x) {
        this.print(""+x);
    }
    public final void print(char x) {
        this.print(""+x);
    }
    public final void print(byte x) {
        this.print(""+x);;
    }
    public final void print(short x) {
        this.print(""+x);
    }
    public final void print(int x) {
        this.writeAsAsciiBytes(x);
    }
    public final void print(long x) {
        this.writeAsAsciiBytes(x);
    }
    public final void print(float x) {
        this.print(dtos(x, 20));
    }
    public final void print(double x) {
        this.print(dtos(x, 20));
    }

    // methods println
    public final void println() {
        this.write((byte)'\n');
    }
    public final void println(boolean x){
        this.print(x);
        this.println();
    }
    public final void println(String s) {
        this.print(s);
        this.println();
    }
    public final void println(char x) {
        this.print(x);
        this.println();
    }
    public final void println(byte x) {
        this.print(x);
        this.println();
    }
    public final void println(short x) {
        this.print(x);
        this.println();
    }
    public final void println(int x) {
        this.print(x);
        this.println();
    }
    public final void println(long x) {
        this.print(x);
        this.println();
    }
    public final void println(float x) {
        this.print(x);
        this.println();
    }
    public final void println(double x) {
        this.print(x);
        this.println();
    }

    @Override
    public final void close() {
        try {
            this.flush();
            this.out.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public final void flush() {
        try {
            this.out.write(buf, 0, pos);
            pos = 0;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void printlnArray(int[] array, String separator){
        int n = array.length;
        if(n==0){
            this.println();
            return;
        }
        for(int i=0; i<n-1; i++){
            this.print(array[i]);
            this.print(separator);
        }
        this.println(array[n-1]);
    }
    public void printlnArray(int[] array){
        this.printlnArray(array, " ");
    }
    public void printlnArray(int[] array, String separator, java.util.function.IntUnaryOperator map){
        int n = array.length;
        if(n==0){
            this.println();
            return;
        }
        for(int i=0; i<n-1; i++){
            this.print(map.applyAsInt(array[i]));
            this.print(separator);
        }
        this.println(map.applyAsInt(array[n-1]));
    }
    public void printlnArray(int[] array, java.util.function.IntUnaryOperator map){
        this.printlnArray(array, " ", map);
    }

    public void printlnArray(long[] array, String separator){
        int n = array.length;
        if(n==0){
            this.println();
            return;
        }
        for(int i=0; i<n-1; i++){
            this.print(array[i]);
            this.print(separator);
        }
        this.println(array[n-1]);
    }
    public void printlnArray(long[] array){
        this.printlnArray(array, " ");
    }
    public void printlnArray(long[] array, String separator, java.util.function.LongUnaryOperator map){
        int n = array.length;
        if(n==0){
            this.println();
            return;
        }
        for(int i=0; i<n-1; i++){
            this.print(map.applyAsLong(array[i]));
            this.print(separator);
        }
        this.println(map.applyAsLong(array[n-1]));
    }
    public void printlnArray(long[] array, java.util.function.LongUnaryOperator map){
        this.printlnArray(array, " ", map);
    }
    public <T> void printlnArray(T[] array, String separator){
        int n = array.length;
        if(n==0){
            this.println();
            return;
        }
        for(int i=0; i<n-1; i++){
            this.print(array[i].toString());
            this.print(separator);
        }
        this.println(array[n-1].toString());
    }
    public <T> void printlnArray(T[] array){
        this.printlnArray(array, " ");
    }
    public <T> void printlnArray(T[] array, String separator, java.util.function.UnaryOperator<T> map){
        int n = array.length;
        if(n==0){
            this.println();
            return;
        }
        for(int i=0; i<n-1; i++){
            this.print(map.apply(array[i]).toString());
            this.print(separator);
        }
        this.println(map.apply(array[n-1]).toString());
    }
    public <T> void printlnArray(T[] array, java.util.function.UnaryOperator<T> map){
        this.printlnArray(array, " ", map);
    }
}
