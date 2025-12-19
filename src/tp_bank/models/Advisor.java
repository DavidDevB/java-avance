
package tp_bank.models;
import tp_bank.daos.BankAccountDao;

public class Advisor {
    private String firstName;
    private String lastName;

    public Advisor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BankAccount openAccount(String number) {
        BankAccount newAccount = new BankAccount(number, firstName + " " + lastName);
        BankAccountDao BankAccountDao = new BankAccountDao();
        BankAccount saveAccount = BankAccountDao.save(newAccount);
        return saveAccount;
    }

    public boolean closeAccount(int accountId) {
        BankAccountDao bankAccountDao = new BankAccountDao();
        return bankAccountDao.delete(accountId);
    }

    public void makeDeposit(BankAccount account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        }
        try {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            BankAccountDao bankAccountDao = new BankAccountDao();
            bankAccountDao.update(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeWithdrawal(BankAccount account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }
        try {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            BankAccountDao bankAccountDao = new BankAccountDao();
            bankAccountDao.update(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeTransfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du transfert doit être positif.");
        }
        try {
            makeWithdrawal(fromAccount, amount);
            makeDeposit(toAccount, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return "Advisor [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
