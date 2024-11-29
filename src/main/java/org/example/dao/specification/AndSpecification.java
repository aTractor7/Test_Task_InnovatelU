package org.example.dao.specification;

import java.util.List;

public class AndSpecification<T> implements Specification<T> {

    private final List<Specification<T>> specifications;

    public AndSpecification(List<Specification<T>> specifications) {
        this.specifications = specifications;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        return specifications.stream().allMatch(spec -> spec.isSatisfiedBy(item));
    }
}
