package salasca_valerio.trustbet;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CreatePariActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

   private ShareActionProvider mShareActionProvider;
    private long pariId = 0;
   public static String dateEcheance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pari);
        createBetButtonAction();
        createGoBackButton();
        createShareButton();
    }

    private void createShareButton() {
        final FloatingActionButton share = findViewById(R.id.shareBtn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pariId  == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(CreatePariActivity.this).create();
                    alertDialog.setTitle("Alerte ! Pari non partagé");
                    alertDialog.setMessage("Veuillez dans un premier temps créer un pari");

                }
                else{
                    Intent myIntent = new Intent(Intent.ACTION_SEND);
                    myIntent.setType("text/plain");
                    myIntent.putExtra(Intent.EXTRA_SUBJECT, "Numéro de pari : ");
                    myIntent.putExtra(Intent.EXTRA_TEXT, "Numéro de pari : " + pariId);
                    startActivity(Intent.createChooser(myIntent, "Partagez votre numéro de pari !"));

                }

            }
        });
    }



    private void createGoBackButton() {
        Button goBack = findViewById(R.id.goAccueil);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CreatePariActivity.this, AccueilActivity.class);
                CreatePariActivity.this.startActivity(myIntent);
            }
        });
    }

    public void datePicker(View view) {

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.showDate)).setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    @SuppressWarnings("deprecation")
     public static class DatePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
             CreatePariActivity.dateEcheance = day + "/" + month + "/" + year;
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }

    }


    private void createBetButtonAction() {

        Button createBetButton = findViewById(R.id.button_create_bet);
        createBetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText inputTitre = findViewById(R.id.input_bet_title);
                EditText inputAmount = findViewById(R.id.input_bet_amount);
                EditText inputDescription = findViewById(R.id.input_bet_description);

                String montant = inputAmount.getText().toString();
                String titre = inputTitre.getText().toString();
                String description = inputDescription.getText().toString();
                String myMail = AccueilActivity.mainUser.getEmail();

                Pari pariCree = new Pari(titre, description, montant, dateEcheance, myMail);
                PariDbHelper pariDbHelper = new PariDbHelper(getBaseContext());
                pariDbHelper.insertPari(pariCree);

                pariId = pariCree.getId();

                 AlertDialog alertDialog = new AlertDialog.Builder(CreatePariActivity.this).create();
                alertDialog.setTitle("Pari crée");
                alertDialog.setMessage("Votre pari d'ID : " + pariCree.getId() + " est crée ! Pensez à garder ce numéro, et donnez le à votre ami");

                alertDialog.show();

            }
        });
    }

}
