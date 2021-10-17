package gosk.szymon.computational.structural;

import gosk.szymon.computational.operators.Operator;
import gosk.szymon.functional.Temporary;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Binary expression tree node. It represents math operation (or function). Its children represent this operator's
 * arguments.
 *
 * @since 1.0
 */
public class NodeImpl implements Node {

    private Node parent;
    private Node left;
    private Node right;

    private Operator operator;

    private int level;

    public NodeImpl(@NotNull Operator operator) {
        this.operator = operator;
    }

    public NodeImpl(@NotNull Operator operator, int level) {
        this(operator);
        this.level = level;
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node node) {
        parent = node;
    }

    @Override
    public void removeParent() {
        parent = null;
    }

    @Override
    public boolean hasLeft() {
        return left != null;
    }

    @Override
    public Node getLeft() {
        return left;
    }

    @Override
    public void setLeft(Node node) {
        left = node;
    }

    @Override
    public void removeLeft() {
        if (hasRight()) {
            throw new IllegalStateException("");
        } else {
            left = null;
        }
    }

    @Override
    public boolean hasRight() {
        return right != null;
    }

    @Override
    public Node getRight() {
        return right;
    }

    @Override
    public void setRight(Node node) {
        if (!hasLeft()) {
            throw new IllegalStateException("");
        } else {
            right = node;
        }
    }

    @Override
    public void removeRight() {
        right = null;
    }

    @Override
    public @NotNull Operator getOperator() {
        return operator;
    }

    @Override
    public void setOperator(@NotNull Operator operator) {
        this.operator = operator;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void incrementLevel() {
        level += 1;
    }

    @Override
    public void decrementLevel() {
        level -= 1;
    }

    @Override
    public String toString() {
        return getLevel() + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeImpl that)) return false;
        if (!this.operator.equals(that.operator) || this.level != that.level) return false;
        if ((this.hasLeft() ^ that.hasLeft()) || (this.hasRight() ^ that.hasRight())) {
            return false;
        }
        boolean result = true;
        if (this.hasLeft()) result = this.getLeft().equals(that.getLeft());
        if (this.hasRight()) result = result && this.getRight().equals(that.getRight());
        return result;
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return StringUtils.repeat("  ", getLevel()) + "Node{ " + operator.debugString() + " }\n";
    }

}
