package com.cours.dao.impl.csv;

import com.cours.dao.IPersonneDao;
import com.cours.entities.Personne;
import com.cours.observer.MySubjectObserver;
import com.cours.utils.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvPersonneDaoImpl /*extends AbstractCsvDao<Personne>*/ implements IPersonneDao {

    private final String personnesCsvPathFile = Constants.PERSONNES_CSV_PATH_FILE;
    private MySubjectObserver subject = null;
    private boolean sendNotification = true;

    public CsvPersonneDaoImpl() {
    }

    public CsvPersonneDaoImpl(MySubjectObserver subject) {
        //super(Personne.class, subject, Constants.PERSONNES_CSV_PATH_FILE);
        this.subject = subject;
    }

    @Override
    public Personne findById(int idPersonne) {

        List<Personne> listePersonne = findAll();
        if (listePersonne != null) {
            Iterator<Personne> iterator = listePersonne.iterator();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    Personne person = (Personne) iterator.next();
                    if (person != null && person instanceof Personne) {
                        if (person.getIdPersonne() == idPersonne) {
                            return person;
                        }
                    }
                }
            }
        }
        return null;
    }

    private Personne createPersonneWithFileObject(String[] tabPersonne) {
        Personne personnes = new Personne();

        if (tabPersonne.length >= 8 && null != tabPersonne[0] && null != tabPersonne[1] && null != tabPersonne[2] && null != tabPersonne[3]
                && null != tabPersonne[4] && null != tabPersonne[5] && null != tabPersonne[6] && null != tabPersonne[7]) {

            personnes.setIdPersonne(Integer.parseInt(tabPersonne[0]));
            personnes.setPrenom(tabPersonne[1]);
            personnes.setNom(tabPersonne[2]);
            personnes.setPoids(Double.parseDouble(tabPersonne[3]));
            personnes.setTaille(Double.parseDouble(tabPersonne[4]));
            personnes.setRue(tabPersonne[5]);
            personnes.setVille(tabPersonne[6]);
            personnes.setCodePostal(tabPersonne[7]);
        }
        return personnes;
    }

    @Override
    public Personne create(Personne obj) {
        Personne personne = new Personne();
        BufferedWriter writer = null;
        String str = "";
        int id = generateIdNewPersonne();

        if (obj.prenom != null && obj.nom != null && obj.poids != null && obj.taille != null && obj.rue != null
                && obj.ville != null && obj.codePostal != null) {

            if (id > 0) {
                personne.setIdPersonne(id);
            }

            str += id + ";";

            personne.setPrenom(obj.prenom);
            str += obj.prenom + ";";

            personne.setNom(obj.nom);
            str += obj.nom + ";";

            personne.setPoids(obj.poids);
            str += obj.poids + ";";

            personne.setTaille(obj.taille);
            str += obj.taille + ";";

            personne.setRue(obj.rue);
            str += obj.rue + ";";

            personne.setVille(obj.ville);
            str += obj.ville + ";";

            personne.setCodePostal(obj.codePostal);
            str += obj.codePostal;
        }

        if (sendNotification){
            subject.setPerson(obj);
            subject.setState(2);
        }

        try {
            writer = new BufferedWriter(new FileWriter(Constants.PERSONNES_CSV_PATH_FILE, true));
            writer.write("\n");
            writer.append(str);
            writer.append("\n");

        } catch (IOException ex) {
            Logger.getLogger(CsvPersonneDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return personne;
    }

    @Override
    public boolean delete(Personne person) {
        boolean remove = false;
        List<Personne> listePersonne = findAll();
        if (listePersonne != null) {
            Iterator<Personne> iterator = listePersonne.iterator();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    Personne pers = (Personne) iterator.next();
                    if (person != null && pers != null) {
                        if (pers.getIdPersonne() == person.getIdPersonne()) {
                            System.out.println("j'ai trouvé" + pers);
                            remove = true;
                            if (sendNotification){
                                subject.setPerson(person);
                                subject.setState(1);
                            }
                        }
                    } else {
                        remove = false;
                    }
                }
            }
        }

        if (remove) {
            listePersonne.remove(person);
            Iterator<Personne> iterator2 = listePersonne.iterator();
            BufferedWriter writer = null;

            try {
                writer = new BufferedWriter(new FileWriter(Constants.PERSONNES_CSV_PATH_FILE, false));
                writer.write("idPersonne;Prenom;Nom;Poids;Taille;Rue;Ville;Code Postal");
                while (iterator2.hasNext()) {
                    Personne obj = iterator2.next();
                    String str = "";

                    if (obj.prenom != null && obj.nom != null && obj.poids != null && obj.taille != null && obj.rue != null
                            && obj.ville != null && obj.codePostal != null) {
                        str += obj.idPersonne + ";";
                        str += obj.prenom + ";";
                        str += obj.nom + ";";
                        str += obj.poids + ";";
                        str += obj.taille + ";";
                        str += obj.rue + ";";
                        str += obj.ville + ";";
                        str += obj.codePostal;
                        writer.append("\n");
                        writer.append(str);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CsvPersonneDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return remove;
    }

    @Override
    public List<Personne> findAll() {
        BufferedReader br = null;

        List<Personne> personnes = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(personnesCsvPathFile));
            int i = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (i > 0) {
                    String[] tabPersonne = line.split(cvsSplitBy);
                    Personne pers = createPersonneWithFileObject(tabPersonne);
                    if (pers != null) {
                        personnes.add(pers);
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return personnes;
    }

    @Override
    public int generateIdNewPersonne() {
        List<Personne> listePersonne = findAll();
        if (listePersonne != null) {
            Personne person = listePersonne.get(listePersonne.size() - 1);
            if (person != null) {
                return person.getIdPersonne() + 1;
            }
        }
        return 0;
    }

    @Override
    public boolean sendNotification() {
        return sendNotification;
    }

    @Override
    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
