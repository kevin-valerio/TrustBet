package salasca_valerio.trustbet;

import java.util.Date;


public class Pari {

    private static final String TAG = "PARI.JAVA";
    private String titre;
    private String montant;
    private String description;
    private long id;
    private String date;
    private String mailOwner;

    public long getId() {
        return id;
    }

    Pari(String titre, String description, String montant, String dateEcheance, String myMail) {
        this.date = dateEcheance;
        this.description = description;
        this.montant = montant;
        this.titre = titre;
        this.mailOwner = myMail;
        this.id =  System.currentTimeMillis() / 1000L;

    }

    public String getMailOwner() {
        return mailOwner;
    }

    public void setMailOwner(String mailOwner) {
        this.mailOwner = mailOwner;
    }

    public String getTitre() {
        return titre;
    }

    public String getMontant() {
        return montant;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }



}
