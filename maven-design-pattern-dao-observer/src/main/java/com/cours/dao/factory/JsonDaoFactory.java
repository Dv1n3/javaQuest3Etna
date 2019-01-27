package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.dao.impl.json.JsonPersonneDaoImpl;
import com.cours.observer.MySubjectObserver;

public class JsonDaoFactory extends AbstractDaoFactory {

    private IPersonneDao personneDao = null;
    private MySubjectObserver mySubjectObserver = null;

    public JsonDaoFactory(MySubjectObserver subject) {

        this.mySubjectObserver = subject;
        this.personneDao = new JsonPersonneDaoImpl(subject);
    }

    @Override
    public IPersonneDao getPersonneDao() {
        return personneDao;
    }

    @Override
    public void updateSource() {
        if (!personneDao.sendNotification()){
            if(mySubjectObserver.getState() == 1)
                personneDao.delete(mySubjectObserver.getPerson());
            else if(mySubjectObserver.getState() == 2){
                personneDao.create(mySubjectObserver.getPerson());
            }
        }
    }
}
