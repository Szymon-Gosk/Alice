package gosk.szymon.computational.structural;

import gosk.szymon.computational.operators.Operator;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractExpressionTreeTest {

    private AbstractExpressionTree tree;

    private final Operator operator = new Operator() {
        @Override public @NotNull BigDecimal evaluate(@NotNull Number @NotNull ... arguments) {
            return new BigDecimal(0);
        }
        @Override public int getOrder() {
            return 0;
        }
        @Override public boolean isNumber() {
            return false;
        }
    };

    @BeforeEach
    void createTree() {
        Node[] nodes = {
                new NodeImpl(operator),
                new NodeImpl(operator),
                new NodeImpl(operator),
                new NodeImpl(operator),
                new NodeImpl(operator),
                new NodeImpl(operator),
                new NodeImpl(operator),
                new NodeImpl(operator)
        };

        for(int i = 0; i < nodes.length; i++) {
            nodes[i].setLevel(i);
        }

        nodes[0].setLeft(nodes[1]);
        nodes[0].setRight(nodes[6]);
        nodes[1].setParent(nodes[0]);
        nodes[1].setLeft(nodes[2]);
        nodes[1].setRight(nodes[3]);
        nodes[2].setParent(nodes[1]);
        nodes[3].setParent(nodes[1]);
        nodes[3].setLeft(nodes[4]);
        nodes[3].setRight(nodes[5]);
        nodes[4].setParent(nodes[3]);
        nodes[5].setParent(nodes[4]);
        nodes[6].setParent(nodes[0]);
        nodes[6].setLeft(nodes[7]);
        nodes[7].setParent(nodes[6]);

        tree = new AbstractExpressionTree() {
            private final Node root = nodes[0];

            @Override @NotNull Node getRoot() { return root; }

            @Override int height() { return 3; }

            @Override int size() { return 8; }

            @Override @NotNull Number evaluate() { return new BigDecimal(0); }
        };
    }

    @Test
    void shouldProperlyReducePreOrder() {
        List<Integer> actual = tree.reducePreOrder(new LinkedList<>(), (n, i) -> {
            i.add(n.getLevel());
            return i;
        });
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);

        assertEquals(expected, actual);
    }

    @Test
    void shouldProperlyReduceInOrder() {
        List<Integer> actual = tree.reduceInOrder(new LinkedList<>(), (n, i) -> {
            i.add(n.getLevel());
            return i;
        });
        List<Integer> expected = Arrays.asList(2, 1, 4, 3, 5, 0, 7, 6);

        assertEquals(expected, actual);
    }

    @Test
    void shouldProperlyReducePostOrder() {
        List<Integer> actual = tree.reducePostOrder(new LinkedList<>(), (n, i) -> {
            i.add(n.getLevel());
            return i;
        });
        List<Integer> expected = Arrays.asList(2, 4, 5, 3, 1, 7, 6, 0);

        assertEquals(expected, actual);
    }

}