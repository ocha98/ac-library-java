package ac_library;

public class StaticRollingHash implements Comparable<StaticRollingHash> {
    private static final long BASE = new java.util.Random().nextInt(1, 1001) + Character.MAX_VALUE;
    private static final long MASK30 = (1L << 30) - 1;
    private static final long MASK31 = (1L << 31) - 1;
    private static final long MOD = (1L << 61) - 1;
    private static final long MASK61 = MOD;
    private final long[] hash;
    private final String string;

    /**
     * 引数の文字列を元にStaticRollingHashを構築します。
     *
     * @param str 元となるString
     */
    public StaticRollingHash(final String str) {
        string = str;
        hash = new long[str.length() + 1];
        roll();
    }

    /**
     * 引数のStaticRollingHashを複製します。
     *
     * @param th 複製元
     */
    public StaticRollingHash(final StaticRollingHash rh) {
        string = rh.toString();
        hash = rh.getHashTable();
    }

    /**
     * フィールドのstringを元にハッシュ値を計算します。
     */
    private void roll() {
        final int len = string.length();
        for (int i = 1; i <= len; ++i) {
            hash[i] = multiply(hash[i - 1], BASE) + string.charAt(i - 1) - ' ' + 1;
            if (MOD <= hash[i]) {
                hash[i] -= MOD;
            }
        }
    }

    /**
     * ハッシュテーブルを返します。
     */
    private long[] getHashTable() {
        return hash;
    }

    /**
     * long*long用の特別メソッドです。
     *
     * @param a 乗算する値
     * @param b 乗算される値
     *
     * @return a*b mod 2^61-1
     */
    private static long multiply(final long a, final long b) {
        final long au = a >> 31;
        final long ad = a & MASK31;
        final long bu = b >> 31;
        final long bd = b & MASK31;
        final long mid = ad * bu + au * bd;
        final long midu = mid >> 30;
        final long midd = mid & MASK30;
        return mod(au * bu * 2 + midu + (midd << 31) + ad * bd);
    }

    /**
     * x mod 2^61-1を計算します。
     *
     * @param x 元となる値
     *
     * @return x mod 2^61-1
     */
    private static long mod(final long x) {
        final long xu = x >> 61;
        final long xd = x & MASK61;
        long ans = xu + xd;
        if (MOD <= ans) {
            ans -= MOD;
        }
        return ans;
    }

    /**
     * ハッシュ値を計算します。
     *
     * @param l 左端
     * @param r 右端
     *
     * @return [l,r]のハッシュ値
     */
    public long getHash(final int l, final int r) {
        return (hash[r] - multiply(hash[l], modBasePow(r - l)) + MOD) % MOD;
    }

    /**
     * BASE^b mod 2^61-1を計算します。
     *
     * @param b 指数
     *
     * @return BASE^b mod 2^61-1
     */
    private static long modBasePow(long b) {
        long ans = 1;
        long a = BASE;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = multiply(ans, a);
            }
            a = multiply(a, a);
            b >>= 1;
        }
        return ans;
    }

    /**
     * 指定された区間と引数の指定された区間が等しいか返します。
     *
     * @param rh 比較対象のStaticRollingHash
     * @param l1 このStaticRollingHashの左端
     * @param r1 このStaticRollingHashの右端
     * @param l2 比較対象の左端
     * @param r2 比較対象の右端
     *
     * @return 等しい可能性があるならtrue、確実に異なるならfalse
     */
    public boolean equals(final StaticRollingHash rh, final int l1, final int r1, final int l2, final int r2) {
        if (r1 - l1 != r2 - l2) {
            return false;
        }
        return getHash(l1, r1) == rh.getHash(l2, r2);
    }

    /**
     * 指定された区間と引数の指定された区間が等しいか返します。
     *
     * @param str 比較対象のStaticRollingHash
     * @param l1 このStaticRollingHashの左端
     * @param r1 このStaticRollingHashの右端
     * @param l2 比較対象の左端
     * @param r2 比較対象の右端
     *
     * @return 等しい可能性があるならtrue、確実に異なるならfalse
     */
    public boolean equals(final String str, final int l1, final int r1, final int l2, final int r2) {
        if (r1 - l1 != r2 - l2) {
            return false;
        }
        for (int i = 0; i < r1 - l1; i++) {
            if (string.charAt(i + l1) != str.charAt(i + l2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * このStaticRollingHashの長さを返します。
     *
     * @return このStaticRollingHashの長さ
     */
    public int length() {
        return string.length();
    }

    /**
     * このStaticRollingHashの元となるStringのハッシュ値を返します。
     *
     * @return このStaticRollingHashの元となるStringのハッシュ値
     */
    @Override
    public int hashCode() {
        return (int) hash[string.length()];
    }

    /**
     * このStaticRollingHashの元となるStringを返します。
     *
     * @return このStaticRollingHashの元となるString
     */
    @Override
    public String toString() {
        return string;
    }

    /**
     * 引数と自身が等しいか返します。
     *
     * @param o 比較対象
     *
     * @return 等しい可能性があるならtrue、確実に異なるならfalse
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof final StaticRollingHash rh) {
            return equals(rh, 0, length(), 0, rh.length());
        }
        return false;
    }

    /**
     * 引数のStaticRollingHashの元となるStringと辞書順で比較します。
     *
     * @param rh the object to be compared.
     *
     * @return 辞書順で自身が小さいなら-1、等しいなら0、それ以外は1
     */
    @Override
    public int compareTo(StaticRollingHash rh) {
        return string.compareTo(rh.toString());
    }

    /**
     * 引数のStringと辞書順で比較します。
     *
     * @param str the object to be compared.
     *
     * @return 辞書順で自身が小さいなら-1、等しいなら0、それ以外は1
     */
    public int compareTo(String str) {
        return string.compareTo(str);
    }

    /**
     * このStaticRollingHashのi文字目を返します。
     *
     * @param i インデックス
     *
     * @return i番目の文字を表すchar
     */
    public char charAt(final int i) {
        return string.charAt(i);
    }

    /**
     * 引数のStaticRollingHashの元となるStringと大文字小文字を無視して辞書順で比較します。
     *
     * @param rh the object to be compared.
     *
     * @return 辞書順で自身が小さいなら-1、等しいなら0、それ以外は1
     */
    public int compareToIgnoreCase(final StaticRollingHash rh) {
        return string.compareToIgnoreCase(rh.toString());
    }

    /**
     * 引数のStringと大文字小文字を無視して辞書順で比較します。
     *
     * @param str the object to be compared.
     *
     * @return 辞書順で自身が小さいなら-1、等しいなら0、それ以外は1
     */
    public int compareToIgnoreCase(final String str) {
        return string.compareToIgnoreCase(str);
    }

    /**
     * このStaticRollingHashの連続部分列として引数のStaticRollingHashが存在するか判定します。
     *
     * @param rh 連続部分列として存在するか検査するStaticRollingHash
     *
     * @return 部分列として存在する可能性があるならtrue、確実に含まれていないならfalse
     */
    public boolean contains(final StaticRollingHash rh) {
        final long hash = rh.getHash(0, rh.length());
        final int len = length() - rh.length();
        for (int i = 0; i <= len; ++i) {
            if (hash == getHash(i, rh.length() + i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 引数のStringが連続部分列として存在するか判定します。
     *
     * @param str 存在判定をするString
     *
     * @return 連続部分列として存在するならtrue、それ以外はfalse
     */
    public boolean contains(final String str) {
        return indexOf(str) != -1;
    }

    /**
     * 引数の文字が先頭から何番目に存在するか返します。
     * 存在しない場合は-1を返します。
     *
     * @param ch 探索する文字を表すint
     *
     * @return 最も左側にある引数の文字のインデックス(無ければ - 1)
     */
    public int indexOf(final int ch) {
        return indexOf(ch, 0);
    }

    /**
     * 引数の文字を指定されたインデックス以降で探索します。
     * 存在しない場合は-1を返します。
     *
     * @param ch 探索する文字を表すint
     * @param fromIndex 探索開始位置
     *
     * @return 最も左側にある引数の文字のインデックス(無ければ - 1)
     */
    public int indexOf(final int ch, final int fromIndex) {
        final int len = length();
        for (int i = fromIndex; i < len; ++i) {
            if (string.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 引数の文字列が先頭から何番目に存在するか返します。
     * 存在しない場合は-1を返します。
     *
     * @param str 探索する文字列
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int indexOf(final String str) {
        return indexOf(str, 0);
    }

    /**
     * 引数の文字列を指定されたインデックス以降で探索します。
     * 存在しない場合は-1を返します。
     *
     * @param str 探索する文字列
     * @param fromIndex 探索開始位置
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int indexOf(final String str, final int fromIndex) {
        long hash = 0;
        for (final char c : str.toCharArray()) {
            hash = multiply(hash, BASE) + c - ' ' + 1;
            if (MOD <= hash) {
                hash -= MOD;
            }
        }
        final int len = length() - str.length();
        for (int i = fromIndex; i <= len; ++i) {
            if (hash == getHash(i, str.length() + i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 引数の文字列が先頭から何番目に存在するか返します。
     * 存在しない場合は-1を返します。
     *
     * @param rh 探索する文字列
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int indexOf(final StaticRollingHash rh) {
        return indexOf(rh, 0);
    }

    /**
     * 引数の文字列を指定されたインデックス以降で探索します。
     * 存在しない場合は-1を返します。
     *
     * @param str 探索する文字列
     * @param fromIndex 探索開始位置
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int indexOf(final StaticRollingHash rh, final int fromIndex) {
        final long hash = rh.getHash(0, rh.length());
        final int len = length() - rh.length();
        for (int i = fromIndex; i <= len; ++i) {
            if (hash == getHash(i, rh.length() + i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * このStaticRollingHashが表す文字列が空文字列か判定します。
     *
     * @return ""と等しいならtrue、それ以外はfalse
     */
    public boolean isEmpty() {
        return length() == 0;
    }

    /**
     * 引数の文字を指定されたインデックス以降で探索します。
     * 存在しない場合は-1を返します。
     *
     * @param ch 探索する文字を表すint
     * @param fromIndex 探索開始位置
     *
     * @return 最も右側にある引数の文字のインデックス(無ければ - 1)
     */
    public int lastIndexOf(final int ch, final int fromIndex) {
        for (int i = fromIndex; i >= 0; --i) {
            if (string.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 引数の文字が先頭から何番目に存在するか返します。
     * 存在しない場合は-1を返します。
     *
     * @param ch 探索する文字を表すint
     *
     * @return 最も左側にある引数の文字のインデックス(無ければ - 1)
     */
    public int lastIndexOf(final int ch) {
        return lastIndexOf(ch, length() - 1);
    }

    /**
     * 引数の文字が先頭から何番目に存在するか返します。
     * 存在しない場合は-1を返します。
     *
     * @param str 探索する文字列
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int lastIndexOf(final String str) {
        return indexOf(str, 0);
    }

    /**
     * 引数の文字列を指定されたインデックス以降で探索します。
     * 存在しない場合は-1を返します。
     *
     * @param str 探索する文字列
     * @param fromIndex 探索開始位置
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int lastIndexOf(final String str, final int fromIndex) {
        long hash = 0;
        for (final char c : str.toCharArray()) {
            hash = multiply(hash, BASE) + c - ' ' + 1;
            if (MOD <= hash) {
                hash -= MOD;
            }
        }
        for (int i = Math.min(fromIndex, length() - str.length()); i >= 0; --i) {
            if (hash == getHash(i, str.length() + i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 引数の文字が先頭から何番目に存在するか返します。
     * 存在しない場合は-1を返します。
     *
     * @param rh 探索する文字列
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int lastIndexOf(final StaticRollingHash rh) {
        return indexOf(rh, 0);
    }

    /**
     * 引数の文字列を指定されたインデックス以降で探索します。
     * 存在しない場合は-1を返します。
     *
     * @param str 探索する文字列
     * @param fromIndex 探索開始位置
     *
     * @return 最も左側にある引数の文字列のインデックス(無ければ - 1)
     */
    public int lastIndexOf(final StaticRollingHash rh, final int fromIndex) {
        final long hash = rh.getHash(0, rh.length());
        for (int i = Math.min(fromIndex, length() - rh.length()); i >= 0; --i) {
            if (hash == getHash(i, rh.length() + i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 引数のbooleanを表す文字列を元にStaticRollingHashを構築します。
     *
     * @param b 元となるboolean
     *
     * @return 引数のbooleanを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final boolean b) {
        return new StaticRollingHash(b ? "true" : "false");
    }

    /**
     * 引数のcharを元にStaticRollingHashを構築します。
     *
     * @param c 元となるchar
     *
     * @return 引数のcharを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final char c) {
        return new StaticRollingHash("" + c);
    }

    /**
     * 引数のchar[]を表す文字列を元にStaticRollingHashを構築します。
     *
     * @param c 元となるchar[]
     *
     * @return 引数のchar[]を表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final char[] c) {
        return new StaticRollingHash(String.valueOf(c, 0, c.length));
    }

    /**
     * 引数のchar[]を表す文字列の、引数の区間を元にStaticRollingHashを構築します。
     *
     * @param c 元となるchar[]
     * @param offset 左端
     * @param count 文字数
     *
     * @return 引数のchar[]の引数の区間を表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final char[] c, final int offset, final int count) {
        return new StaticRollingHash(String.valueOf(c, offset, count));
    }

    /**
     * 引数のdoubleを表す文字列を元にStaticRollingHashを構築します。
     *
     * @param d 元となるdouble
     *
     * @return 引数のdoubleを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final double d) {
        return new StaticRollingHash(String.valueOf(d));
    }

    /**
     * 引数のfloatを表す文字列を元にStaticRollingHashを構築します。
     *
     * @param f 元となるfloat
     *
     * @return 引数のfloatを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final float f) {
        return new StaticRollingHash(String.valueOf(f));
    }

    /**
     * 引数のintを表す文字列を元にStaticRollingHashを構築します。
     *
     * @param i 元となるint
     *
     * @return 引数のintを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final int i) {
        return new StaticRollingHash(String.valueOf(i));
    }

    /**
     * 引数のlongを表す文字列を元にStaticRollingHashを構築します。
     *
     * @param l 元となるlong
     *
     * @return 引数のlongを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final long l) {
        return new StaticRollingHash(String.valueOf(l));
    }

    /**
     * 引数のObjectを表す文字列を元にStaticRollingHashを構築します。
     *
     * @param obj 元となるObject
     *
     * @return 引数のObjectを表すStaticRollingHash
     */
    public static StaticRollingHash valueOf(final Object obj) {
        return new StaticRollingHash(String.valueOf(obj));
    }
}
