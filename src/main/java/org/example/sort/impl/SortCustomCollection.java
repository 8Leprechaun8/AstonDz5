package org.example.sort.impl;
import org.example.collections.*;

import java.util.Comparator;
import java.util.stream.StreamSupport;

public class SortCustomCollection {


    public <T> CustomCollection<T> sort(CustomCollection<T> collection, Comparator<T> comparator) {
        return StreamSupport.stream(collection.spliterator(), false)
                .sorted(comparator)
                .collect(CustomArrayList.toCustomArrayList());
    }
}
