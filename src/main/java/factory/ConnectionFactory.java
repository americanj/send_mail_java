package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static String url = "jdbc:mariadb://localhost/list_email_db?useTimezone=true&serverTimezone=UTC";
    private static String user = "igor";
    private static String password = "0122";

    private static Connection connection = null;

    static {
        conectar();
    }

    public ConnectionFactory(){
        conectar();
    }

    private static void conectar(){
        try {
            if (connection == null) {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);

                connection.setAutoCommit(false);
                System.out.println("conectou");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection recoveryConnection(){
        return connection;
    }





}
