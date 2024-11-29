package org.example.dao.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}
