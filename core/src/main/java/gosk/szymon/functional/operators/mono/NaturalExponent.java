package gosk.szymon.functional.operators.mono;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WorkInProgress;
import gosk.szymon.functional.operators.Operator;
import org.jetbrains.annotations.NotNull;

public class NaturalExponent implements Operator {

    @WorkInProgress
    @Override
    public Number evaluate(Number number) {
        return null;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public String toString() {
        return "NaturalExponent{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "EXPL";
    }

}
