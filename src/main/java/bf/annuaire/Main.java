package bf.annuaire;

import bf.annuaire.repository.ContactRepository;
import bf.annuaire.service.ContactManager;
import bf.annuaire.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        String chemin = "contacts.txt";  // nom du fichier local
        ContactRepository repo = new ContactRepository(chemin);  // couche d'accès fichier
        ContactManager manager = new ContactManager(repo);       // couche métier
        ConsoleUI ui = new ConsoleUI(manager);                   // interface console

        ui.demarrer();  // démarrer le programme
    }
}
