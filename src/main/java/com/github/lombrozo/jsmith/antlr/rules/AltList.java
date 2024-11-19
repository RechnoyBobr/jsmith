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
import com.github.lombrozo.jsmith.antlr.view.Error;
import com.github.lombrozo.jsmith.antlr.view.Text;
import com.github.lombrozo.jsmith.antlr.view.TextLeaf;
import com.github.lombrozo.jsmith.antlr.view.TextNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.ToString;

/**
 * Alternative ANTLR rule.
 * The rule definition:
 * {@code
 * altList
 *     : {@link Alternative} (OR {@link Alternative})*
 *     ;
 * }
 * @since 0.1
 */
@ToString
public final class AltList implements Rule {

    /**
     * Parent rule.
     */
    private final Rule top;

    /**
     * Alternatives.
     */
    private final List<Rule> alternatives;

    /**
     * Default constructor.
     */
    public AltList() {
        this(new Root());
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     */
    public AltList(final Rule parent) {
        this(parent, new ArrayList<>(0));
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param alternatives Alternatives.
     */
    AltList(final Rule parent, final Rule... alternatives) {
        this(parent, Arrays.asList(alternatives));
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param alternatives Alternatives.
     */
    private AltList(
        final Rule parent,
        final List<Rule> alternatives
    ) {
        this.top = parent;
        this.alternatives = alternatives;
    }

    @Override
    public Rule parent() {
        return this.top;
    }

    @Override
    public Text generate(final Context context) {
        /**
         * todo! refactor this see {@link RuleAltList}
         */
        final Text result;
        if (this.alternatives.isEmpty()) {
            result = new TextLeaf(this, "");
        } else {
            Text output = context.strategy()
                .choose(this, this.alternatives)
                .generate(context);
            int attempts = 0;
            while (output.error()) {
                output = context.strategy()
                    .choose(this, this.alternatives)
                    .generate(context);
                attempts++;
                if (attempts > 10) {
                    throw new IllegalStateException(
                        String.format("Can't generate output for altList '%s'", this)
                    );
                }
            }
            result = new TextNode(this, output);
        }
        return result;
    }

    @Override
    public void append(final Rule rule) {
        this.alternatives.add(rule);
    }

    @Override
    public String name() {
        return String.format("altList(size=%d)", this.alternatives.size());
    }

    @Override
    public Rule copy() {
        return new AltList(
            this.top, this.alternatives.stream().map(Rule::copy).collect(Collectors.toList())
        );
    }
}
