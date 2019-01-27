/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.dao.impl.csv;

import com.cours.dao.IDao;
import com.cours.observer.MySubjectObserver;
import java.util.List;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractCsvDao<T> implements IDao<T> {

    private String csvPathFile = null;
    private Class<T> myClass = null;
    private MySubjectObserver mySubject = null;

    public AbstractCsvDao(Class<T> myClass, MySubjectObserver mySubject, String csvPathFile) {
        this.myClass = myClass;
        this.mySubject = mySubject;
        this.csvPathFile = csvPathFile;
    }

    public AbstractCsvDao(Class<T> myClass, String csvPathFile) {
        this.myClass = myClass;
        this.csvPathFile = csvPathFile;
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
