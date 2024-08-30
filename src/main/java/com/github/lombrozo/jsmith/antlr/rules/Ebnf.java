/*
 * MIT License
 *
 * Copyright (c) 2023-2024 Volodya Lombrozo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.lombrozo.jsmith.antlr.rules;

import com.github.lombrozo.jsmith.antlr.GenerationContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ebnf rule.
 * The ANTLR grammar definition:
 * {@code
 * ebnf
 *     : {@link Block} {@link BlockSuffix}?
 *     ;
 * }
 * @since 0.1
 */
public final class Ebnf implements Rule {

    /**
     * Parent rule.
     */
    private final Rule parent;

    /**
     * Block and suffix.
     */
    private final List<Rule> children;

    /**
     * Constructor.
     * @param parent Parent rule.
     */
    public Ebnf(final Rule parent) {
        this(parent, new ArrayList<>(0));
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param children Children rules.
     */
    public Ebnf(final Rule parent, final List<Rule> children) {
        this.parent = parent;
        this.children = children;
    }

    @Override
    public Rule parent() {
        return this.parent;
    }

    @Override
    public String generate(final GenerationContext context) {
        return this.children.stream()
            .map(rule -> rule.generate(context))
            .collect(Collectors.joining(" "));
    }

    @Override
    public void append(final Rule rule) {
        this.children.add(rule);
    }

    @Override
    public String toString() {
        return "ebnf";
    }
}
