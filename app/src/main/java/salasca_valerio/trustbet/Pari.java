package salasca_valerio.trustbet;

import java.util.Date;


public class Pari {

        private String titre;
        private String montant;
        private String description;
        private Date date;
        private String mailOwner;

    Pari(String titre, String description, String montant, Date dateEcheance, String myMail) {
        this.date = dateEcheance;
        this.description = description;
        this.montant = montant;
        this.titre = titre;
        this.mailOwner = myMail;
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

    public Date getDate() {
        return date;
    }


    public void uploadToDatabase() {

    }
}
