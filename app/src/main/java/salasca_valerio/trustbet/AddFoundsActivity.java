package salasca_valerio.trustbet;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by s16008030 on 06/04/18.
 */

public class AddFoundsActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfounds);
        addFoundButton();

    }

    private void addFoundButton() {
        final Button add20 = (Button) findViewById(R.id.add20);
        add20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(add20.getText().toString());



//
//                        PariDbHelper newPariDb = new PariDbHelper(getBaseContext());
//                        newPariDb.joinPari(Long.parseLong(ID));
//
                AlertDialog alertDialog = new AlertDialog.Builder(AddFoundsActivity.this).create();
                alertDialog.setTitle("Fond ajouté");
                alertDialog.setMessage("Vous avez ajouté :" + amount);
                alertDialog.show();

            }
        });
    }


}
