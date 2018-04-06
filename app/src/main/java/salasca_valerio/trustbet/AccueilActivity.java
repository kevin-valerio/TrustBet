package salasca_valerio.trustbet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

import io.fabric.sdk.android.Fabric;

public class AccueilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 9000;
    private static String TAG_NAME = "AccueilActivity";
    public static User mainUser;
    private SignInButton signInButton;
    private GoogleApiClient mgoogleApiClient;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        initInterface(initToolbar());
        initLogin();

        if(mainUser != null)
            initRecycleView();

    }

    private void initRecycleView() {

        mRecyclerView = findViewById(R.id.recycle_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AdapterPari();
        mRecyclerView.setAdapter(mAdapter);

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

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
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
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            userLoggedIn(account);
        } catch (ApiException e) {
            Log.d(TAG_NAME, "Erreur : " + e.getMessage());
        }
    }

    private void userLoggedIn(GoogleSignInAccount account) {


        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        header.findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        header.findViewById(R.id.pleaseAuth).setVisibility(View.INVISIBLE);

        TextView lblMail = header.findViewById(R.id.lblMail);
        lblMail.setText(account.getEmail());
        lblMail.setVisibility(View.VISIBLE);

        TextView lblNom = header.findViewById(R.id.lblNom);
        lblNom.setText(account.getFamilyName());
        lblNom.setVisibility(View.VISIBLE);

        TextView lblPrenom = header.findViewById(R.id.lblPrenom);
        lblPrenom.setText(account.getGivenName());
        lblPrenom.setVisibility(View.VISIBLE);

        final ImageView pic = header.findViewById(R.id.imageAccount);
        pic.setImageURI(account.getPhotoUrl());
        pic.setVisibility(View.VISIBLE);


        Glide.with(getApplicationContext()).load(account.getPhotoUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(pic) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                pic.setImageDrawable(circularBitmapDrawable);
            }
        });

        mainUser = new User(
                account.getDisplayName(),
                account.getEmail(),
                account.getId(),
                account.getIdToken(),
                account.getAccount(),
                account.getFamilyName(),
                account.getGivenName(),
                account.getPhotoUrl()
        );

        initRecycleView();
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
        TextView mRevenusFooter = findViewById(R.id.revenusFooter);
        //  mRevenusFooter.setText(mainUser.getRevenus());
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

        if (id == R.id.nav_rejoindre) {
            Intent myIntent = new Intent(AccueilActivity.this, JoinPariActivity.class);
            AccueilActivity.this.startActivity(myIntent);
        }
        if (id == R.id.nav_portefeuille) {
            Intent myIntent = new Intent(AccueilActivity.this, AddFoundsActivity.class);
            AccueilActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
