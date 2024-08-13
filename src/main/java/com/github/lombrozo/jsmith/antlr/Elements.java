package com.github.lombrozo.jsmith.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Elements implements Generative {

    private final Generative parent;

    private final List<Generative> elems;

    public Elements(final Generative parent) {
        this(parent, new ArrayList<>(0));
    }

    public Elements(final Generative parent, final List<Generative> elems) {
        this.parent = parent;
        this.elems = elems;
    }

    @Override
    public Generative parent() {
        return this.parent;
    }

    @Override
    public String generate() {
        return this.elems.stream().map(Generative::generate).collect(Collectors.joining(""));
    }

    @Override
    public void append(final Generative generative) {
        this.elems.add(generative);
    }
}