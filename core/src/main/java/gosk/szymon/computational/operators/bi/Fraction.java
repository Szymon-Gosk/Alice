package gosk.szymon.computational.operators.bi;

import gosk.szymon.computational.Development;
import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Fraction implements Operator {

    @Contract(value = " -> new", pure = true)
    public static @NotNull Fraction get() {
        return new Fraction();
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
        return 2;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public String toString() {
        return "Fraction{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Fraction;
    }

    @Override
    @Development
    public @NotNull String debugString() {
        return "FRACTION";
    }

}
