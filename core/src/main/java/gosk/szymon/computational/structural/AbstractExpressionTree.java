package gosk.szymon.computational.structural;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Binary expression tree abstraction providing basic operations for tree traversal.
 *
 * @since 1.0
 */
public abstract class AbstractExpressionTree {

    /**
     * Returns the root node of this expression tree.
     *
     * @return root node
     */
    abstract @NotNull Node getRoot();

    /**
     * Returns the height of this expression tree. The height of the tree is equal to the largest number of the
     * edges from the root to the most distant leaf node. The height of a tree with only root is equal to <code>0</code>.
     *
     * @return height of this tree
     */
    abstract int height();

    /**
     * Returns the size of this expression tree. The size of the tree is equal to the number of nodes present.
     *
     * @return size of this tree
     */
    abstract int size();

    /**
     * Evaluates this expression tree by evaluating each of its node operator.
     *
     * @return number result of the evaluation
     */
    abstract @NotNull Number evaluate();

    /**
     * Performs a reduction on the nodes of this expression tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value. The reduction is performed by pre-order tree traversal
     * starting from the root.
     *
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, non-interfering, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reducePreOrder(@NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        return reducePreOrder(getRoot(), identity, accumulation);
    }

    /**
     * Performs a reduction on the nodes of this expression tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value. The reduction is performed by pre-order tree traversal
     * starting from the specified node.
     *
     * @param node node from which the reduction starts
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, non-interfering, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reducePreOrder(@NotNull Node node, @NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(node);

        while (!nodeStack.empty()) {
            Node currentNode = nodeStack.peek();
            identity = accumulation.apply(currentNode, identity);
            nodeStack.pop();
            if (currentNode.hasRight()) {
                nodeStack.push(currentNode.getRight());
            }
            if (currentNode.hasLeft()) {
                nodeStack.push(currentNode.getLeft());
            }
        }
        return identity;
    }

    /**
     * Performs a reduction on the nodes of this expression tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value. The reduction is performed by in-order tree traversal
     * starting from the root.
     *
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, non-interfering, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reduceInOrder(@NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        return reduceInOrder(getRoot(), identity, accumulation);
    }

    /**
     * Performs a reduction on the nodes of this expression tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value. The reduction is performed by in-order tree traversal
     * starting from the specified node.
     *
     * @param node node from which the reduction starts
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, non-interfering, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reduceInOrder(@NotNull Node node, @NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        Stack<Node> nodeStack = new Stack<>();
        Node currentNode = node;

        while (currentNode != null || !nodeStack.empty()) {
            while (currentNode !=  null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeft();
            }
            currentNode = nodeStack.pop();
            identity = accumulation.apply(currentNode, identity);

            currentNode = currentNode.getRight();
        }
        return identity;
    }

    /**
     * Performs a reduction on the nodes of this expression tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value. The reduction is performed by post-order tree traversal
     * starting from the root.
     *
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, non-interfering, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reducePostOrder(@NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        return reducePostOrder(getRoot(), identity, accumulation);
    }

    /**
     * Performs a reduction on the nodes of this expression tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value. The reduction is performed by post-order tree traversal
     * starting from the specified node.
     *
     * @param node node from which the reduction starts
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, non-interfering, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reducePostOrder(@NotNull Node node, @NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(node);
        Node previousNode = null;

        while (!nodeStack.empty()) {
            Node currentNode = nodeStack.peek();
            if (previousNode == null || previousNode.getLeft() == currentNode || previousNode.getRight() == currentNode) {
                if (currentNode.hasLeft()) {
                    nodeStack.push(currentNode.getLeft());
                } else if (currentNode.hasRight()) {
                    nodeStack.push(currentNode.getRight());
                } else {
                    nodeStack.pop();
                    identity = accumulation.apply(currentNode, identity);
                }
            } else if (currentNode.getLeft() == previousNode) {
                if (currentNode.hasRight()) {
                    nodeStack.push(currentNode.getRight());
                } else {
                    nodeStack.pop();
                    identity = accumulation.apply(currentNode, identity);
                }
            } else if (currentNode.getRight() == previousNode) {
                nodeStack.pop();
                identity = accumulation.apply(currentNode, identity);
            }
            previousNode = currentNode;
        }
        return identity;
    }

    /**
     * Performs an action for each node of this stream by pre-order tree traversal starting from the root.
     *
     * @param action a non-interfering action to perform on the elements
     */
    public void forEachPreOrder(@NotNull Consumer<Node> action) {
        forEachPreOrder(getRoot(), action);
    }

    /**
     * Performs an action for each node of this stream by pre-order tree traversal starting from the specified node.
     *
     * @param action a non-interfering action to perform on the elements
     */
    public void forEachPreOrder(@NotNull Node node, @NotNull Consumer<Node> action) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(node);

        while (!nodeStack.empty()) {
            Node currentNode = nodeStack.peek();
            action.accept(currentNode);
            nodeStack.pop();
            if (currentNode.hasRight()) {
                nodeStack.push(currentNode.getRight());
            }
            if (currentNode.hasLeft()) {
                nodeStack.push(currentNode.getLeft());
            }
        }
    }

    /**
     * Performs an action for each node of this stream by in-order tree traversal starting from the root.
     *
     * @param action a non-interfering action to perform on the elements
     */
    public void forEachInOrder(@NotNull Consumer<Node> action) {
        forEachInOrder(getRoot(), action);
    }

    /**
     * Performs an action for each node of this stream by in-order tree traversal starting from the specified node.
     *
     * @param action a non-interfering action to perform on the elements
     */
    public void forEachInOrder(@NotNull Node node, @NotNull Consumer<Node> action) {
        Stack<Node> nodeStack = new Stack<>();
        Node currentNode = node;

        while (currentNode != null || !nodeStack.empty()) {
            while (currentNode !=  null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeft();
            }
            currentNode = nodeStack.pop();
            action.accept(currentNode);

            currentNode = currentNode.getRight();
        }
    }

    /**
     * Performs an action for each node of this stream by post-order tree traversal starting from the root.
     *
     * @param action a non-interfering action to perform on the elements
     */
    public void forEachPostOrder(@NotNull Consumer<Node> action) {
        forEachPostOrder(getRoot(), action);
    }

    /**
     * Performs an action for each node of this stream by in-order tree traversal starting from the specified node.
     *
     * @param action a non-interfering action to perform on the elements
     */
    public void forEachPostOrder(@NotNull Node node, @NotNull Consumer<Node> action) {
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(node);
        Node previousNode = null;

        while (!nodeStack.empty()) {
            Node currentNode = nodeStack.peek();
            if (previousNode == null || previousNode.getLeft() == currentNode || previousNode.getRight() == currentNode) {
                if (currentNode.hasLeft()) {
                    nodeStack.push(currentNode.getLeft());
                } else if (currentNode.hasRight()) {
                    nodeStack.push(currentNode.getRight());
                } else {
                    nodeStack.pop();
                    action.accept(currentNode);
                }
            } else if (currentNode.getLeft() == previousNode) {
                if (currentNode.hasRight()) {
                    nodeStack.push(currentNode.getRight());
                } else {
                    nodeStack.pop();
                    action.accept(currentNode);
                }
            } else if (currentNode.getRight() == previousNode) {
                nodeStack.pop();
                action.accept(currentNode);
            }
            previousNode = currentNode;
        }
    }

}
