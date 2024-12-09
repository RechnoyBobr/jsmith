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

import com.github.lombrozo.jsmith.antlr.Context;
import com.github.lombrozo.jsmith.antlr.NodeSnippet;
import com.github.lombrozo.jsmith.antlr.Snippet;
import com.github.lombrozo.jsmith.antlr.view.Text;
import com.github.lombrozo.jsmith.antlr.view.TextNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Root rule.
 * Useful for tests mostly.
 * WARNING: This is NOT a part of the ANTLR grammar!
 * @since 0.1
 */
public final class Root implements Rule {

    /**
     * All children of the current node.
     */
    private final List<Rule> all;

    /**
     * Constructor.
     */
    public Root() {
        this(new ArrayList<>(0));
    }

    /**
     * Constructor.
     * @param all All children of the current node.
     */
    private Root(final List<Rule> all) {
        this.all = all;
    }

    @Override
    public Rule parent() {
        return this;
    }

    @Override
    public Snippet generate(final Context context) {
        return new NodeSnippet(
            this,
            this.all.stream()
                .map(rule -> rule.generate(context))
                .collect(Collectors.toList())
        );
    }

    @Override
    public void append(final Rule rule) {
        this.all.add(rule);
    }

    @Override
    public String name() {
        return "root";
    }

    @Override
    public Rule copy() {
        return new Root(
            this.all.stream().map(Rule::copy).collect(Collectors.toList())
        );
    }

    @Override
    public String toString() {
        return this.name();
    }
}
