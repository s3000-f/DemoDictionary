package ir.s3000.demodictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * Created by S3000 on 10/29/2015.
 */
public class WordDatabase extends SQLiteOpenHelper {
    public static final String TABLE_DICT = "dictionary";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DEFF = "deff";
    Context context;
    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table "
            + TABLE_DICT + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_WORD
            + " text not null,"+ COLUMN_DEFF
            + " text not null "+ ");";


    public WordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database = context.openOrCreateDatabase(DATABASE_NAME, );
        } catch (FileNotFoundException e) {
            try {
                database =
                        context.createDatabase(DATABASE_NAME, DATABASE_VERSION, 0,
                                null);
                database.execSQL(DATABASE_CREATE);
            } catch (FileNotFoundException e1) {
                database = null;
            }
        }
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(WordDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICT);

        onCreate(db);
    }
}
