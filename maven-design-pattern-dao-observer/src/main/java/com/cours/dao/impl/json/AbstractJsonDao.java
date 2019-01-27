/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.dao.impl.json;

import com.cours.dao.IDao;
import com.cours.observer.MySubjectObserver;
import java.util.List;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractJsonDao<T> implements IDao<T> {

    private String jsonPathFile = null;
    private Class<T> myClass = null;
    private MySubjectObserver subject = null;

    public AbstractJsonDao(Class<T> myClass, MySubjectObserver subject, String jsonPathFile) {
        this.myClass = myClass;
        this.subject = subject;
        this.jsonPathFile = jsonPathFile;
    }

    public AbstractJsonDao(Class<T> myClass, String jsonPathFile) {
        this.myClass = myClass;
        this.jsonPathFile = jsonPathFile;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }
}
