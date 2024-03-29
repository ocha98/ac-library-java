package ac_library;

public final class ArrayUtils {
    public static int lowerBound(int[] a, int x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid] >= x) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static int lowerBound(long[] a, long x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid] >= x) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static int lowerBound(double[] a, double x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid] >= x) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] a, T x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid].compareTo(x) >= 0) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static int upperBound(int[] a, int x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid] > x) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static int upperBound(long[] a, long x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid] > x) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static int upperBound(double[] a, double x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid] > x) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static <T extends Comparable<T>> int upperBound(T[] a, T x) {
        int ok = a.length, ng = -1;
        while (ok - ng > 1) {
            int mid = (ok+ng) >> 1;
            if (a[mid].compareTo(x) > 0) {
                ok = mid;
            } else {
                ng = mid;
            }
        }
        return ok;
    }

    public static int[] dedup(int[] a) {
        int n = a.length;
        if (n <= 1) return a.clone();

        int cnt = 1;
        for (int i = 1;i < n; ++i) {
            if (a[i-1] != a[i]) {
                ++cnt;
            }
        }

        int[] b = new int[cnt];
        b[0] = a[0];
        int dist = 1;
        for (int i = 1;i < n; ++i) {
            if (a[i-1] != a[i]) {
                b[dist++] = a[i];
            }
        }
        return b;
    }

    public static long[] dedup(long[] a) {
        int n = a.length;
        if (n <= 1) return a.clone();

        int cnt = 1;
        for (int i = 1;i < n; ++i) {
            if (a[i-1] != a[i]) {
                ++cnt;
            }
        }

        long[] b = new long[cnt];
        b[0] = a[0];
        int dist = 1;
        for (int i = 1;i < n; ++i) {
            if (a[i-1] != a[i]) {
                b[dist++] = a[i];
            }
        }
        return b;
    }
}
