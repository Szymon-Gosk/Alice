package gosk.szymon.computational.operators.mono;

import gosk.szymon.computational.Development;
import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class NaturalExponent implements Operator {

    @Contract(value = " -> new", pure = true)
    public static @NotNull NaturalExponent get() {
        return new NaturalExponent();
    }

    @Override
    public @NotNull Number evaluate(@NotNull Number @NotNull... arguments) {
        if(arguments.length != getOrder()) {
            throw new IllegalArgumentException("Number of arguments must be equal to order of this operator");
        }
        return new BigDecimal(0);
    }

    @Override
    public int getOrder() {
        return 1;
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
        return o instanceof NaturalExponent;
    }

    @Override
    @Development
    public @NotNull String debugString() {
        return "EXP";
    }

}