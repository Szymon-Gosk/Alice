package gosk.szymon.functional.facades;

import gosk.szymon.functional.structural.ValueBuilder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Values {

    private Values() {}

    public static @NotNull ValueBuilder Int(long v) {
        return Int("" + v);
    }

    public static @NotNull ValueBuilder Int(@NotNull String v) {
        return Int(new BigInteger(v));
    }

    public static @NotNull ValueBuilder Int(@NotNull BigInteger v) {
        return new ValueBuilder(v);
    }

    public static @NotNull ValueBuilder Decimal(long v) {
        return new ValueBuilder(new BigInteger("" + v));
    }

    public static @NotNull ValueBuilder Decimal(double v) {
        return Decimal("" + v);
    }

    public static @NotNull ValueBuilder Decimal(String s) {
        return Decimal(new BigDecimal(s));
    }

    public static @NotNull ValueBuilder Decimal(BigDecimal v) {
        return new ValueBuilder(v);
    }

    public static @NotNull ValueBuilder Decimal(BigInteger v) {
        return new ValueBuilder(v);
    }

    public static @NotNull ValueBuilder Frac(long a, long b) {
        return Frac(new BigInteger("" + a), new BigInteger("" + b));
    }

    public static @NotNull ValueBuilder Frac(@NotNull String num, @NotNull String den) {
        return Frac(new BigInteger(num), new BigInteger(den));
    }

    public static @NotNull ValueBuilder Frac(@NotNull BigInteger num, @NotNull BigInteger den) {
        return new ValueBuilder(num).divide(new ValueBuilder(den));
    }

    public static @NotNull ValueBuilder Frac(@NotNull ValueBuilder num, @NotNull ValueBuilder den) {
        return num.divide(den);
    }

    public static @NotNull ValueBuilder Sqrt(long of) {
        return Sqrt(new BigInteger("" + of));
    }

    public static @NotNull ValueBuilder Sqrt(@NotNull String of) {
        return Sqrt(new BigInteger(of));
    }

    public static @NotNull ValueBuilder Sqrt(@NotNull BigInteger of) {
        return new ValueBuilder(of).sqrt();
    }

    public static @NotNull ValueBuilder Sqrt(@NotNull ValueBuilder of) {
        return of.sqrt();
    }

}
