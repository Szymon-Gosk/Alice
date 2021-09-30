package gosk.szymon.functional.operators;

import org.jetbrains.annotations.NotNull;

public interface Operator {

    Number evaluate(Number number);

    boolean isNumber();

    default @NotNull String debugString() {
        return "OPERATOR";
    }

}
