package gosk.szymon.functional.structural;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.operators.BiOperator;
import gosk.szymon.functional.operators.Operator;
import gosk.szymon.functional.operators.numbers.Decimal;
import gosk.szymon.functional.operators.numbers.Int;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class FunctionTree extends AbstractFunctionTree {

    /**
     * Root of <code>this</code> function tree.
     */
    private NodeImpl root;

    private int height;
    private int size;

    public FunctionTree(@NotNull Operator operator) {
        if(!operator.isNumber()) throw new IllegalArgumentException("Tree can be instantiated only from a number");
        root = new NodeImpl(new NodeContext(operator, 0));
        height = 0;
        size = 1;
    }

    /**
     * Returns root node of <code>this</code> tree.
     *
     * @return root node of <code>this</code> tree
     */
    @Override
    public @NotNull NodeImpl getRoot() {
        return root;
    }

    /**
     * Returns height of <code>this</code> tree. Tree consisting of only root has height of 0.
     *
     * @return height of <code>this</code> tree
     */
    @Override
    public int height() {
        return height;
    }

    /**
     * Returns numbers of nodes in this tree.
     *
     * @return size of this tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends a new node with specified operator at the top if <code>this</code> tree - the newly appended node becomes
     * the new root.
     *
     * @param operator operator of new root
     */
    public void merge(@NotNull Operator operator) {
        final NodeImpl newRoot = new NodeImpl((new NodeContext(operator, 0)));
        incrementLevelsFrom(root);
        newRoot.setLeft(root);
        root = newRoot;
        height += 1;
        size += 1;
    }

    /**
     * Merges two trees with <code>biOperator</code> as the new root - <code>this</code> tree becomes the <code>left</code> of the
     * root and <code>that</code> tree becomes the right of the root.
     *
     * @param that tree to be merged with <code>this</code> tree
     * @param biOperator biOperator of new root
     */
    public void merge(@NotNull FunctionTree that, @NotNull BiOperator biOperator) {
        final NodeImpl newRoot = new NodeImpl((new NodeContext(biOperator, 0)));
        incrementLevelsFrom(root);
        incrementLevelsFrom(that.getRoot());
        newRoot.setLeft(root);
        newRoot.setRight(that.getRoot());
        root = newRoot;
        height = Math.max(height, that.height()) + 1;
        size = size + that.size() + 1;
    }

    /**
     * Evaluates all the nodes in mathematically correct order (post-order) returning <code>Number</code> obtained from
     * calculations. The result is equivalent to evaluating mathematical expression <code>this</code> tree represents.
     *
     * @return <code>Number</code> value of <code>this</code> tree
     */
    public Number evaluate() {
        return evaluate(root);
    }

    @Override
    public String toString() {
        final String nodes = reducePreOrder(root, "", (node, acc) -> acc + node.toString() + ", ");
        return "FunctionTree[" + nodes.substring(0, nodes.length() - 2) + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionTree that = (FunctionTree) o;
        if(height != that.height() || size != that.size()) return false;
        return root.equals(that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    /**
     * Increments level of this node and every of its sub-node
     *
     * @param node starting node
     */
    private void incrementLevelsFrom(@NotNull Node node) {
        forEachPreOrder(node, n -> n.getNodeContext().incrementLevel());
    }

    /**
     * Evaluating function suited for recursively evaluating individual nodes.
     *
     * @param node starting node
     *
     * @return <code>Number</code> value of specified <code>node</code>
     */
    private Number evaluate(@NotNull Node node) {
        Number leftResult = null;
        Number rightResult = null;
        if (node.hasLeft()) {
            leftResult = evaluate(node.getLeft().get());
        }
        if (node.hasRight()) {
            rightResult = evaluate(node.getRight().get());
        }
        return node.hasRight()
                ? node.getNodeContext().getBiOperator().get().evaluate(leftResult, rightResult)
                : node.getNodeContext().getOperator().get().evaluate(leftResult);
    }

    @Temporary
    public void print() {
        forEachPreOrder(root, node -> System.out.print(node.debugString()));
        System.out.println(this);
    }

    static class NodeContext {

        private Operator operator;
        private BiOperator biOperator;
        private int level;

        NodeContext(@NotNull Operator operator, int level) {
            this.operator = operator;
            this.level = level;
        }

        NodeContext(@NotNull BiOperator operator, int level) {
            this.biOperator = operator;
            this.level = level;
        }

        /**
         * Returns optional of <code>operator</code>. If the optional is empty, the optional of <code>biOperator</code>
         * cannot be empty.
         *
         * @return optional of <code>operator</code>
         */
        public @NotNull Optional<Operator> getOperator() {
            return Optional.ofNullable(operator);
        }

        /**
         * Sets the <code>operator</code>. If <code>biOperator</code> already exists, this method will throw
         * <code>IllegalStateException</code>
         *
         * @param operator new <code>operator</code>
         */
        public void setOperator(@NotNull Operator operator) {
            if(biOperator != null) throw new IllegalStateException("Node context cannot store two types of operators");
            this.operator = operator;
        }

        /**
         * Returns optional of <code>biOperator</code>. If the optional is empty, the optional of <code>operator</code>
         * cannot be empty.
         *
         * @return optional of <code>biOperator</code>
         */
        public @NotNull Optional<BiOperator> getBiOperator() {
            return Optional.ofNullable(biOperator);
        }

        /**
         * Sets the <code>biOperator</code>. If <code>operator</code> already exists, this method will throw
         * <code>IllegalStateException</code>
         *
         * @param biOperator new <code>biOperator</code>
         */
        public void setOperator(@NotNull BiOperator biOperator) {
            if(operator != null) throw new IllegalStateException("Node context cannot store two types of operators");
            this.biOperator = biOperator;
        }

        /**
         * Returns the level (height) on which node corresponding to this context is in the tree.
         *
         * @return level of corresponding node
         */
        public int getLevel() {
            return level;
        }

        /**
         * Increments the level of corresponding node.
         */
        public void incrementLevel() {
            this.level += 1;
        }

        @Override
        public String toString() {
            return "NodeContext{" + "operator=" + operator + ", biOperator=" + biOperator + ", level=" + level + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeContext that = (NodeContext) o;
            if(this.level != that.getLevel()) return false;
            if((this.getOperator().isPresent() && that.getOperator().isEmpty())
                    || (this.getOperator().isEmpty() && that.getOperator().isPresent())) {
                return false;
            }
            if((this.getBiOperator().isPresent() && that.getBiOperator().isEmpty())
                    || (this.getBiOperator().isEmpty() && that.getBiOperator().isPresent())) {
                return false;
            }
            if(this.getOperator().isPresent()) {
                return this.operator.equals(that.getOperator().get());
            } else {
                return this.biOperator.equals(that.getBiOperator().get());
            }
        }

        @Temporary
        public String debugString() {
            final String contextString = operator == null ? biOperator.debugString() : operator.debugString();
            final String suffix = operator == null
                    ? "(?,?)"
                    : (operator instanceof Int m
                        ? "(" + m.getValue() + ")"
                        : (operator instanceof Decimal d ? "(" + d.getValue() + ")" : "(?)"));
            return contextString + suffix;
        }

    }

}
