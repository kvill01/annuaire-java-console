package bf.annuaire.ui;

import bf.annuaire.model.Contact;
import bf.annuaire.service.ContactManager;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final ContactManager manager;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(ContactManager manager) {
        this.manager = manager;
    }

    public void demarrer() {
        int choix;
        do {
            System.out.println("\n==== ANNUAIRE TELEPHONIQUE ====");
            System.out.println("1. Lister les contacts");
            System.out.println("2. Ajouter un contact");
            System.out.println("3. Supprimer un contact");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");

            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }

            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le saut de ligne

            switch (choix) {
                case 1 -> afficherContacts();
                case 2 -> ajouterContact();
                case 3 -> supprimerContact();
                case 4 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }

        } while (choix != 4);
    }

    private void afficherContacts() {
        List<Contact> contacts = manager.listerContacts();
        if (contacts.isEmpty()) {
            System.out.println("Aucun contact enregistré.");
        } else {
            System.out.println("---- Liste des contacts ----");
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    private void ajouterContact() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Téléphone : ");
        String numero = scanner.nextLine();

        manager.ajouterContact(nom, prenom, numero);
        System.out.println("Contact ajouté avec succès !");
    }

    public void supprimerContact() {
        System.out.print("Entrez le numéro du contact à supprimer : ");
        String numero = scanner.nextLine();

        boolean resultat = manager.supprimerContact(numero);
        if (resultat) {
            System.out.println("✅ Contact supprimé !!!");
        } else {
            System.out.println("❌ Aucun contact trouvé avec ce numéro !!!");
        }
    }
}
