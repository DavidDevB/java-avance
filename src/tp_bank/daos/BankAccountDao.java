
package tp_bank.daos;

import tp_bank.models.BankAccount;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankAccountDao extends Dao {
    
    @Override
    public BankAccount save(BankAccount account) {
        // Logic to save the bank account to the database
        String sql = "INSERT INTO bank_accounts (number, owner, balance) VALUES (?, ?, ?)";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getNumber());
            stmt.setString(2, account.getOwner());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public BankAccount read(String number) {
        // Logic to read the bank account from the database by number
        String sql = "SELECT * FROM bank_accounts WHERE number = ?";
        BankAccount account = null;
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new BankAccount(rs.getString("number"), rs.getString("owner"));
                account.setBalance(rs.getDouble("balance"));
                account.setAccountId(rs.getInt("accountId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public BankAccount update(BankAccount account) {
        // Logic to update the bank account in the database
        String sql = "UPDATE bank_accounts SET number = ?, owner = ?, balance = ? WHERE accountId = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getNumber());
            stmt.setString(2, account.getOwner());
            stmt.setDouble(3, account.getBalance());
            stmt.setInt(4, account.getAccountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account; // Return the updated account (for simplicity)
    }

    @Override
    public boolean delete(String number) {
        // Logic to delete the bank account from the database by number
        String sql = "DELETE FROM bank_accounts WHERE number = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, number);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; // Placeholder
    }
}
