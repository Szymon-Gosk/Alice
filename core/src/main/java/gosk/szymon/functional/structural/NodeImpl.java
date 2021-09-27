package gosk.szymon.functional.structural;

import gosk.szymon.functional.Temporary;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

class NodeImpl implements Node {

    protected Node parent;
    protected Node left;
    protected Node right;

    protected FunctionTree.NodeContext nodeContext;

    public NodeImpl(@NotNull FunctionTree.NodeContext nodeContext) {
        this.nodeContext = nodeContext;
    }

    @Override
    public @NotNull Optional<Node> getParent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public void setParent(@NotNull Node parent) {
        this.parent = parent;
    }

    @Override
    public @NotNull Optional<Node> getLeft() {
        return Optional.ofNullable(left);
    }

    @Override
    public void setLeft(@NotNull Node left) {
        this.left = left;
    }

    @Override
    public @NotNull Optional<Node> getRight() {
        return Optional.ofNullable(right);
    }

    @Override
    public void setRight(@NotNull Node right) {
        this.right = right;
    }

    @Override
    public @NotNull FunctionTree.NodeContext getNodeContext() {
        return nodeContext;
    }

    @Override
    public String toString() {
        return "NodeImpl{"
                + "operator=" + (nodeContext.getOperator().isPresent()
                ? nodeContext.getOperator().get().toString()
                : nodeContext.getBiOperator().get().toString())
                + ", level=" + nodeContext.getLevel()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeImpl that = (NodeImpl) o;
        if ((this.hasLeft() && !that.hasLeft()) || (!this.hasLeft() && that.hasLeft())) {
            return false;
        }
        if ((this.hasRight() && !that.hasRight()) || (!this.hasRight() && that.hasRight())) {
            return false;
        }
        if (!this.nodeContext.equals(that.getNodeContext())) return false;
        boolean result = true;
        if (this.hasLeft()) {
            result = this.getLeft().get().equals(that.getLeft().get());
        }
        if (this.hasRight()) {
            result = result && this.getRight().get().equals(that.getRight().get());
        }
        return result;
    }

    @Temporary
    @Override
    public @NotNull String debugString() {
        return StringUtils.repeat("  ", nodeContext.getLevel()) + "Node{ " + getNodeContext().debugString() + " }\n";
    }

}
