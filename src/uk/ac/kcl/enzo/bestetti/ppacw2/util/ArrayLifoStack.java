package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * This class is part of the "White Souls" application.
 * This class represents last-in-first-out stack objects. This is achieved through the LifoStack interface.
 * This class also represents a collection, as LifoStack<E> extends Collection<E>.
 * <p>
 * The methods of the interface are implemented in this class via the Deque<E> interface, which creates a list of items which
 * accepts modifications from either end. The interface methods essentially rename the Deque methods to make it more "stack-like".
 * <p>
 * Methods from the Collection<E> interface are inherited here, but few changes are made to their implementation.
 * Objects of this class are meant to behave like a stack, so only peek(), pop(), push(), and clear() should be used.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class ArrayLifoStack<E> implements LifoStack<E> {

    //ATTRIBUTES
    private final Deque<E> deque = new ArrayDeque<>();
    //ATTRIBUTES

    /**
     * @return the element at the top of the stack.
     **/
    @Override
    public E peek() {
        return deque.getFirst();
    }


    /**
     * Remove the element at the top of the stack.
     **/
    @Override
    public E pop() {
        return deque.removeFirst();
    }


    /**
     * Add an element to the top of the stack.
     **/
    @Override
    public void push(E item) {
        deque.addFirst(item);
    }

    /**
     * @return the number of elements in the stack.
     **/
    @Override
    public int size() {
        return deque.size();
    }

    /**
     * @return true if the size of the stack is 0. False otherwise.
     **/
    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * @param o, meaning any object.
     * @return true if the element is in the stack. False otherwise.
     **/
    @Override
    public boolean contains(Object o) {
        return deque.contains(o);
    }

    /**
     * @return the stack iterator.
     **/
    @Override
    public Iterator<E> iterator() {
        return deque.iterator();
    }

    /**
     * @return the stack as an array.
     **/
    @Override
    public Object[] toArray() {
        return deque.toArray();
    }

    /**
     * Remove all elements from the stack.
     **/
    @Override
    public void clear() {
        deque.clear();
    }

    /**
     * Following methods are not relevant for this implementation. They are the standard implementation of the Collection<> interface.
     **/
    @Deprecated
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Deprecated
    @Override
    public boolean add(E e) {
        return false;
    }

    @Deprecated
    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Deprecated
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Deprecated
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Deprecated
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Deprecated
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


}
