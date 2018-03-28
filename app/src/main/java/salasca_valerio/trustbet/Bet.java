package salasca_valerio.trustbet;

import java.util.Date;

/**
 * Created by s16008030 on 28/03/18.
 */

public class Bet {



        private String titre;
        private float montant;
        private String description;
        private Date date;


    public String getTitre() {
        return titre;
    }

    public float getMontant() {
        return montant;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    Bet(String titre, float montant, String description, Date date) {
            this.titre = titre;
            this.montant = montant;
            this.description = description;
            this.date = date;







    }

}
