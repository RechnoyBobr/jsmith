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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Rule that has several children.
 * WARNING: This is NOT a part of the ANTLR grammar!
 * @since 0.1
 */
public final class Several implements RuleDefinition {

    /**
     * All children of the current node.
     */
    private final List<? extends RuleDefinition> all;

    /**
     * Constructor.
     * @param all All children of the current node.
     */
    Several(final List<? extends RuleDefinition> all) {
        this.all = all;
    }

    @Override
    public RuleDefinition parent() {
        throw new UnsupportedOperationException("'Several' node doesn't have a parent node");
    }

    @Override
    public String generate() {
        return this.all.stream()
            .map(RuleDefinition::generate)
            .collect(Collectors.joining());
    }

    @Override
    public void append(final RuleDefinition rule) {
        throw new UnsupportedOperationException("'Several' node cannot add children");
    }

    @Override
    public String toString() {
        return "several";
    }
}