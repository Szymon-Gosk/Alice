package gosk.szymon.computational.facades;

import gosk.szymon.computational.operators.numbers.Int;
import gosk.szymon.computational.operators.numbers.Real;
import gosk.szymon.computational.structural.ExpressionBuilder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Numbers {

    public static @NotNull ExpressionBuilder Int(@NotNull Integer i) {
        return Int(i + "");
    }

    public static @NotNull ExpressionBuilder Int(@NotNull Long l) {
        return Int(l + "");
    }

    public static @NotNull ExpressionBuilder Int(@NotNull String s) {
        return Int(new BigInteger(s.replaceAll("(\\.0+)$", "")));
    }

    public static @NotNull ExpressionBuilder Int(@NotNull BigInteger v) {
        return new ExpressionBuilder(new Int(v));
    }

    public static @NotNull ExpressionBuilder Real(@NotNull Integer i) {
        return Int(i);
    }

    public static @NotNull ExpressionBuilder Real(@NotNull Long l) {
        return Int(l);
    }

    public static @NotNull ExpressionBuilder Real(@NotNull BigInteger v) {
        return Int(v);
    }

    public static @NotNull ExpressionBuilder Real(@NotNull Float f) {
        return Real(f + "");
    }

    public static @NotNull ExpressionBuilder Real(@NotNull Double d) {
        return Real(d + "");
    }

    public static @NotNull ExpressionBuilder Real(@NotNull String s) {
        return Real(new BigDecimal(s));
    }

    public static @NotNull ExpressionBuilder Real(@NotNull BigDecimal v) {
        return new ExpressionBuilder(new Real(v));
    }

}
