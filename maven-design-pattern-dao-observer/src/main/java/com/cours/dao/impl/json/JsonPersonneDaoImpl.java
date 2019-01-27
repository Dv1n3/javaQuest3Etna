package com.cours.dao.impl.json;

import com.cours.dao.IPersonneDao;
import com.cours.dao.impl.csv.CsvPersonneDaoImpl;
import com.cours.entities.Personne;
import com.cours.observer.MySubjectObserver;
import com.cours.utils.Constants;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonPersonneDaoImpl /*extends AbstractJsonDao<Personne>*/ implements IPersonneDao {

    private final String personnesJsonPathFile = Constants.PERSONNES_JSON_PATH_FILE;
    private MySubjectObserver subject = null;
    private boolean sendNotification = true;

    public JsonPersonneDaoImpl() {
    }

    public JsonPersonneDaoImpl(MySubjectObserver subject) {
        //super(Personne.class, subject, Constants.PERSONNES_JSON_PATH_FILE);
        this.subject = subject;
    }

    public Personne createPersonneWithFileObject(JSONObject jsonObjectPerson) {
        if (jsonObjectPerson.containsKey("id") && null != jsonObjectPerson.get("id")
                && null != jsonObjectPerson.get("prenom") && jsonObjectPerson.containsKey("prenom") && null != jsonObjectPerson.get("nom")
                && jsonObjectPerson.containsKey("nom") && null != jsonObjectPerson.get("poids") && jsonObjectPerson.containsKey("poids")
                && null != jsonObjectPerson.get("taille") && jsonObjectPerson.containsKey("taille") && null != jsonObjectPerson.get("rue")
                && jsonObjectPerson.containsKey("rue") && null != jsonObjectPerson.get("ville") && jsonObjectPerson.containsKey("ville")
                && null != jsonObjectPerson.get("codePostal") && jsonObjectPerson.containsKey("codePostal")) {
            Personne personne = new Personne();

            personne.setIdPersonne(Integer.parseInt(jsonObjectPerson.get("id").toString()));
            personne.setPrenom(jsonObjectPerson.get("prenom").toString());
            personne.setNom(jsonObjectPerson.get("nom").toString());
            personne.setPoids(Double.parseDouble(jsonObjectPerson.get("poids").toString()));
            personne.setTaille(Double.parseDouble(jsonObjectPerson.get("taille").toString()));
            personne.setRue(jsonObjectPerson.get("rue").toString());
            personne.setVille(jsonObjectPerson.get("ville").toString());
            personne.setCodePostal(jsonObjectPerson.get("codePostal").toString());

            return personne;
        } else {
            return null;
        }
    }

    @Override
    public Personne findById(int idPersonne) {
        List<Personne> listePersonne = findAll();
        if (listePersonne != null) {
            Iterator<Personne> iterator = listePersonne.iterator();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    Personne personne = (Personne) iterator.next();
                    if (personne != null && personne instanceof Personne) {
                        if (personne.idPersonne == idPersonne) {
                            return personne;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Personne create(Personne obj) {
        FileWriter file = null;
        int id = generateIdNewPersonne();

        if (id > 0) {
            obj.setIdPersonne(id);
        }

        List<Personne> listePersonne = findAll();

        String str = "";
        JSONObject pers = new JSONObject();
        pers.put("id", id);
        pers.put("prenom", obj.prenom);
        pers.put("nom", obj.nom);
        pers.put("poids", obj.poids);
        pers.put("taille", obj.taille);
        pers.put("rue", obj.rue);
        pers.put("ville", obj.ville);
        pers.put("codePostal", obj.codePostal);

        try {
            file = new FileWriter(Constants.PERSONNES_JSON_PATH_FILE, false);

            file.write("{\"personnes\":[");
            for (Personne psn : listePersonne) {
                if (psn.idPersonne != 0 && psn.prenom != null && psn.nom != null && psn.poids != null && psn.taille != null && psn.rue != null && psn.ville != null && psn.codePostal != null) {
                    str += "{\"id\":" + psn.idPersonne + ",";
                    str += "\"prenom\":" + " \"" + psn.prenom + "\"" + ",";
                    str += "\"nom\":" + "\"" + psn.nom + "\"" + ",";
                    str += "\"poids\":" + psn.poids + ",";
                    str += "\"taille\":" + psn.taille + ",";
                    str += "\"rue\":" + "\"" + psn.rue + "\"" + ",";
                    str += "\"ville\":" + "\"" + psn.ville + "\"" + ",";
                    str += "\"codePostal\":" + "\"" + psn.codePostal + "\"" + "},\n";
                }
            }
            file.append(str);

            String strpers = toJString(pers);

            if (pers != null && strpers != null) {
                file.append(strpers);
            }

            file.append("]}");
        } catch (IOException ex) {
            Logger.getLogger(CsvPersonneDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(sendNotification){
            subject.setPerson(obj);
            subject.setState(2);
        }

        return obj;
    }

    public String toJString(JSONObject pers) {
        if (pers != null && ((int)pers.get("id") > 0) && pers.get("id") != null && pers.get("nom") != null && pers.get("poids")!=null && pers.get("taille") != null && pers.get("rue") != null && pers.get("ville") != null && pers.get("codePostal") != null) {
            return "{\"id\":" + pers.get("id") + ','
                    + "\"prenom\":" + "\"" + pers.get("prenom") + "\"" + ','
                    + "\"nom\":" + "\"" + pers.get("nom") + "\"" + ','
                    + "\"poids\":" + pers.get("poids") + ','
                    + "\"taille\":" + pers.get("taille") + ','
                    + "\"rue\":" + "\"" + pers.get("rue") + "\"" + ','
                    + "\"ville\":" + "\"" + pers.get("ville") + "\"" + ','
                    + "\"codePostal\":" + "\"" + pers.get("codePostal") + "\"}\n";
        }
        return null;
    }

    @Override
    public boolean delete(Personne person) {
        BufferedWriter file = null;
        boolean remove = false;
        List<Personne> listePersonne = findAll();
        if (listePersonne != null) {
            Iterator<Personne> iterator = listePersonne.iterator();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    Personne psn = (Personne) iterator.next();
                    if (person != null && psn != null) {
                        if (person.getIdPersonne() == psn.getIdPersonne()) {
                            System.out.println("j'ai trouvé " + person);
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
            int listeLen = listePersonne.size();
            int i = 0;

            try {
                file = new BufferedWriter(new FileWriter(Constants.PERSONNES_JSON_PATH_FILE, false));
                file.write("{\"personnes\":[");

                while (iterator2.hasNext()) {
                    String str = "";

                    Personne psn = iterator2.next();
                    str += "{\"id\":" + psn.idPersonne + ",";
                    str += "\"prenom\":" + " \"" + psn.prenom + "\"" + ",";
                    str += "\"nom\":" + "\"" + psn.nom + "\"" + ",";
                    str += "\"poids\":" + psn.poids + ",";
                    str += "\"taille\":" + psn.taille + ",";
                    str += "\"rue\":" + "\"" + psn.rue + "\"" + ",";
                    str += "\"ville\":" + "\"" + psn.ville + "\"" + ",";
                    str += "\"codePostal\":" + "\"" + psn.codePostal + "\"" + "}";
                    str += (i < listeLen - 1) ? ",\n" : "\n";
                    i++;
                    file.append(str);
                }
                file.append("]}");
            } catch (IOException ex) {
                Logger.getLogger(CsvPersonneDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (file != null) {
                        file.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return remove;
    }

    @Override
    public int generateIdNewPersonne() {
        List<Personne> listePersonne = findAll();

        Personne personne = listePersonne.get(listePersonne.size() - 1);
        int id = personne.idPersonne + 1;
        return id;
    }

    @Override
    public List<Personne> findAll() {
        List<Personne> listePersonne = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object obj = null;

        try {
            obj = parser.parse(new FileReader(personnesJsonPathFile));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonPersonneDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject jsonObject = null;
        Iterator<JSONObject> iterator;
        JSONArray personnesArray = null;

        if (obj instanceof JSONObject) {
            jsonObject = (JSONObject) obj;
        }

        if (jsonObject.get("personnes") instanceof JSONArray) {
            personnesArray = (JSONArray) jsonObject.get("personnes");
        }

        if (personnesArray != null) {
            iterator = personnesArray.iterator();

            while (iterator.hasNext()) {
                JSONObject j = (JSONObject) iterator.next();
                Personne personne = createPersonneWithFileObject(j);
                if (personne != null && personne instanceof Personne) {
                    listePersonne.add(personne);
                }
            }
        }
        return listePersonne;
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
