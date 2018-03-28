package salasca_valerio.trustbet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import io.fabric.sdk.android.Fabric;

public class AccueilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int RC_SIGN_IN = 9000;
    public static User mainUser;
    SignInButton signInButton;
    GoogleApiClient mgoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        initInterface(initToolbar());
        initLogin();
     }

    private void createFloatingButton() {

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AccueilActivity.this, CreatePariActivity.class);
                AccueilActivity.this.startActivity(myIntent);
            }
        });
    }

    void initLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestEmail()
                .build();

        mgoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        signInButton = header.findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mgoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
             userLoggedIn(Auth.GoogleSignInApi.getSignInResultFromIntent(data));
        }
    }

    private void userLoggedIn(GoogleSignInResult account) {


       SignInButton mSign_in_button;
       TextView mPleaseAuth;

        mSign_in_button = findViewById(R.id.sign_in_button);
        mPleaseAuth = findViewById(R.id.pleaseAuth);

        mSign_in_button.setVisibility(View.INVISIBLE);
        mPleaseAuth.setVisibility(View.INVISIBLE);




        mainUser = new User(
                account.getSignInAccount().getDisplayName(),
                account.getSignInAccount().getEmail(),
                account.getSignInAccount().getId(),
                account.getSignInAccount().getIdToken(),
                account.getSignInAccount().getAccount(),
                account.getSignInAccount().getFamilyName(),
                account.getSignInAccount().getGivenName(),
                account.getSignInAccount().getPhotoUrl()

        );


    }

    private Toolbar initToolbar() {
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        return toolbar;

    }

    private void initInterface(Toolbar toolbar) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createFloatingButton();

        initAllHeaderDetails();

    }

    @SuppressLint("SetTextI18n")
    private void initAllHeaderDetails() {

       TextView mRevenusFooter = (TextView) findViewById(R.id.revenusFooter);
//         Log.d(mainUser.getRevenus(), mainUser.getRevenus());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_creer) {
            Intent myIntent = new Intent(AccueilActivity.this, CreatePariActivity.class);
            AccueilActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
