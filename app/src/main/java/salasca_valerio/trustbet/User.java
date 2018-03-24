package salasca_valerio.trustbet;

import android.accounts.Account;
import android.net.Uri;

public class User {

    private String username;
    private String email;
    private String id;
    private String idToken;
    private Account account;
    private String familyName;
    private String givenName;
    private Uri photoUri;
    private Integer revenus;

    public User(String username, String email, String  id, String idToken, Account account, String familyName, String givenName, Uri photoUri) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.idToken = idToken;
        this.account = account;
        this.familyName = familyName;
        this.givenName = givenName;
        this.photoUri = photoUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }
}
