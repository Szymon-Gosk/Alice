package gosk.szymon.functional.operators.numbers;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WorkInProgress;
import gosk.szymon.functional.operators.BiOperator;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class ComplexInt implements BiOperator {

    private final BigInteger real;
    private final BigInteger imaginary;

    public ComplexInt(String real, String imaginary) {
        this.real = new BigInteger(real);
        this.imaginary = new BigInteger(imaginary);
    }

    public ComplexInt(long real, long imaginary) {
        this.real = new BigInteger("" + real);
        this.imaginary = new BigInteger("" + imaginary);
    }

    public ComplexInt(BigInteger real, BigInteger imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @WorkInProgress
    @Override
    public Number evaluate(Number n1, Number n2) {
        return null;
    }

    public BigInteger getReal() {
        return real;
    }

    public BigInteger getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return "ComplexInt{real=" + real + ", imaginary=" + imaginary + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexInt that = (ComplexInt) o;
        return this.real.equals(that.real) && this.imaginary.equals(that.imaginary);
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return "COMPLEX_INT";
    }

}
