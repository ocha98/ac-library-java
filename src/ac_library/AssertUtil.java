package ac_library;

public final class AssertUtil {
    // if flag is false, throw AssertionError
    public static void check(boolean flag) {
        check(flag, "Check failed. flag is false.");
    }

    // if flag is false, throw AssertionError with err_msg
    public static void check(boolean flag, String err_msg) {
        if (!flag) {
            throw new AssertionError(err_msg);
        }
    }

    // check l <= val && val < r
    public static void checkRange(int val, int l, int r) {
        check(l <= val && val < r, String.format("Check failed. Val must be %d <= val < %d. val = %d.", l, r, val));
    }

    // check 0 <= val && val < r
    public static void checkRange(int val, int r) {
        checkRange(val, 0, r);
    }
}
