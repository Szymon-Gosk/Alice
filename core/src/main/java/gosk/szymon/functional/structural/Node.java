package gosk.szymon.functional.structural;

import gosk.szymon.functional.Temporary;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Node {

    Optional<Node> getParent();

    void setParent(Node parent);

    Optional<Node> getLeft();

    void setLeft(Node left);

    Optional<Node> getRight();

    void setRight(Node right);

    FunctionTree.NodeContext getNodeContext();

    @Temporary
    default @NotNull String debugString() {
        return "NODE";
    }

    default boolean hasParent() {
        return getParent().isPresent();
    }

    default boolean hasLeft() {
        return getLeft().isPresent();
    }

    default boolean hasRight() {
        return getRight().isPresent();
    }

    default boolean hasDescendants() {
        return getLeft().isPresent() && getRight().isPresent();
    }

}
