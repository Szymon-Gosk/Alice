package gosk.szymon.functional.operators;

import gosk.szymon.functional.WIP;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

public interface Operator extends Function<Operator, Operator> {

    @Override
    Operator apply(Operator operator);

    @WIP
    default Number evaluate(Number number) {
        return null;
    }

    default @NotNull Operator bridge(@NotNull Operator operator) {
        return compose(operator);
    }

    default @NotNull Operator compose(@NotNull Operator before) {
        Objects.requireNonNull(before);
        return (x) -> this.apply(before.apply(x));
    }

    default @NotNull Operator andThen(@NotNull Operator after) {
        Objects.requireNonNull(after);
        return (x) -> after.apply(this.apply(x));
    }

    default @NotNull String debugString() {
        return "OPERATOR";
    }

    static Operator identity() {
        return (x) -> x;
    }

}
