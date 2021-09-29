package gosk.szymon.functional.operators.numbers;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Decimal implements Operator {

    private final BigDecimal value;

    public Decimal(String value) {
        this.value = new BigDecimal(value);
    }

    public Decimal(long value) {
        this.value = new BigDecimal(value);
    }

    public Decimal(double value) {
        this.value = new BigDecimal(value);
    }

    public Decimal(BigInteger value) {
        this.value = new BigDecimal(value);
    }

    public Decimal(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Number evaluate(Number number) {
        return value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Decimal{value=" + value + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decimal that = (Decimal) o;
        return this.value.equals(that.value);
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "DECIMAL";
    }

}
