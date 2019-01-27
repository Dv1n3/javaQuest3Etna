package com.cours.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ElHadji
 */
public class Personne implements Comparable<Personne>{

    public Personne(int idPersonne, String prenom, String nom, Double poids, Double taille, String rue, String ville, String codePostal) {
        this.idPersonne = idPersonne;
        this.prenom = prenom;
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
    }
    public int idPersonne;

    public String prenom;

    public String nom;

    public Double poids;

    public Double taille;

    public String rue;

    public String ville;

    public String codePostal;

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Personne() {
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Personne{"
                + "id=" + idPersonne
                + ", prenom='" + prenom + '\''
                + ", nom='" + nom + '\''
                + ", poids=" + poids
                + ", taille=" + taille
                + ", rue='" + rue + '\''
                + ", ville='" + ville + '\''
                + ", codePostal='" + codePostal + '\''
                + '}';
    }

   
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personne other = (Personne) obj;
        return this.idPersonne == other.idPersonne;
    }

    public Personne(String prenom, String nom, Double poids, Double taille, String rue, String ville, String codePostal) {
        this.prenom = prenom;
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIdPersonne();
        result = 31 * result + getPrenom().hashCode();
        result = 31 * result + getNom().hashCode();
        result = 31 * result + getPoids().hashCode();
        result = 31 * result + getTaille().hashCode();
        result = 31 * result + getRue().hashCode();
        result = 31 * result + getVille().hashCode();
        result = 31 * result + getCodePostal().hashCode();
        return result;
    }

//    public Personne() {
    //   }
    //Indice masse corporelle
    public Double getImc() {
        return (this.getPoids()) / (this.getTaille() * this.getTaille());
    }

    public boolean isMaigre() {
        return (this.getImc() < 18.5 && this.getImc() > 16.5);
    }

    public boolean isSurPoids() {
        return (this.getImc() < 30 && this.getImc() > 25);
    }

    public boolean isObese() {
        return (this.getImc() > 30);
    }

    @Override
    public int compareTo(Personne o) {
        Personne p = (Personne) o;
        return this.idPersonne - p.idPersonne;
    }
}