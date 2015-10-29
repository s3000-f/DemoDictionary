package ir.s3000.demodictionary;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by S3000 on 10/30/2015.
 */
public class DictDataSource {


        // Database fields
        private SQLiteDatabase database;
        private WordDatabase dbHelper;
        private String[] allColumns = { WordDatabase.COLUMN_ID,
                WordDatabase.COLUMN_WORD,WordDatabase.COLUMN_DEFF };

        public DictDataSource(Context context) {
            dbHelper = new WordDatabase(context);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public Word createComment(String word) {
            ContentValues values = new ContentValues();
            ContentValues values2 = new ContentValues();

            values.put(WordDatabase.COLUMN_WORD, word);
            values2.put(WordDatabase.COLUMN_DEFF,word);
            long insertId = database.insert(WordDatabase.TABLE_DICT, null,
                    values);
            Cursor cursor = database.query(WordDatabase.TABLE_DICT,
                    allColumns, WordDatabase.COLUMN_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            Word newWord = cursorToWord(cursor);
            cursor.close();
            return newWord;
        }

        public void deleteComment(Word word) {
            long id = word.getId();
            System.out.println("Comment deleted with id: " + id);
            database.delete(WordDatabase.TABLE_DICT, WordDatabase.COLUMN_ID
                    + " = " + id, null);
        }

        public List<Word> getAllComments() {
            List<Word> words = new ArrayList<Word>();

            Cursor cursor = database.query(WordDatabase.TABLE_DICT,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Word word = cursorToWord(cursor);
                words.add(word);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return words;
        }

        private Word cursorToWord(Cursor cursor) {
            Word word = new Word();
            word.setId(cursor.getLong(0));
            word.setWord(cursor.getString(1));
            word.setDeff(cursor.getString(2));
            return word;
        }
    }
}
