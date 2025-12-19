package tp_bank;
import java.util.Scanner;
import tp_bank.models.Advisor;
import tp_bank.models.BankAccount;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans l'application de gestion bancaire !");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez vos identifiants pour continuer.");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Les identifiants ne peuvent pas être vides.");
            scanner.close();
            throw new IllegalArgumentException("Identifiants invalides.");
        }
            Advisor advisor = new Advisor(firstName, lastName);
        System.out.println("Veuillez utiliser les fonctionnalités disponibles pour gérer les comptes bancaires.");
        System.out.println("Entrez le mot de passe pour vous connecter: ");
        String password = scanner.nextLine();
        if (password.equals("admin123")) {
            System.out.println("Connexion réussie !");
            System.out.println("Voulez vous créer ou gérer des comptes bancaires ?");
            String command = scanner.nextLine();
            System.out.println("Vous avez choisi: " + command);
            if (command.equalsIgnoreCase("créer")) {
                System.out.println("Création de compte bancaire...");
                System.out.println("Veuilez entrer un numéro de compte au format 'FR-XXXX-XXXX': ");
                String accountNumber = scanner.nextLine();
                if (accountNumber.isEmpty() || !accountNumber.matches("FR-\\d{4}-\\d{4}")) {
                    System.out.println("Numéro de compte invalide.");
                    scanner.close();
                    throw new IllegalArgumentException("Numéro de compte invalide.");
                }
                System.out.println("Veuillez entrer le prénom du propriétaire du compte: ");
                String ownerFirstName = scanner.nextLine();
                System.out.println("Veuillez entrer le nom du propriétaire du compte: ");
                String ownerLastName = scanner.nextLine();
                if (ownerFirstName.isEmpty() || ownerLastName.isEmpty()) {
                    System.out.println("Le nom du propriétaire ne peut pas être vide.");
                    scanner.close();
                    throw new IllegalArgumentException("Nom du propriétaire invalide.");
                }
                try {
                    advisor.openAccount(accountNumber, ownerFirstName, ownerLastName);
                    System.out.println("Compte bancaire " + accountNumber + " créé avec succès !");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (command.equalsIgnoreCase("gérer")) {
                System.out.println("Gestion des comptes bancaires...");
                System.out.println("Voulez-vous effectuer un dépôt, un retrait ou un transfert ?");
                String action = scanner.nextLine();
                System.out.println("Vous avez choisi: " + action);
                System.out.println("--------------------------------");
                if (action.equalsIgnoreCase("dépôt")) {
                    System.out.println("Dépôt d'argent...");
                    // Logique de dépôt
                    System.out.println("Veuillez entrer le numéro de compte: ");
                    String accountNumber = scanner.nextLine();
                    if (accountNumber.isEmpty() || !accountNumber.matches("FR-\\d{4}-\\d{4}")) {
                        System.out.println("Numéro de compte invalide.");
                        scanner.close();
                        throw new IllegalArgumentException("Numéro de compte invalide.");
                    }
                    BankAccount account = advisor.getAccount(accountNumber);
                    System.out.println("Veuillez entrer le montant à déposer: ");
                    double amount = scanner.nextDouble();
                    try {
                        advisor.makeDeposit(account, amount);
                        System.out.println("Dépôt effectué avec succès.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); 
                    }
                } else if (action.equalsIgnoreCase("retrait")) {
                    System.out.println("Retrait d'argent...");
                    // Logique de retrait
                    System.out.println("Veuillez entrer le numéro de compte: ");
                    String accountNumber = scanner.nextLine();
                    if (accountNumber.isEmpty() || !accountNumber.matches("FR-\\d{4}-\\d{4}")) {
                        System.out.println("Numéro de compte invalide.");
                        scanner.close();
                        throw new IllegalArgumentException("Numéro de compte invalide.");
                    }
                    BankAccount account = advisor.getAccount(accountNumber);
                    System.out.println("Veuillez entrer le montant à retirer: ");
                    double amount = scanner.nextDouble();
                    try {
                        advisor.makeWithdrawal(account, amount);
                        System.out.println("Retrait effectué avec succès.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); 
                    }
                } else if (action.equalsIgnoreCase("transfert")) {
                    System.out.println("Transfert d'argent...");
                    // Logique de transfert
                    System.out.println("Veuillez entrer le numéro de compte source: ");
                    String fromAccountNumber = scanner.nextLine();
                    if (fromAccountNumber.isEmpty() || !fromAccountNumber.matches("FR-\\d{4}-\\d{4}")) {
                        System.out.println("Numéro de compte source invalide.");
                        scanner.close();
                        throw new IllegalArgumentException("Numéro de compte invalide.");
                    }
                    BankAccount fromAccount = advisor.getAccount(fromAccountNumber);
                    System.out.println("Veuillez entrer le numéro de compte destination: ");
                    String toAccountNumber = scanner.nextLine();
                    if (toAccountNumber.isEmpty() || !toAccountNumber.matches("FR-\\d{4}-\\d{4}")) {
                        System.out.println("Numéro de compte destination invalide.");
                        scanner.close();
                        throw new IllegalArgumentException("Numéro de compte invalide.");
                    }
                    BankAccount toAccount = advisor.getAccount(toAccountNumber);
                    System.out.println("Veuillez entrer le montant à transférer: ");
                    double amount = scanner.nextDouble();
                    try {
                        advisor.makeTransfer(fromAccount, toAccount, amount);
                        System.out.println("Transfert effectué avec succès.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); 
                    }
                    scanner.close();
                } else {
                    System.out.println("Action non reconnue.");
                    scanner.close();
                    throw new IllegalArgumentException("Action invalide.");
                }
            } else {
                System.out.println("Commande non reconnue.");
                scanner.close();
                throw new IllegalArgumentException("Commande invalide.");
                
            }
        } else {
            System.out.println("Mot de passe incorrect. Accès refusé.");
            scanner.close();
        }
    }
}
