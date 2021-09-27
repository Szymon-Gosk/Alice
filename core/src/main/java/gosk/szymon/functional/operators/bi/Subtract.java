package gosk.szymon.functional.operators.bi;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WIP;
import gosk.szymon.functional.operators.BiEvaluable;
import gosk.szymon.functional.operators.BiOperator;
import gosk.szymon.functional.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Subtract implements BiOperator, BiEvaluable {

    @Override
    public Operator apply(Operator op1, Operator op2) {
        return null;
    }

    @WIP
    @Override
    public Number evaluate(Number n1, Number n2) {
        if(n1 instanceof BigInteger i1 && n2 instanceof BigInteger i2) {
            return i1.subtract(i2);
        }
        return null;
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "SUBTRACT";
    }

    @Override
    public String toString() {
        return "Subtract{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

}
