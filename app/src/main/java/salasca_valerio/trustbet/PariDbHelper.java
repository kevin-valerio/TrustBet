package salasca_valerio.trustbet;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static salasca_valerio.trustbet.DatabasePari.DATE;
import static salasca_valerio.trustbet.DatabasePari.DESCRIPTION;
import static salasca_valerio.trustbet.DatabasePari.MONTANT;
import static salasca_valerio.trustbet.DatabasePari.SQL_CREATE_ENTRIES;
import static salasca_valerio.trustbet.DatabasePari.SQL_DELETE_ENTRIES;
import static salasca_valerio.trustbet.DatabasePari.TABLE_NAME_PARI;

public class PariDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TrustBet.db";

    public void insertPari(Pari pari) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabasePari.MAIL_USER_PRIMAIRE, pari.getMailOwner());
        values.put(DatabasePari.BET_ID, pari.getId());
        values.put(DatabasePari.TITRE, pari.getTitre());
        values.put(DatabasePari.MAIL_USER_SECONDAIRE, "NULL");
        values.put(DatabasePari.DESCRIPTION, pari.getDescription());
        values.put(DatabasePari.DATE, pari.getDate());
        values.put(DatabasePari.MONTANT, pari.getMontant());

        long newRowId = db.insert(DatabasePari.TABLE_NAME_PARI, null, values);
    }

    public ArrayList<Pari> getPariByMail(String mail) {
        ArrayList<Pari> parisTemp = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DatabasePari.TITRE + ", " +
                DESCRIPTION + "," + MONTANT + "," +  DATE + " FROM " + TABLE_NAME_PARI, null);
        if (c.moveToFirst()) {
            do {
                String titre = c.getString(0);
                String description = c.getString(1);
                String montant = c.getString(2);
                String dateEcheance = c.getString(3);
               // parisTemp.add(new Pari(titre, description, montant, dateEcheance, mail));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return parisTemp;

    }

    public void joinPari(Long ID) {

        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE pari SET mail_userSecondaire = \"" + AccueilActivity.mainUser.getEmail()
                + "\" WHERE bet_id = " + ID;
        db.execSQL(strSQL);
    }


    public PariDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
