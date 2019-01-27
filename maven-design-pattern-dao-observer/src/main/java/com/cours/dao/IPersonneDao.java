package com.cours.dao;

import com.cours.entities.Personne;

import java.util.List;

public interface IPersonneDao {

    public List<Personne> findAll();

    public Personne findById(int id);

    public int generateIdNewPersonne();

    public Personne create(Personne p);

    public boolean delete(Personne p);

    public boolean sendNotification();

    public void setSendNotification(boolean sendNotification);
}
