package gosk.szymon.computational.structural;

import org.junit.jupiter.api.Test;

import static gosk.szymon.computational.facades.Numbers.Int;
import static gosk.szymon.computational.facades.Numbers.Real;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionBuilderTest {

    @Test
    void shouldProperlyConvertNumericalValues() {
        assertEquals(Int(3), Int(3L));
        assertEquals(Int(3), Int("3"));
        assertEquals(Int(3), Int("3.0"));
        assertEquals(Int(3), Int(3L));
        assertEquals(Int(3), Real(3));
        assertEquals(Int(3), Real(3L));
        assertEquals(Int(3), Real(3.0f));
        assertEquals(Int(3), Real(3.0d));
        assertEquals(Int(3), Real("3"));
        assertEquals(Int(3), Real("3.0"));
    }

}