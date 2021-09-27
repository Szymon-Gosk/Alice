package gosk.szymon.functional.structural;

import org.junit.jupiter.api.Test;

import static gosk.szymon.functional.facades.Values.Frac;
import static gosk.szymon.functional.facades.Values.Int;
import static gosk.szymon.functional.facades.Values.Sqrt;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueBuilderTest {

    @Test
    public void equivalentFacadesAndMethodsShouldGenerateSameResults() {
        ValueBuilder result1 = Int(2).add(Sqrt(Int(3))).divide(Sqrt(Int(5)).subtract(Int(3)));
        ValueBuilder result2 = Frac(Int(2).add(Sqrt(Int(3))), Sqrt(Int(5)).subtract(Int(3)));

        assertEquals(result1, result2);
    }

}