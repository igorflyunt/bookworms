package com.softserve.bookworm.service;

import com.softserve.bookworm.dao.Dao;

public abstract class AbstractService<E, D extends Dao<E>> implements Service<E> {
    private D dao;

    public AbstractService(D dao) {
        this.dao = dao;
    }

    public D getDao() {
        return dao;
    }

    @Override
    public void save(E entity) {
        this.dao.saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        this.dao.delete(entity);
    }
}