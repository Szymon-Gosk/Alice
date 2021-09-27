package gosk.szymon.objects.numbers.mutable;

import gosk.szymon.objects.numbers.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MutableComplex extends Complex {

    public static final MutableComplex ZERO = new MutableComplex();
    public static final MutableComplex ONE = new MutableComplex(1, 0);
    public static final MutableComplex NEGATIVE = new MutableComplex(-1, -1);
    public static final MutableComplex I = new MutableComplex(0, 1);

    public MutableComplex(final double re, final double im) {
        super(re, im);
    }

    public MutableComplex() {
        super();
    }

    @Override
    public MutableComplex conjugate() {
        im *= -1;
        return this;
    }

    @Override
    public MutableComplex reciprocal() {
        if (isZero()) throw new ArithmeticException("Cannot divide by 0");
        final double denominator = re * re + im * im;
        re /= denominator;
        im /= -denominator;
        return this;
    }

    @Override
    public MutableComplex add(@NotNull Complex z) {
        re += z.re();
        im += z.im();
        return this;
    }

    @Override
    public MutableComplex subtract(@NotNull Complex z) {
        re -= z.re();
        im -= z.im();
        return this;
    }

    @Override
    public MutableComplex multiply(@NotNull Complex z) {
        final double oldRe = re;
        re = re * z.re() - im * z.im();
        im = oldRe * z.im() + im * z.re();
        return this;
    }

    @Override
    public MutableComplex divide(@NotNull Complex z) {
        if (z.isZero()) throw new ArithmeticException("Cannot divide by 0");
        final double denominator = z.re() * z.re() + z.im() * z.im();
        final double oldRe = re;
        re = (re * z.re() + im * z.im()) / denominator;
        im = (im * z.re() - oldRe * z.im()) / denominator;
        return this;
    }

    @Override
    public List<Complex> sqrt() {
        return null;
    }

    @Override
    public MutableComplex exp() {
        return null;
    }

    @Override
    public MutableComplex exp(Complex base) {
        return null;
    }

    @Override
    public MutableComplex ln() {
        return null;
    }

    @Override
    public MutableComplex log(Complex base) {
        return null;
    }

    @Override
    public MutableComplex pow(Complex exponent) {
        return null;
    }

    @Override
    public String toString() {
        return "MutableComplex{" + "re=" + re + ", im=" + im + '}';
    }

}
