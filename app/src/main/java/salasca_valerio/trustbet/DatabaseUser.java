package salasca_valerio.trustbet;

/**
 * Created by s16008030 on 06/04/18.
 */

public class DatabaseUser {

    public static final String MAIL_USER = "mail_user";
    public static final String FUNDS = "funds";
    public static final String TABLE_NAME_USER = "user";
//    public static final String SQL_CREATE_ENTRIES =
//            "CREATE TABLE `" + TABLE_NAME_USER + "` (\n" +
//                    "  `" + MAIL_USER+ "` varchar(255) NOT NULL,\n" +
//                    "  `" + FUNDS + "` int(11) NOT NULL,\n" +
//                    ");";

    public static final String SQL_CREATE_ENTRIES ="CREATE TABLE " + TABLE_NAME_USER + "(" + MAIL_USER +
            " varchar(255) NOT NULL " + FUNDS + " int(11) NOT NULL " + ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS '" + TABLE_NAME_USER + "'";
}
