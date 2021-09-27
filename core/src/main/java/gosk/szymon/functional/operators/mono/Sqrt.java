package gosk.szymon.functional.operators.mono;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WIP;
import gosk.szymon.functional.operators.Evaluable;
import gosk.szymon.functional.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Sqrt implements Operator, Evaluable {

    @Override
    public Operator apply(Operator operator) {
        return null;
    }

    @WIP
    @Override
    public Number evaluate(Number number) {
        if(number instanceof BigInteger bigInteger) {
            return bigInteger.sqrt();
        } else {
            return null;
        }
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "SQRT";
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

}
