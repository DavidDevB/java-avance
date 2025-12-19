
package tp_bank.models;

public class BankAccount {
    
    private int accountId;
    private String number;
    private String owner;
    private double balance;

    public BankAccount(String number, String owner) {
        this.number = number;
        this.owner = owner;
        this.balance = 0.0;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toString() {
        return "BankAccount [accountId=" + accountId + ", number=" + number + ", owner=" + owner + ", balance=" + balance + "]";
    }
}
