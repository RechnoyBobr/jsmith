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
package com.github.lombrozo.jsmith.antlr.semantic;

import com.github.lombrozo.jsmith.antlr.Context;
import com.github.lombrozo.jsmith.antlr.rules.Rule;
import com.github.lombrozo.jsmith.antlr.view.Text;

/**
 * Variable Declaration Semantic.
 * Adds variable declaration to the context.
 * @since 0.1
 */
public final class VariableDeclaration implements Rule {

    /**
     * Comment to activate this rule.
     */
    public static final String COMMENT = "$jsmith-var-decl";

    /**
     * Original rule.
     */
    private final Rule origin;

    /**
     * Constructor.
     * @param origin Origin rule.
     */
    public VariableDeclaration(final Rule origin) {
        this.origin = origin;
    }

    @Override
    public Rule parent() {
        return this.origin.parent();
    }

    @Override
    public Text generate(final Context context) {
        final Text text = this.origin.generate(context);
        if (!text.error()) {
            context.current().declare(text.output());
        }
        return text;
    }

    @Override
    public void append(final Rule rule) {
        this.origin.append(rule);
    }

    @Override
    public String name() {
        return VariableDeclaration.COMMENT;
    }

    @Override
    public Rule copy() {
        return new VariableDeclaration(this.origin.copy());
    }
}