package salasca_valerio.trustbet;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

public class User {

    private String username;
    private String email;
    private String id;
    private String idToken;
    private Account account;
    private String familyName;
    private String givenName;
    private Uri photoUri;
    private String revenus;
    private Context context;

    User(Context context, String username, String email, String id, String idToken, Account account, String familyName, String givenName, Uri photoUri) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.idToken = idToken;
        this.account = account;
        this.familyName = familyName;
        this.givenName = givenName;
        this.photoUri = photoUri;
        this.revenus = getRevenus();
        this.context=context;

    }

    public String getRevenus() {

        String montant = "3.3";
        this.revenus = montant;
        return montant.toString() + " €";
    }

    public ArrayList<Pari> getParis(){
        PariDbHelper pariDbHelper = new PariDbHelper(context);
        return  pariDbHelper.getPariByMail(this.email);

    }

    public String getEmail() {
        return email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
