package gosk.szymon.computational.operators.mono;

import gosk.szymon.computational.Development;
import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Sqrt implements Operator {

    @Contract(value = " -> new", pure = true)
    public static @NotNull Sqrt get() {
        return new Sqrt();
    }

    @Override
    public @NotNull Number evaluate(@NotNull Number @NotNull... arguments) {
        if(arguments.length != getOrder()) {
            throw new IllegalArgumentException("Number of arguments must be equal to order of this operator");
        }
        if(arguments[0] instanceof BigInteger bigInteger) {
            if(bigInteger.signum() == -1) {
                // TODO complex case - would cast this exception regardless
                throw new ArithmeticException("Sqrt of negative values not implemented");
            }
            // TODO check if squares nicely to Int - if not return Decimal instead
            return bigInteger.sqrt();
        } else if(arguments[0] instanceof BigDecimal bigDecimal){
            if(bigDecimal.signum() == -1) {
                // TODO complex case - would cast this exception regardless
                throw new ArithmeticException("Sqrt of negative values not implemented");
            }
            // TODO check if is Int - if so check Int case
            return bigDecimal.sqrt(new MathContext(bigDecimal.precision(), RoundingMode.HALF_UP));
        } else {
            throw new IllegalArgumentException("Only Big values can be evaluated");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public String toString() {
        return "Sqrt{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Sqrt;
    }

    @Override
    @Development
    public @NotNull String debugString() {
        return "SQRT";
    }

}
