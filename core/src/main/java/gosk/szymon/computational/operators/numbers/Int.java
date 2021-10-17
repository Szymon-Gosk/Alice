package gosk.szymon.computational.operators.numbers;

import gosk.szymon.computational.Development;
import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Int implements Operator {

    private final BigInteger value;

    public Int(BigInteger value) {
        this.value = value;
    }

    @Override
    public @NotNull Number evaluate(@NotNull Number @NotNull... arguments) {
        if(arguments.length != getOrder()) {
            throw new IllegalArgumentException("Number of arguments must be equal to order of this operator");
        }
        return value;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String toString() {
        return "Int{value=" + value + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Int that)) return false;
        return this.value.equals(that.value);
    }

    @Override
    @Development
    public @NotNull String debugString() {
        return "INT(" + value + ")";
    }

}
