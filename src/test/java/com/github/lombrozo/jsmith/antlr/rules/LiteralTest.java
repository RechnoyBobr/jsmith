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

import java.util.stream.Stream;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test case for {@link Literal}.
 * @since 0.1
 */
final class LiteralTest {

    @ParameterizedTest
    @MethodSource("examples")
    void generatesSymbols(final String input, final String expected) {
        MatcherAssert.assertThat(
            "We expect that the literal will generate the symbol without special characters",
            new Literal(input).generate(),
            Matchers.equalTo(expected)
        );
    }

    /**
     * Test cases for {@link #generatesSymbols(String, String)} test.
     * @return Stream of test cases.
     */
    private static Stream<Arguments> examples() {
        return Stream.of(
            Arguments.of("PLUS", "PLUS"),
            Arguments.of("'+'", "+"),
            Arguments.of("'-'", "-"),
            Arguments.of("'*'", "*"),
            Arguments.of("'/'", "/"),
            Arguments.of("'='", "="),
            Arguments.of("'!'", "!"),
            Arguments.of("'('", "("),
            Arguments.of("')'", ")"),
            Arguments.of("NEWLINE", "NEWLINE"),
            Arguments.of("ID", "ID"),
            Arguments.of("INT", "INT"),
            Arguments.of("NUMBER", "NUMBER"),
            Arguments.of("'\\r'", "\r"),
            Arguments.of("'\r'", "\r"),
            Arguments.of("'\\n'", "\n"),
            Arguments.of("'\n'", "\n"),
            Arguments.of("'\\t'", "\t"),
            Arguments.of("'\t'", "\t"),
            Arguments.of("'\\f'", "\f"),
            Arguments.of("'\f'", "\f"),
            Arguments.of("'\\b'", "\b"),
            Arguments.of("'\b'", "\b"),
            Arguments.of("'\\\"'", "\""),
            Arguments.of("'\\\"'", "\"")
        );
    }
}