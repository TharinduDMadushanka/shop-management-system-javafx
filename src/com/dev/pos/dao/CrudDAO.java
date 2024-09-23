package com.dev.pos.dao;

import com.dev.pos.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {

    public boolean save(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(ID id) throws Exception;

    public T find(ID id) throws Exception;

    public List<T> findAll() throws Exception;

    public List<T> search(ID id) throws Exception;

}
