package com.cours.main;

import com.cours.dao.IPersonneDao;
import com.cours.dao.factory.AbstractDaoFactory;
import com.cours.dao.impl.csv.CsvPersonneDaoImpl;
import com.cours.dao.impl.json.JsonPersonneDaoImpl;
import com.cours.dao.impl.xml.XmlPersonneDaoImpl;
import com.cours.entities.Personne;
import com.cours.observer.MyObserver;
import com.cours.observer.MySubjectObserver;
import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;

import java.util.List;

public class MainDao {

    public static void main(String[] args) {
        CsvPersonneDaoImpl csvP = new CsvPersonneDaoImpl();
        AbstractDaoFactory json = AbstractDaoFactory.getDaoFactory(AbstractDaoFactory.FactoryType.JSON_DAO);
        AbstractDaoFactory csv = AbstractDaoFactory.getDaoFactory(AbstractDaoFactory.FactoryType.CSV_DAO);
        JsonPersonneDaoImpl JsonPersonne = new JsonPersonneDaoImpl();
       //List<Personne> listePersonne = xml.findAll();
        //        //Personne p = xml.findById(5);
        //        //System.out.println(listePersonne);
        //        //System.out.println(p);

        //IPersonneDao personneDao = abstractJsonDaoFactory.getPersonneDao();

        //List<Personne> liste = csvP.findAll();
        //Personne personne = JsonPersonne.findById(5);
        //Personne personne = JsonPersonne.findById(5);
        Personne pers = new Personne("Adrien", "DEVAUX", 63.2, 145.0, "rue Mitterrand", "Bordeaux", "33000");
        Personne pers2 = new Personne(1,"Martin", "Marshall", 60.0, 150.0, "rue de Nantes", "Laval", "53000");

        json.getPersonneDao().delete(pers2);
        //json.getPersonneDao().create(pers);
        //csvP.create(pers);
        //System.out.println(personne);
        //JsonPersonne.create(pers);
        //System.out.println(personne);
        //csvP.delete(pers2);
        //Personne perso = JsonPersonne.create(pers);
        //JsonPersonne.delete(pers2);
       /* List<Personne> liste = JsonPersonne.findAll();
        System.out.println(liste);
        System.out.println("fini");*/
    }
}