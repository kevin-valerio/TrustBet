package salasca_valerio.trustbet;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database    {

    private static final String TAG = "JDBC_LOG";
    private static String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "sql11229330";
    private static String password = "RJsHnqlb7n";
    private static Connection con;
    private static String urlstring;


    /*
        @Usage :
        private Connection con = Database.getConnection();
        private Statement stmt = con.createStatement();
        private ResultSet rs = stmt.executeQuery(sql);
     */


    public Database() {
        getConnection();
    }

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
            } catch (SQLException ex) {
                 Log.d(TAG, "Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
             System.out.println("Driver not found.");
        }
        return con;
    }
}