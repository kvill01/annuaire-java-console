package bf.annuaire.repository;

import bf.annuaire.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private final File fichier;

    public ContactRepository(String cheminFichier) {
        this.fichier = new File(cheminFichier);
        try {
            if (!fichier.exists()) {
                fichier.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la création du fichier", e);
        }
    }

    public void sauvegarder(Contact contact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true))) {
            writer.write(contact.getNom() + ";" + contact.getPrenom() + ";" + contact.getNumero());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier", e);
        }
    }

    public List<Contact> chargerTous() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split(";");
                if (parties.length == 3) {
                    contacts.add(new Contact(parties[0], parties[1], parties[2]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
        return contacts;
    }

    public boolean supprimerParNumero(String numero) {
        List<Contact> tous = chargerTous();
        List<Contact> gardes = new ArrayList<>();

        boolean supprime = false;

        for (Contact c : tous) {
            if (c.getNumero().equals(numero)) {
                supprime = true; // ✅ on a trouvé le contact à supprimer
            } else {
                gardes.add(c);
            }
        }

        // Réécriture du fichier
        try (BufferedWriter w = new BufferedWriter(new FileWriter(fichier, false))) {
            for (Contact c : gardes) {
                w.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return supprime; // ✅ On indique si le contact a été supprimé
    }

    public void viderListe() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))){
            System.out.println("Le repertoire a été vidé avec succès !");
        }catch (IOException e) {
            throw new RuntimeException("Erreur lors de la vidange" + e);
        }
    }
}