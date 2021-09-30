package gosk.szymon.functional.operators.numbers;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WorkInProgress;
import gosk.szymon.functional.operators.BiOperator;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ComplexDecimal implements BiOperator {

    private final BigDecimal real;
    private final BigDecimal imaginary;

    public ComplexDecimal(String real, String imaginary) {
        this.real = new BigDecimal(real);
        this.imaginary = new BigDecimal(imaginary);
    }

    public ComplexDecimal(long real, long imaginary) {
        this.real = new BigDecimal(real);
        this.imaginary = new BigDecimal(imaginary);
    }

    public ComplexDecimal(BigInteger real, BigInteger imaginary) {
        this.real = new BigDecimal(real);
        this.imaginary = new BigDecimal(imaginary);
    }

    public ComplexDecimal(BigDecimal real, BigDecimal imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @WorkInProgress
    @Override
    public Number evaluate(Number n1, Number n2) {
        return null;
    }

    public BigDecimal getReal() {
        return real;
    }

    public BigDecimal getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return "ComplexDecimal{real=" + real + ", imaginary=" + imaginary + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexDecimal that = (ComplexDecimal) o;
        return this.real.equals(that.real) && this.imaginary.equals(that.imaginary);
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "COMPLEX";
    }

}
