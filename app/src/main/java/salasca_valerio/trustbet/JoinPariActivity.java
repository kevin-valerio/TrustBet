
package salasca_valerio.trustbet;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class JoinPariActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_bet);
        joinBetButton();

    }

    private void joinBetButton() {
        final Button joinBet = findViewById(R.id.joinbet);

        joinBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText inputId = findViewById(R.id.input_bet_id);
                String ID = inputId.getText().toString();
                PariDbHelper newPariDb = new PariDbHelper(getBaseContext());
                newPariDb.joinPari(Long.parseLong(ID));


                AlertDialog alertDialog = new AlertDialog.Builder(JoinPariActivity.this).create();
                alertDialog.setTitle("Pari rejoins");
                alertDialog.setMessage("Vous avez rejoins le pari ! Vous pouvez revenir à l'accueil");
                alertDialog.show();

            }
        });
    }
}