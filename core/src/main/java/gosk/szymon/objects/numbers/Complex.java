package gosk.szymon.objects.numbers;

import gosk.szymon.general.Renderable;

import java.util.List;
import java.util.Objects;

public abstract class Complex implements Renderable {

    protected double re;
    protected double im;

    public Complex(final double re, final double im) {
        this.re = re;
        this.im = im;
    }

    public Complex() {
        this(0, 0);
    }

    public abstract Complex conjugate();

    public abstract Complex reciprocal();

    public abstract Complex add(Complex z);

    public abstract Complex subtract(Complex z);

    public abstract Complex multiply(Complex z);

    public abstract Complex divide(Complex z);

    public abstract List<Complex> sqrt();

    public abstract Complex exp();

    public abstract Complex exp(Complex base);

    public abstract Complex ln();

    public abstract Complex log(Complex base);

    public abstract Complex pow(Complex exponent);

    public double re() {
        return re;
    }

    public double im() {
        return im;
    }

    public double mod() {
        return Math.pow(re * re + im * im, 0.5);
    }

    public double arg() {
        return Math.atan2(im, re);
    }

    public boolean isZero() {
        return im == 0 && re == 0;
    }

    public boolean isReal() {
        return im == 0;
    }

    public boolean isImaginary() {
        return re == 0;
    }

    @Override
    public String render() {
        return re + "+i\\cdot " + im;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex that = (Complex) o;
        return Double.compare(that.re, re) == 0 && Double.compare(that.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }

    @Override
    public String toString() {
        return "Complex{" + "re=" + re + ", im=" + im + '}';
    }

}
