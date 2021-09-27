package gosk.szymon.functional.operators.numbers;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class Int implements Operator {

    private final BigInteger value;

    public Int(String value) {
        this.value = new BigInteger(value);
    }

    public Int(long value) {
        this.value = new BigInteger("" + value);
    }

    public Int(BigInteger value) {
        this.value = value;
    }

    @Override
    public Int apply(Operator operator) {
        return this;
    }

    @Override
    public Number evaluate(Number number) {
        return value;
    }

    public BigInteger getValue() {
        return value;
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "INT";
    }

    @Override
    public String toString() {
        return "Int{value="+ value + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Int that = (Int) o;
        return this.value.equals(that.value);
    }

}
