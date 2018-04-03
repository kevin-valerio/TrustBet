package salasca_valerio.trustbet;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasePari {


    public static final String BET_ID = "bet_id";
    public static final String MAIL_USER_PRIMAIRE = "mail_userPrimaire";
    public static final String MAIL_USER_SECONDAIRE = "mail_userSecondaire";
    public static final String DESCRIPTION = "description";
    public static final String MONTANT = "montant";
    public static final String DATE = "date";
    public static final String TABLE_NAME_PARI = "pari";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE `" + TABLE_NAME_PARI + "` (\n" +
                    "  `" + BET_ID + "` LONG NOT NULL,\n" +
                    "  `" + MAIL_USER_PRIMAIRE + "` varchar(255) NOT NULL,\n" +
                    "  `" + MAIL_USER_SECONDAIRE + "` varchar(255) NOT NULL,\n" +
                    "  `" + DESCRIPTION + "` varchar(255) NOT NULL,\n" +
                    "  `" + MONTANT + "` int(11) NOT NULL,\n" +
                    "  `" + DATE + "` text NOT NULL\n" +
                    ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS '" + TABLE_NAME_PARI + "'";



}