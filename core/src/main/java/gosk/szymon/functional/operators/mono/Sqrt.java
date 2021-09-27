package gosk.szymon.functional.operators.mono;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WorkInProgress;
import gosk.szymon.functional.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Sqrt implements Operator {

    @WorkInProgress
    @Override
    public Number evaluate(Number number) {
        if(number instanceof BigInteger bigInteger) {
            return bigInteger.sqrt();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Sqrt{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "SQRT";
    }

}
