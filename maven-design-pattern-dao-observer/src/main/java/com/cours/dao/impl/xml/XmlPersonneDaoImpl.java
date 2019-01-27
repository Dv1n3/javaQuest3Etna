package com.cours.dao.impl.xml;

import com.cours.dao.IPersonneDao;
import com.cours.entities.Personne;
import com.cours.observer.MySubjectObserver;
import com.cours.utils.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlPersonneDaoImpl /*extends AbstractXmlDao<Personne>*/ implements IPersonneDao {

    private final String personnesXmlPathFile = Constants.PERSONNES_XML_PATH_FILE;
    private MySubjectObserver subject = null;
    private boolean sendNotification = true;

    public XmlPersonneDaoImpl() {
    }

    public XmlPersonneDaoImpl(MySubjectObserver subject) {
        //super(Personne.class, subject, Constants.PERSONNES_XML_PATH_FILE);
        this.subject = subject;
    }

    public Personne createPersonneWithFileObject(Element elementPersonne) {
        Personne personne = new Personne();
        if (elementPersonne != null && elementPersonne.hasAttribute("id") && elementPersonne.getElementsByTagName("prenom") != null
                && elementPersonne.getElementsByTagName("nom") != null && elementPersonne.getElementsByTagName("poids") != null
                && elementPersonne.getElementsByTagName("taille") != null && elementPersonne.getElementsByTagName("rue") != null
                && elementPersonne.getElementsByTagName("ville") != null && elementPersonne.getElementsByTagName("codePostal") != null
                && elementPersonne.getElementsByTagName("prenom").item(0) != null && elementPersonne.getElementsByTagName("nom").item(0) != null
                && elementPersonne.getElementsByTagName("poids").item(0) != null && elementPersonne.getElementsByTagName("taille").item(0) != null
                && elementPersonne.getElementsByTagName("rue").item(0) != null && elementPersonne.getElementsByTagName("ville").item(0) != null
                && elementPersonne.getElementsByTagName("codePostal").item(0) != null) {

            personne.setIdPersonne(Integer.parseInt(elementPersonne.getAttribute("id")));
            personne.setPrenom(elementPersonne.getElementsByTagName("prenom").item(0).getTextContent());
            personne.setNom(elementPersonne.getElementsByTagName("nom").item(0).getTextContent());
            personne.setPoids(Double.parseDouble(elementPersonne.getElementsByTagName("poids").item(0).getTextContent()));
            personne.setTaille(Double.parseDouble(elementPersonne.getElementsByTagName("taille").item(0).getTextContent()));
            personne.setRue(elementPersonne.getElementsByTagName("rue").item(0).getTextContent());
            personne.setVille(elementPersonne.getElementsByTagName("ville").item(0).getTextContent());
            personne.setCodePostal(elementPersonne.getElementsByTagName("codePostal").item(0).getTextContent());

        }
        return personne;
    }


    @Override
    public List<Personne> findAll() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Personne> listePersonnes = new ArrayList<>();
        DocumentBuilder builder = null;
        Personne person = null;
        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(personnesXmlPathFile);
            NodeList personneListe = doc.getElementsByTagName("personne");
            for (int i = 0; i < personneListe.getLength(); i++) {
                Node p = personneListe.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element personne = (Element) p;
                    if (personne != null && personne instanceof Element) {
                        person = createPersonneWithFileObject(personne);
                    }
                    if(person != null && person instanceof Personne) {
                        listePersonnes.add(person);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listePersonnes;
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

    @Override
    public Personne create(Personne obj) {

        return null;
    }

    @Override
    public boolean delete(Personne person) {
        return true;
    }

    @Override
    public int generateIdNewPersonne() {
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
