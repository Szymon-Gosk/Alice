package gosk.szymon.functional.structural;

import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Consumer;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public abstract class AbstractFunctionTree {

    public abstract Node getRoot();

    public abstract int height();

    public abstract int size();

    /**
     * Performs a reduction on the nodes of this tree, using the provided identity value and an associative
     * accumulation function, and returns the reduced value.
     *
     * @param node node from which reduction will start
     * @param identity the identity value for the accumulating function
     * @param accumulation an associative, stateless function for combining two values
     * @return the result of the reduction
     */
    public <T> T reducePreOrder(@NotNull Node node, @NotNull T identity, @NotNull BiFunction<Node, T, T> accumulation) {
        identity = accumulation.apply(node, identity);
        if (node.hasLeft()) {
            identity = reducePreOrder(node.getLeft().get(), identity, accumulation);
        }
        if (node.hasRight()) {
            identity = reducePreOrder(node.getRight().get(), identity, accumulation);
        }
        return identity;
    }

    /**
     * Performs an action for each node of this tree staring with the given node in pre-order.
     *
     * @param node starting node
     * @param action an action to perform on the nodes
     */
    public void forEachPreOrder(@NotNull Node node, @NotNull Consumer<Node> action) {
        action.accept(node);
        if (node.hasLeft()) {
            forEachPreOrder(node.getLeft().get(), action);
        }
        if (node.hasRight()) {
            forEachPreOrder(node.getRight().get(), action);
        }
    }

}
