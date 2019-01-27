package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.observer.MyObserver;
import com.cours.observer.MySubjectObserver;

public abstract class AbstractDaoFactory extends MyObserver {

    public abstract IPersonneDao getPersonneDao();

    public enum FactoryType {

        MANUAL_DAO, CSV_DAO, XML_DAO, JSON_DAO, SQL_DAO
    }

    public static AbstractDaoFactory getDaoFactory(FactoryType type) {
        MySubjectObserver sujet = new MySubjectObserver();

        //XmlDaoFactory xmlDaoFactory = new XmlDaoFactory(sujet);
        //CsvDaoFactory csvDaoFactory = new CsvDaoFactory(sujet);
        JsonDaoFactory jsonDaoFactory = new JsonDaoFactory(sujet);
        CsvDaoFactory csvDaoFactory = new CsvDaoFactory(sujet);

        sujet.addObserveur(jsonDaoFactory);
        sujet.addObserveur(csvDaoFactory);
        switch (type) {
            case CSV_DAO:
                jsonDaoFactory.getPersonneDao().setSendNotification(false);
                return csvDaoFactory;

            case XML_DAO:
                return null;

            case JSON_DAO:
                csvDaoFactory.getPersonneDao().setSendNotification(false);
                return jsonDaoFactory;
        }
        return null;
    }
}