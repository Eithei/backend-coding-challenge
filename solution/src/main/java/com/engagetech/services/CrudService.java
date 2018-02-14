package com.engagetech.services;

import com.engagetech.entities.CrudEntity;

import java.util.List;

public interface CrudService<T extends CrudEntity> {
    T save(T object);

    List<T> findAll();
}
