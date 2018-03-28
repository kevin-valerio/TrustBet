package salasca_valerio.trustbet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.fabric.sdk.android.Fabric;
public class Database {

    private static final String TAG = "JDBC_LOG";
    private static String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "sql11229330";
    private static String password = "RJsHnqlb7n";
    private static Connection con;
    private static String urlstring;


    /*
        @Usage :
        private Connection con = ConnectionManager.getConnection();
        private Statement stmt = con.createStatement();
        private ResultSet rs = stmt.executeQuery(sql);

     */
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