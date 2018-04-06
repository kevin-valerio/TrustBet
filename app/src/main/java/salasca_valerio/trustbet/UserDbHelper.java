package salasca_valerio.trustbet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static salasca_valerio.trustbet.DatabaseUser.MAIL_USER;
import static salasca_valerio.trustbet.DatabaseUser.SQL_CREATE_ENTRIES;
import static salasca_valerio.trustbet.DatabaseUser.SQL_DELETE_ENTRIES;



public class UserDbHelper extends SQLiteOpenHelper{
    // qd un utilisateur ce connecte on l'insère dans la base

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TrustBet.db";

    public void addFunds(int amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseUser.FUNDS, amount);


        db.update(DatabaseUser.TABLE_NAME_USER, values," WHERE "+MAIL_USER +" = " + AccueilActivity.mainUser.getEmail(),null);
    }




    public UserDbHelper(Context context) {
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
