package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import java.util.Collection;

/**
 * This interface is part of the White Souls application.
 * <p>
 * The interface is used to create a last-in-first-out (lifo) collection.
 * Methods declared in this class have the same name as those of the traditional Stack class, but they will be overridden in
 * the implementations of the interface.
 * <p>
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public interface LifoStack<E> extends Collection<E> {

    /**
     * Normally used to retrieve the element at the top of the stack.
     **/
    E peek();

    /**
     * Normally used to remove the element at the top of the stack.
     **/
    E pop();

    /**
     * Normally used to add an element to the top of the stack.
     **/
    void push(E item);

}
