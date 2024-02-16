import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ac_library.AssertUtil;
import org.junit.jupiter.api.Test;

class AssertUtilTest {
    // methods test
    @Test
    void testCheck() {
        AssertUtil.check(true);

        AssertionError error = assertThrows(AssertionError.class, () -> {
            AssertUtil.check(false);
        });
        assertEquals("Check failed. flag is false.", error.getMessage());
    }

    @Test
    void testCheckWithErrorMessage() {
        AssertUtil.check(true, "This is a custom error message");

        AssertionError error = assertThrows(AssertionError.class, () -> {
            AssertUtil.check(false, "This is a custom error message");
        });
        assertEquals("This is a custom error message", error.getMessage());
    }

    @Test
    void testCheckRange() {
        AssertUtil.checkRange(5, 0, 10);

        AssertionError error = assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(15, 0, 10);
        });
        assertEquals("Check failed. Val must be 0 <= val < 10. val = 15.", error.getMessage());
    }

    @Test
    void testCheckRangeWithUpperBound() {
        AssertUtil.checkRange(5, 10);

        AssertionError error = assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(15, 10);
        });
        assertEquals("Check failed. Val must be 0 <= val < 10. val = 15.", error.getMessage());
    }

    // Additional Tests
    @Test
    void testCheckRangeBoundary() {
        AssertUtil.checkRange(0, 10);
        AssertUtil.checkRange(9, 10);
        AssertUtil.checkRange(0, 0, 10);
        AssertUtil.checkRange(9, 0, 10);

        assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(10, 10);
        });
        assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(11, 10);
        });
        assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(10, 0, 10);
        });
        assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(11, 0, 10);
        });
        assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(-1, 10);
        });
        assertThrows(AssertionError.class, () -> {
            AssertUtil.checkRange(-1, 0, 10);
        });
    }
}
