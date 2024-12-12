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
import com.github.lombrozo.jsmith.antlr.Unlexer;
import com.github.lombrozo.jsmith.antlr.view.Snippet;
import com.github.lombrozo.jsmith.antlr.view.TextSnippet;
import com.github.lombrozo.jsmith.random.Rand;
import java.util.Collections;
import java.util.List;

/**
 * Terminal definition.
 * The ANTLR grammar definition:
 * {@code
 * terminalDef
 *     : TOKEN_REF {@link ElementOptions}?
 *     | STRING_LITERAL {@link ElementOptions}?
 *     ;
 * }
 * @since 0.1
 */
public final class TerminalDef implements Rule {

    /**
     * End of file terminal.
     */
    private static final String END_OF_FILE = "EOF";

    /**
     * Parent rule.
     */
    private final Rule parentr;

    /**
     * Unlexer.
     */
    private final Unlexer unlexer;

    /**
     * Text.
     */
    private final String text;

    /**
     * Random generator.
     */
    private final Rand rand;

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param unlexer Unparser.
     * @param text Text.
     */
    public TerminalDef(final Rule parent, final Unlexer unlexer, final String text) {
        this(parent, unlexer, text, new Rand());
    }

    public TerminalDef(
        final Rule parentr,
        final Unlexer unlexer,
        final String text,
        final Rand rand
    ) {
        this.parentr = parentr;
        this.unlexer = unlexer;
        this.text = text;
        this.rand = rand;
    }

    /**
     * Constructor.
     * @param unlexer Unlexer.
     * @param text Terminal text.
     */
    TerminalDef(final Unlexer unlexer, final String text) {
        this(new Empty(), unlexer, text);
    }

    @Override
    public Rule parent() {
        return this.parentr;
    }

    @Override
    public Snippet generate(final Context context) {
        final Snippet result;
        if (TerminalDef.END_OF_FILE.equals(this.text)) {
            result = new TextSnippet(this, "");
        } else {
            result = this.unlexer.find(this.text)
                .orElseGet(() -> new Literal(this, this.text, this.rand))
                .generate(context);
        }
        return result;
    }

    @Override
    public List<Rule> children(final Context context) {
        return Collections.emptyList();
    }

    @Override
    public void append(final Rule rule) {
        throw new UnsupportedOperationException("Terminal cannot have children yet");
    }

    @Override
    public String name() {
        return String.format("terminalDef(%s)", this.text);
    }

    @Override
    public Rule copy() {
        return new TerminalDef(
            this.parentr,
            this.unlexer,
            this.text
        );
    }
}
