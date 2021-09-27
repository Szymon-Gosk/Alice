package gosk.szymon.functional.operators;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.WIP;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public interface BiOperator extends BiFunction<Operator, Operator, Operator> {

    @Override
    Operator apply(Operator op1, Operator op2);

    @WIP
    default Number evaluate(Number n1, Number n2) {
        return null;
    }

    default @NotNull Operator bridge(@NotNull Operator op1, @NotNull Operator op2) {
        return (x) -> this.apply(op1.apply(x), op2.apply(x));
    }

    default @NotNull BiOperator andThen(@NotNull Operator after) {
        return (x, y) -> after.apply(this.apply(x, y));
    }

    @Temporary
    default @NotNull String debugString() {
        return "BI_OPERATOR";
    }
    
}
