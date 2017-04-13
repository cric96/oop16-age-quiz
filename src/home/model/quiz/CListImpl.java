package home.model.quiz;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import home.utility.CList;
/**
 *
 * @param <X>
 */
public class CListImpl<X> implements CList<X> {
    private final List<X> elements;
    private final int pos;
    CListImpl(final List<X> elements, final int pos) {
        this.elements = elements;
        this.pos = pos;
    }
    private int relativePos(final int pos) {
        return (pos + this.pos) % this.elements.size();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public X getElem(final int pos) {
        return this.elements.get(this.relativePos(pos));
    }

    @Override
    public boolean contains(final X x) {
        return this.elements.contains(x);
    }

    @Override
    public CList<X> add(final X x, final int pos) {
        final int p = this.relativePos(pos);
        final List<X> l = new LinkedList<X>(this.elements);
        l.add(p, x);
        return new CListImpl<>(l, p);
    }

    @Override
    public CList<X> shift(final int pos) {
        return new CListImpl<>(this.elements, this.relativePos(pos));
    }

    @Override
    public List<X> toList() {
        final List<X> l = new LinkedList<>(this.elements);
        Collections.rotate(l, -this.pos);
        return l;
    }

    @Override
    public String toString() {
        return "CListImpl [elements=" + elements + ", pos=" + pos + "]";
    }
}