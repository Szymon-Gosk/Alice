package gosk.szymon.computational.operators;

import gosk.szymon.computational.Development;
import org.jetbrains.annotations.NotNull;

/**
 * Operator containing logic of equivalent math operator. An operator can be a concrete value - an integer for instance
 * - or a math operator (function) like square root, logarithm, etc.
 * <p>
 * Order of an operator is the number of arguments it accepts. Numerical values, like integer or decimal, has order of
 * <code>0</code>. Functions like square root or natural logarithm has order of <code>1</code>. Functions like addition
 * has order of <code>2</code>.
 *
 * @since 1.0
 */
public interface Operator {

    /**
     * Evaluates this operator by applying its logic to a given collection of numbers (arguments), returning a number after the
     * logic has been applied.
     *
     * @param arguments collection of numbers compliant with order of this operator.
     * @return evaluated number after this operator has been applied.
     */
    @NotNull Number evaluate(@NotNull Number @NotNull... arguments);

    /**
     * Returns order of this operator - order is equivalent to the number of arguments this operator accepts.
     *
     * @return order of this operator.
     */
    int getOrder();

    /**
     * Returns <code>true</code> if this operator represents concrete number value (for example <code>Int(2)</code>
     * will be a number while <code>Sqrt(...)</code> will not).
     *
     * @return <code>true</code> if this operator represents number, <code>false</code> otherwise.
     */
    boolean isNumber();

    /**
     * <b>Early development only!</b> Returns debug string for development purposes. To be deleted later.
     *
     * @return debug string of this operator.
     */
    @Development
    default @NotNull String debugString() {
        return "OPERATOR";
    }

}
