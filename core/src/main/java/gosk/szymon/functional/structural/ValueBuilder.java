package gosk.szymon.functional.structural;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.operators.bi.Add;
import gosk.szymon.functional.operators.bi.Fraction;
import gosk.szymon.functional.operators.bi.Multiply;
import gosk.szymon.functional.operators.bi.Subtract;
import gosk.szymon.functional.operators.mono.Sqrt;
import gosk.szymon.functional.operators.numbers.Decimal;
import gosk.szymon.functional.operators.numbers.Int;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ValueBuilder {

    private final FunctionTree tree;

    public ValueBuilder(@NotNull BigInteger value) {
        tree = new FunctionTree(new Int(value));
    }

    public ValueBuilder(@NotNull BigDecimal value) {
        if(value.stripTrailingZeros().scale() <= 0) {
            tree = new FunctionTree(new Int(value.toBigInteger()));
        } else {
            tree = new FunctionTree(new Decimal(value));
        }
    }

    @NotNull FunctionTree getTree() {
        return tree;
    }

    /**
     * Adds a value to the current value.
     *
     * @param value a value to be added
     * @return <code>this + value</code>
     */
    public @NotNull ValueBuilder add(@NotNull ValueBuilder value) {
        tree.merge(value.getTree(), new Add());
        return this;
    }

    /**
     * Subtracts a value from the current value
     *
     * @param value a value to be subtracted
     * @return <code>this - value</code>
     */
    public @NotNull ValueBuilder subtract(@NotNull ValueBuilder value) {
        tree.merge(value.getTree(), new Subtract());
        return this;
    }

    /**
     * Multiplies current value by a specified value
     *
     * @param value multiplicand
     * @return <code>this * value</code>
     */
    public @NotNull ValueBuilder multiply(@NotNull ValueBuilder value) {
        tree.merge(value.getTree(), new Multiply());
        return this;
    }

    /**
     * Divides current value by a specified value
     *
     * @param value divisor
     * @return <code>this / value</code>
     */
    public @NotNull ValueBuilder divide(@NotNull ValueBuilder value) {
        tree.merge(value.getTree(), new Fraction());
        return this;
    }

    /**
     * Takes a square root of the current value
     *
     * @return <code>sqrt(this)</code>
     */
    public @NotNull ValueBuilder sqrt() {
        tree.merge(new Sqrt());
        return this;
    }

    /**
     * Evaluates this builder returning a <code>Number</code> containing the result. The result is equivalent to
     * evaluating mathematical expression <code>this</code> builder represents.
     *
     * @return <code>Number</code> value of this builder
     */
    public @NotNull Number evaluate() {
        return tree.evaluate();
    }

    @Override
    public String toString() {
        final String content = tree.reducePreOrder(tree.getRoot(), "",
                (node, string) -> string + (node.getNodeContext().getOperator().isPresent()
                        ? node.getNodeContext().getOperator().get().debugString()
                        : node.getNodeContext().getBiOperator().get().debugString()).toLowerCase() + ", ");
        return "ValueBuilder[" + content.substring(0, content.length() - 2) + ']';
    }

    @Temporary
    public void print() {
        tree.print();
    }

    @Temporary
    public FunctionTree tree() {
        return tree;
    }

}
