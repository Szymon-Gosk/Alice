package gosk.szymon.computational.structural;

import gosk.szymon.computational.Development;
import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.NotNull;

/**
 * Interface of node used in expression trees. A node is a form of a container for an operator, be usable in a tree.
 *
 * @since 1.0
 */
public interface Node {

    /**
     * Returns <code>true</code> if this node has a parent. If it has not, meaning the parent is <code>null</code>, it
     * will return <code>false</code>. If this node has no parent it should be a root of some tree.
     *
     * @return <code>true</code> if this node has a parent, <code>false</code> otherwise.
     */
    boolean hasParent();

    /**
     * Returns parent of this node. It should be checked if this node has a parent before accessing it in order to avoid
     * obtaining a <code>null</code> value.
     *
     * @return parent of this node.
     */
    Node getParent();

    /**
     * Sets the parent of this node.
     *
     * @param node parent node
     */
    void setParent(Node node);

    /**
     * Removes the parent of this node. The parent node value will be set to <code>null</code>.
     */
    void removeParent();

    /**
     * Returns <code>true</code> if this node has left child. If it has not, meaning the left is <code>null</code>, it
     * will return <code>false</code>. If there is no left node, it is guaranteed there is no right node.
     *
     * @return <code>true</code> if this node has left child, <code>false</code> otherwise.
     */
    boolean hasLeft();

    /**
     * Returns left child of this node. It should be checked if this node has left child before accessing it in order to
     * avoid obtaining a <code>null</code> value.
     *
     * @return left child of this node.
     */
    Node getLeft();

    /**
     * Sets the left child of this node.
     *
     * @param node left child node
     */
    void setLeft(Node node);

    /**
     * Removes the left child of this node. The left node value will be set to <code>null</code>.
     */
    void removeLeft();

    /**
     * Returns <code>true</code> if this node has right child. If it has not, meaning the right is <code>null</code>, it
     * will return <code>false</code>. If there is no right node, it is guaranteed there is no right node.
     *
     * @return <code>true</code> if this node has right child, <code>false</code> otherwise.
     */
    boolean hasRight();

    /**
     * Returns right child of this node. It should be checked if this node has right child before accessing it in order to
     * avoid obtaining a <code>null</code> value.
     *
     * @return right child of this node.
     */
    Node getRight();

    /**
     * Sets the right child of this node.
     *
     * @param node right child node
     */
    void setRight(Node node);

    /**
     * Removes the right child of this node. The right node value will be set to <code>null</code>.
     */
    void removeRight();

    /**
     * Return <code>true</code> if this node has descendants, that is if it has any children.
     *
     * @return <code>true</code> if this node has descendants, <code>false</code> otherwise.
     */
    default boolean hasDescendants() {
        return hasLeft() || hasRight();
    }

    /**
     * Returns operator associated with this node.
     *
     * @return associated operator.
     */
    @NotNull Operator getOperator();

    /**
     * Sets operator of this node.
     *
     * @param operator any operator.
     */
    void setOperator(@NotNull Operator operator);

    /**
     * Returns order of operator associated with this node - order is equivalent to the number of arguments this
     * operator accepts.
     *
     * @return order of associated operator.
     */
    default int getOrder() {
        return getOperator().getOrder();
    }

    /**
     * Returns level this node is on. A root node has always level of <code>0</code>, while the deepest node has level of <code>height - 1</code>.
     *
     * @return level of this node.
     */
    int getLevel();

    /**
     * Sets level of this node.
     *
     * @param level new level value
     */
    void setLevel(int level);

    /**
     * Increments level of this node.
     */
    void incrementLevel();

    /**
     * Decrements level of this node.
     */
    void decrementLevel();

    /**
     * <b>Early development only!</b> Returns debug string for development purposes. To be deleted later.
     *
     * @return debug string of this operator.
     */
    @Development
    default @NotNull String debugString() {
        return "NODE";
    }

}
