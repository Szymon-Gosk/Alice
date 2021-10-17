package gosk.szymon.computational.facades;

import gosk.szymon.computational.structural.ExpressionBuilder;
import org.jetbrains.annotations.NotNull;

public class Functions {

    public static @NotNull ExpressionBuilder Frac(@NotNull ExpressionBuilder num, @NotNull ExpressionBuilder den) {
        return num.divide(den);
    }

    public static @NotNull ExpressionBuilder Sqrt(@NotNull ExpressionBuilder of) {
        return of.sqrt();
    }

    public static @NotNull ExpressionBuilder Ln(@NotNull ExpressionBuilder of) {
        return of.ln();
    }

    public static @NotNull ExpressionBuilder Exp(@NotNull ExpressionBuilder of) {
        return of.exp();
    }

}
