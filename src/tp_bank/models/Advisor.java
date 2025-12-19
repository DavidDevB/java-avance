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
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        BankAccountDao bankAccountDao = new BankAccountDao();
        bankAccountDao.update(account);
    }

    public void makeWithdrawal(BankAccount account, double amount) {
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        BankAccountDao bankAccountDao = new BankAccountDao();
        bankAccountDao.update(account);
    }

    public void makeTransfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        makeWithdrawal(fromAccount, amount);
        makeDeposit(toAccount, amount);
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
