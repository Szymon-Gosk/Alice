package gosk.szymon.functional.operators;

import org.jetbrains.annotations.NotNull;

public interface Operator {

    Number evaluate(Number number);

    default @NotNull String debugString() {
        return "OPERATOR";
    }

}
