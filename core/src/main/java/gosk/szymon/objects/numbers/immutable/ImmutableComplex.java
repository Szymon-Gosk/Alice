package gosk.szymon.objects.numbers.immutable;

import gosk.szymon.objects.numbers.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImmutableComplex extends Complex {

    public static final ImmutableComplex ZERO = new ImmutableComplex();
    public static final ImmutableComplex ONE = new ImmutableComplex(1, 0);
    public static final ImmutableComplex NEGATIVE = new ImmutableComplex(-1, -1);
    public static final ImmutableComplex I = new ImmutableComplex(0, 1);

    public ImmutableComplex(final double re, final double im) {
        super(re, im);
    }

    public ImmutableComplex() {
        super();
    }

    @Override
    public ImmutableComplex conjugate() {
        return new ImmutableComplex(re, -im);
    }

    @Override
    public ImmutableComplex reciprocal() {
        if (isZero()) throw new ArithmeticException("Cannot divide by 0");
        final double denominator = re * re + im * im;
        return new ImmutableComplex(re / denominator, -im / denominator);
    }

    @Override
    public ImmutableComplex add(@NotNull Complex z) {
        return new ImmutableComplex(re + z.re(), im + z.im());
    }

    @Override
    public ImmutableComplex subtract(@NotNull Complex z) {
        return new ImmutableComplex(re - z.re(), im - z.im());
    }

    @Override
    public ImmutableComplex multiply(@NotNull Complex z) {
        return new ImmutableComplex(re * z.re() - im * z.im(), re * z.im() + im * z.re());
    }

    @Override
    public ImmutableComplex divide(@NotNull Complex z) {
        if (z.isZero()) throw new ArithmeticException("Cannot divide by 0");
        final double denominator = z.re() * z.re() + z.im() * z.im();
        return new ImmutableComplex((re * z.re() + im * z.im()) / denominator, (im * z.re() - re * z.im()) / denominator);
    }

    @Override
    public List<Complex> sqrt() {
        return null;
    }

    @Override
    public ImmutableComplex exp() {
        return null;
    }

    @Override
    public ImmutableComplex exp(Complex base) {
        return null;
    }

    @Override
    public ImmutableComplex ln() {
        return null;
    }

    @Override
    public ImmutableComplex log(Complex base) {
        return null;
    }

    @Override
    public ImmutableComplex pow(Complex exponent) {
        return null;
    }

    @Override
    public String toString() {
        return "ImmutableComplex{" + "re=" + re + ", im=" + im + '}';
    }

}
