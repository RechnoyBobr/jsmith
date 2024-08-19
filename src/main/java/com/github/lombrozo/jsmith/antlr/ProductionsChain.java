package com.github.lombrozo.jsmith.antlr;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Chain of {@link Generative} objects.
 * Used for logging of the current chain of {@link Generative} objects.
 * @since 0.1
 */
final class ProductionsChain {

    private Generative child;

    ProductionsChain(final Generative start) {
        this.child = start;
    }

    String toTree() {
        return this.tree(this.child);
    }

    /**
     * Prints the tree of the {@link Generative} objects.
     * @param node The current node.
     * @return The tree of the {@link Generative} objects.
     */
    private String tree(final Generative node) {
        final String result;
        if (node == node.parent()) {
            result = String.format("%s\n", node);
        } else {
            final String prev = this.tree(node.parent());
            final int length = prev.split("\n").length;
            result = String.format(
                "%s%s└──%s%n",
                prev,
                Stream.generate(() -> " ")
                    .limit(2 + ((length - 1) * 5L))
                    .collect(Collectors.joining()),
                node
            );
        }
        return result;
    }
}
