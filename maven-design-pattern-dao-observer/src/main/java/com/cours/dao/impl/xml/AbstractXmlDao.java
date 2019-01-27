/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.dao.impl.xml;

import com.cours.dao.IDao;
import com.cours.observer.MySubjectObserver;
import java.util.List;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractXmlDao<T> implements IDao<T> {

    private String xmlPathFile = null;
    private Class<T> myClass = null;
    private MySubjectObserver mySubject = null;

    public AbstractXmlDao(Class<T> myClass, MySubjectObserver mySubject, String xmlPathFile) {
        this.myClass = myClass;
        this.mySubject = mySubject;
        this.xmlPathFile = xmlPathFile;
    }

    public AbstractXmlDao(Class<T> myClass, String xmlPathFile) {
        this.myClass = myClass;
        this.xmlPathFile = xmlPathFile;
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
