package gosk.szymon.computational.structural;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static gosk.szymon.computational.facades.Functions.Exp;
import static gosk.szymon.computational.facades.Functions.Frac;
import static gosk.szymon.computational.facades.Functions.Ln;
import static gosk.szymon.computational.facades.Functions.Sqrt;
import static gosk.szymon.computational.facades.Numbers.Int;
import static gosk.szymon.computational.facades.Numbers.Real;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ExpressionTest {

    @Test
    void shouldReturnCorrectTreeSize() {
        assertEquals(1, singletonIntExpression().size());
        assertEquals(1, singletonRealExpression().size());
        assertEquals(4, smallExpression().size());
        assertEquals(9, mediumExpression().size());
        assertEquals(17, largeExpression().size());
    }

    @Test
    void shouldReturnCorrectTreeHeight() {
        assertEquals(0, singletonIntExpression().height());
        assertEquals(0, singletonRealExpression().height());
        assertEquals(2, smallExpression().height());
        assertEquals(3, mediumExpression().height());
        assertEquals(6, largeExpression().height());
    }

    @Test
    void shouldProperlyCompareTreesWhenCheckingEquality() {
        assertEquals(singletonIntExpression(), singletonIntExpression());
        assertEquals(singletonRealExpression(), singletonRealExpression());
        assertEquals(smallExpression(), smallExpression());
        assertEquals(mediumExpression(), mediumExpression());
        assertEquals(largeExpression(), largeExpression());

        assertNotEquals(singletonIntExpression(), singletonRealExpression());
        assertNotEquals(singletonIntExpression(), Int(3).getExpression());
        assertNotEquals(singletonRealExpression(), Real(3.2).getExpression());
        assertNotEquals(largeExpression(), mediumExpression());
        assertNotEquals(smallExpression(), mediumExpression());
    }

    private @NotNull Expression singletonIntExpression() {
        return Int(2).getExpression();
    }

    private @NotNull Expression singletonRealExpression() {
        return Real(2.5).getExpression();
    }

    private @NotNull Expression smallExpression() {
        return Frac(Sqrt(Int(2)), Int(3)).getExpression();
    }

    private @NotNull Expression mediumExpression() {
        return Int(2).add(Sqrt(Real(3.2))).divide(Sqrt(Int("2")).subtract(Real("3.67"))).getExpression();
    }

    private @NotNull Expression largeExpression() {
        return Sqrt(Int(2).divide(Real(new BigDecimal("2.21")).sqrt()).subtract(Ln(Frac(Int(6)
                .multiply(Real(1.5f)), Int(2))).add(Exp(Int(2).divide(Real(10)))))).getExpression();
    }

}
