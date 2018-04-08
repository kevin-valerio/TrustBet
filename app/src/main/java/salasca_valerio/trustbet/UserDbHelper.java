package salasca_valerio.trustbet;

import android.app.AlertDialog;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static salasca_valerio.trustbet.DatabaseUser.SQL_CREATE_ENTRIES;
import static salasca_valerio.trustbet.DatabaseUser.MAIL_USER;
import static salasca_valerio.trustbet.DatabaseUser.SQL_DELETE_ENTRIES;
import static salasca_valerio.trustbet.DatabaseUser.TABLE_NAME_USER;


public class UserDbHelper extends SQLiteOpenHelper{
    /* TODO 1  qd un utilisateur ce connecte on check et on l'insère dans la base s'il faut
        TODO 1  checkaddFunds()

       TODO 2. mes revenus : on va chercher les fonds dans la bd et pas un string en dur
    */



    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "TrustBet.db";
    public static final String TAG = "UserDbHelper";


    public void addFunds(int amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseUser.FUNDS, amount);


        db.update(DatabaseUser.TABLE_NAME_USER, values," WHERE "+MAIL_USER +" = " + AccueilActivity.mainUser.getEmail(),null);
    }

    public boolean isUserInDB(String mail){

        //check si déja dans la bd
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user WHERE mail_user = '"+mail+"'", null);
        if(!c.moveToFirst()) { //movetofirst retourne faux si le curseur est vide

            return false;
        }
        else {

            return  true;
        }



    }

    public void insertUser(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseUser.MAIL_USER, mail);
        values.put(DatabaseUser.FUNDS, 20);


        long newRowId = db.insert(DatabaseUser.TABLE_NAME_USER, null, values);
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
