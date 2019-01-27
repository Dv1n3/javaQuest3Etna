package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.dao.impl.csv.CsvPersonneDaoImpl;
import com.cours.observer.MySubjectObserver;

public class CsvDaoFactory extends AbstractDaoFactory {

    private IPersonneDao personneDao = null;
    private MySubjectObserver mySubjectObserver = null;

    public CsvDaoFactory(MySubjectObserver subject) {
        this.mySubjectObserver = subject;
        this.personneDao = new CsvPersonneDaoImpl(subject);

    }

    @Override
    public IPersonneDao getPersonneDao() {
        return personneDao;
    }

    @Override
    public void updateSource() {
        if (mySubjectObserver.getState() == 1) {
            personneDao.delete(mySubjectObserver.getPerson());
        } else if (mySubjectObserver.getState() == 2) {
            personneDao.create(mySubjectObserver.getPerson());
        }
    }
}

