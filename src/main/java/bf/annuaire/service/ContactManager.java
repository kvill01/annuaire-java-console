package bf.annuaire.service;

import bf.annuaire.model.Contact;
import bf.annuaire.repository.ContactRepository;
 
import java.util.List;

public class ContactManager {
    private final ContactRepository repository;

    public ContactManager(ContactRepository repository) {
        this.repository = repository;
    }

    public void ajouterContact(String nom, String prenom, String numero) {
        Contact contact = new Contact(nom, prenom, numero);
        repository.sauvegarder(contact);
    }

    public boolean supprimerContact(String numero) {
        return repository.supprimerParNumero(numero);
    }

    public List<Contact> listerContacts() {
        return repository.chargerTous();
    }
}