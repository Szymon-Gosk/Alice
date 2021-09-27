package gosk.szymon.functional.operators.bi;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WorkInProgress;
import gosk.szymon.functional.operators.BiOperator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Add implements BiOperator {

    @WorkInProgress
    @Override
    public Number evaluate(Number n1, Number n2) {
        if(n1 instanceof BigInteger i1 && n2 instanceof BigInteger i2) {
            return i1.add(i2);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Add{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "ADD";
    }

}
