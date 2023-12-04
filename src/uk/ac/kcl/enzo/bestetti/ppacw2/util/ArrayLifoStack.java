package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class ArrayLifoStack<E> implements LifoStack<E> {

    private final Deque<E> deque = new ArrayDeque<>();

    @Override
    public E peek() {
        return deque.getFirst();
    }

    @Override
    public E pop() {
        return deque.removeFirst();
    }

    @Override
    public void push(E item) {
        deque.addFirst(item);
    }

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return deque.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return deque.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return deque.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }


    @Override
    public void clear() {
        deque.clear();
    }
}
