package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.dao.impl.xml.XmlPersonneDaoImpl;
import com.cours.observer.MySubjectObserver;

public class XmlDaoFactory extends AbstractDaoFactory {

    private IPersonneDao personneDao = null;

    public XmlDaoFactory(MySubjectObserver subject) {
        this.personneDao = new XmlPersonneDaoImpl(subject);
    }

    @Override
    public IPersonneDao getPersonneDao() {
        return personneDao;
    }

    @Override
    public void updateSource() {

    }
}
