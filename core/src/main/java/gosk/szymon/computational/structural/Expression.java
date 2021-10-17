package gosk.szymon.computational.structural;

import gosk.szymon.computational.Development;
import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Binary tree representing a mathematical expression. It contains a root and operations for manipulating its content.
 *
 * @since 1.0
 */
public class Expression extends AbstractExpressionTree {

    private Node root;

    private int height;
    private int size;

    public Expression(@NotNull Operator operator) {
        if(!operator.isNumber()) {
            throw new IllegalArgumentException("Tree can be instantiated only from a number");
        }
        this.root = new NodeImpl(operator, 0);
        height = 0;
        size = 1;
    }

    /**
     * Merges this tree with a provided 1-st order operator, appending it as a new node to the top of the tree. Newly
     * appended node becomes the new root.
     *
     * @param operator any 1-st order operator
     */
    public void merge(@NotNull Operator operator) {
        if(operator.getOrder() != 1) {
            throw new IllegalArgumentException("");
        }
        NodeImpl newRoot = new NodeImpl(operator, 0);
        incrementLevelsFrom(root);
        newRoot.setLeft(root);
        root.setParent(newRoot);
        root = newRoot;
        height += 1;
        size += 1;
    }

    /**
     * Merges this tree with another expression tree using a provided 2-nd order operator, appending it as a new node
     * to the top of the new tree. Newly appended node becomes the new root.
     *
     * @param operator any 2-nd order operator
     */
    public void merge(@NotNull Operator operator, @NotNull Expression expression) {
        if(operator.getOrder() != 2) {
            throw new IllegalArgumentException("");
        }
        NodeImpl newRoot = new NodeImpl(operator, 0);
        incrementLevelsFrom(root);
        incrementLevelsFrom(expression.getRoot());
        newRoot.setLeft(root);
        newRoot.setRight(expression.getRoot());
        root.setParent(newRoot);
        expression.getRoot().setParent(newRoot);
        root = newRoot;
        height = Math.max(height, expression.height()) + 1;
        size = size + expression.size() + 1;
    }

    @Override
    @NotNull Node getRoot() {
        return root;
    }

    @Override
    int height() {
        return height;
    }

    @Override
    int size() {
        return size;
    }

    @Override
    @NotNull Number evaluate() {
        return evaluate(getRoot());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expression that)) return false;
        return height == that.height && size == that.size && root.equals(that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, height, size);
    }

    private @NotNull Number evaluate(@NotNull Node node) {
        return switch (node.getOrder()) {
            case 0 -> node.getOperator().evaluate();
            case 1 -> node.getOperator().evaluate(evaluate(node.getLeft()));
            case 2 -> node.getOperator().evaluate(evaluate(node.getLeft()), evaluate(node.getRight()));
            default -> throw new IllegalArgumentException("");
        };
    }

    private void incrementLevelsFrom(@NotNull Node node) {
        forEachPreOrder(node, Node::incrementLevel);
    }

    @Development
    public void print() {
        forEachPreOrder(root, node -> System.out.print(node.debugString()));
    }

}
