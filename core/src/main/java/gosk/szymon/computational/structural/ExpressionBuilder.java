package gosk.szymon.computational.structural;

import gosk.szymon.computational.operators.Operator;
import gosk.szymon.computational.operators.bi.Add;
import gosk.szymon.computational.operators.bi.Fraction;
import gosk.szymon.computational.operators.bi.Multiply;
import gosk.szymon.computational.operators.bi.Subtract;
import gosk.szymon.computational.operators.mono.NaturalExponent;
import gosk.szymon.computational.operators.mono.NaturalLogarithm;
import gosk.szymon.computational.operators.mono.Sqrt;
import gosk.szymon.computational.operators.numbers.Int;
import gosk.szymon.computational.operators.numbers.Real;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class ExpressionBuilder {

    private final Expression expression;

    public ExpressionBuilder(@NotNull Operator operator) {
        if(!operator.isNumber()) {
            throw new IllegalArgumentException("New tree must be provided with a number");
        }
        if(operator instanceof Real r) {
            BigDecimal bd = (BigDecimal) r.evaluate();
            if(bd.stripTrailingZeros().scale() <= 0) {
                expression = new Expression(new Int(bd.toBigInteger()));
            } else {
                expression = new Expression(new Real(bd));
            }
        } else {
            expression = new Expression(operator);
        }
    }

    @NotNull Expression getExpression() {
        return expression;
    }

    public @NotNull Number evaluate() {
        return expression.evaluate();
    }

    /**
     * Adds a value to the current value.
     *
     * @param value a value to be added
     * @return <code>this + value</code>
     */
    public @NotNull ExpressionBuilder add(@NotNull ExpressionBuilder value) {
        expression.merge(Add.get(), value.getExpression());
        return this;
    }

    /**
     * Adds a value to the current value.
     *
     * @param value a value to be added
     * @return <code>this + value</code>
     */
    public @NotNull ExpressionBuilder subtract(@NotNull ExpressionBuilder value) {
        expression.merge(Subtract.get(), value.getExpression());
        return this;
    }

    /**
     * Multiplies current value by a specified value
     *
     * @param value multiplicand
     * @return <code>this * value</code>
     */
    public @NotNull ExpressionBuilder multiply(@NotNull ExpressionBuilder value) {
        expression.merge(Multiply.get(), value.getExpression());
        return this;
    }

    /**
     * Divides current value by a specified value
     *
     * @param value divisor
     * @return <code>this / value</code>
     */
    public @NotNull ExpressionBuilder divide(@NotNull ExpressionBuilder value) {
        expression.merge(Fraction.get(), value.getExpression());
        return this;
    }

    /**
     * Takes a square root of the current value
     *
     * @return <code>sqrt(this)</code>
     */
    public @NotNull ExpressionBuilder sqrt() {
        expression.merge(Sqrt.get());
        return this;
    }

    /**
     * Takes a natural logarithm of the current value
     *
     * @return <code>ln(this)</code>
     */
    public @NotNull ExpressionBuilder ln() {
        expression.merge(NaturalLogarithm.get());
        return this;
    }

    /**
     * Raises <code>e</code> to the current value
     *
     * @return <code>e^(this)</code>
     */
    public @NotNull ExpressionBuilder exp() {
        expression.merge(NaturalExponent.get());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpressionBuilder that)) return false;
        return expression.equals(that.expression);
    }

}
