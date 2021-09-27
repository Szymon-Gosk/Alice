package gosk.szymon.functional.operators;

import gosk.szymon.functional.Temporary;
import org.jetbrains.annotations.NotNull;

public interface BiOperator {

    Number evaluate(Number n1, Number n2);

    @Temporary
    default @NotNull String debugString() {
        return "BI_OPERATOR";
    }
    
}
