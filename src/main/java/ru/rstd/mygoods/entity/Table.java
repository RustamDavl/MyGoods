package ru.rstd.mygoods.entity;

import java.util.List;
import java.util.Optional;

public interface Table<E> {
    E selectById(Long id);

    List<E> selectAll();

    Long insert(E entity);

    E update (E entity);
    void delete(Long id);
}
