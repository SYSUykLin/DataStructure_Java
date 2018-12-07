package SegmentTree;

public interface Merger<E> {
    E merger(E a, E b);
}
