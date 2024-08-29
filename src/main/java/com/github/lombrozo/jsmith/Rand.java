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
package com.github.lombrozo.jsmith;

import com.mifmif.common.regex.Generex;
import java.util.Random;

/**
 * Random generator.
 * @since 0.1
 */
public final class Rand {

    /**
     * Random.
     */
    private final Random random;

    /**
     * Default constructor.
     */
    public Rand() {
        this(new Random());
    }

    /**
     * Constructor.
     * @param random Java random.
     */
    public Rand(final Random random) {
        this.random = random;
    }

    /**
     * Generates a random integer.
     * @param bound Bound.
     * @return Random integer.
     */
    public int nextInt(int bound) {
        return this.random.nextInt(bound);
    }

    /**
     * Flips a coin.
     * @return True if heads, false if tails.
     */
    public boolean flip() {
        return this.random.nextBoolean();
    }

    /**
     * Generates a random string for a given regex.
     * @param regex Regex for which to generate a string.
     * @return Random string.
     */
    public String regex(final String regex) {
        return new Generex(regex).random();
    }

    /**
     * Generates a random double.
     * @return Random double.
     */
    public double nextDouble() {
        return this.random.nextDouble();
    }

}