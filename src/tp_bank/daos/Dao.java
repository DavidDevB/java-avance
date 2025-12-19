
package tp_bank.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tp_bank.models.BankAccount;

public abstract class Dao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String USER = "root";
    private static final String PASSWORD = "weR(@!-Zo_ho(cJc";

    protected static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion à la base de données établie");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la connexion à la base de données");
            e.printStackTrace();
            throw new RuntimeException("Impossible de se connecter à la base de données", e);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Connexion fermée");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract BankAccount save(BankAccount obj);

    public abstract BankAccount read(int id);

    public abstract BankAccount update(BankAccount obj);

    public abstract boolean delete(int id);
}
