package salasca_valerio.trustbet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static salasca_valerio.trustbet.DatabasePari.SQL_CREATE_ENTRIES;
import static salasca_valerio.trustbet.DatabasePari.SQL_DELETE_ENTRIES;

public class PariDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TrustBet.db";

    public void insertPari(Pari pari){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabasePari.MAIL_USER_PRIMAIRE, pari.getMailOwner());
        values.put(DatabasePari.BET_ID, pari.getId());
        values.put(DatabasePari.MAIL_USER_SECONDAIRE, "NULL");
        values.put(DatabasePari.DESCRIPTION, pari.getDescription());
        values.put(DatabasePari.DATE, pari.getDate().toString());
        values.put(DatabasePari.MONTANT, pari.getMontant());

        long newRowId = db.insert(DatabasePari.TABLE_NAME_PARI, null, values);
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
