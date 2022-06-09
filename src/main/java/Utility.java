import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Utility {
    static Connection con ;
    public static Statement makeConnection(String forName, String getConn,
                                           String root, String password) throws ClassNotFoundException, SQLException {
        Class.forName(forName);
        con = DriverManager.getConnection(getConn, root, password);
        Statement st = con.createStatement();
        return st;
    }
}
