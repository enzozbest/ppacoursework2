package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import java.util.Collection;

public interface LifoStack<E> extends Collection<E> {

    E peek();

    E pop();

    void push(E item);

}
