package gosk.szymon.functional.structural;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static gosk.szymon.functional.facades.Values.Frac;
import static gosk.szymon.functional.facades.Values.Int;
import static gosk.szymon.functional.facades.Values.Sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FunctionTreeTest {

    @Test
    void shouldReturnCorrectTreeSize() {
        assertEquals(1, generateSingletonTree().size());
        assertEquals(4, generateSmallTree().size());
        assertEquals(9, generateMediumTree().size());
        assertEquals(17, generateLargeTree().size());
    }

    @Test
    void shouldReturnCorrectTreeHeight() {
        assertEquals(0, generateSingletonTree().height());
        assertEquals(2, generateSmallTree().height());
        assertEquals(3, generateMediumTree().height());
        assertEquals(6, generateLargeTree().height());
    }

    @Test
    void shouldProperlyCompareTreesWhenCheckingEquality() {
        assertEquals(generateSingletonTree(), generateSingletonTree());
        assertEquals(generateSmallTree(), generateSmallTree());
        assertEquals(generateMediumTree(), generateMediumTree());
        assertEquals(generateLargeTree(), generateLargeTree());

        assertNotEquals(generateSingletonTree(), generateMediumTree());
        assertNotEquals(generateSingletonTree(), Int(3).getTree());
        assertNotEquals(generateLargeTree(), generateMediumTree());
        assertNotEquals(generateSmallTree(), generateMediumTree());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void shouldNotAcceptNullValuesUponCreation() {
        assertThrows(IllegalArgumentException.class, () -> new FunctionTree(null));
    }

    private @NotNull FunctionTree generateSingletonTree() {
        return Int(2).getTree();
    }

    private @NotNull FunctionTree generateSmallTree() {
        return Frac(Sqrt(Int(2)), Int(3)).getTree();
    }

    private @NotNull FunctionTree generateMediumTree() {
        return Int(2).add(Sqrt(Int(3))).divide(Sqrt(Int(5)).subtract(Int(3))).getTree();
    }

    private @NotNull FunctionTree generateLargeTree() {
        return Sqrt(Int(2).divide(Int(4).sqrt()).subtract(Sqrt(Frac(Int(2).multiply(Int(3)), Int(2))).add(Sqrt(Int(2).divide(Int(10)))))).getTree();
    }

}