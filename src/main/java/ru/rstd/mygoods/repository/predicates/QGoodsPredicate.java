package ru.rstd.mygoods.repository.predicates;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QGoodsPredicate {

    private final List<Predicate> predicates = new ArrayList<>();

    public static QGoodsPredicate builder() {
        return new QGoodsPredicate();
    }

    public QGoodsPredicate add(Object object, Function<Object, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Predicate build() {
        return ExpressionUtils.allOf(predicates);
    }
}
