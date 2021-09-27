package gosk.szymon.objects.numbers;

public abstract class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if(denominator == 0) throw new ArithmeticException("Cannot divide by 0");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(0, 1);
    }

    public abstract Complex reciprocal();

    public abstract Fraction add(Fraction z);

    public abstract Fraction subtract(Fraction z);

    public abstract Fraction multiply(Fraction z);

    public abstract Fraction divide(Fraction z);

    public int floor() {
        return 0;
    }

    public int ceil() {
        return 0;
    }

    public boolean isWhole() {
        return false;
    }

}
