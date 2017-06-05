package com.qing.android.word;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qing.android.word.database.DbManager;
import com.qing.android.word.database.WordBaseHelper;
import com.qing.android.word.database.WordCursorWrapper;
import com.qing.android.word.database.WordDbSchema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.qing.android.word.database.WordDbSchema.*;

/**
 * Created by qing on 2017/5/22.
 */

public class WordLab {

    private static WordLab sWordLab;



    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static WordLab get(Context context) {
        if(sWordLab == null) {
            sWordLab = new WordLab(context);
        }
        return sWordLab;
    }

    private WordLab(Context context) {
        mContext = context.getApplicationContext();

        mDatabase = new WordBaseHelper(mContext)
                .getWritableDatabase();

     }


    public void addWord(Word w) {
        ContentValues values = getContentValues(w);

        mDatabase.insert(WordTable.NAME, null, values);
    }

    public List<Word> getWords() {

        List<Word> words = new ArrayList<>();

        WordCursorWrapper cursor = queryWords(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                words.add(cursor.getWord());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return words;
     }

    public List<Word> getWords(String s) {

        List<Word> words = new ArrayList<>();

        WordCursorWrapper cursor = queryWords(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CalDistance dis = new CalDistance(cursor.getWord().getSpell(), s);
                if (dis.getSimilarity() > 0.7) {
                    words.add(cursor.getWord());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return words;
    }

    public Word getWord(UUID id) {

      WordCursorWrapper cursor = queryWords(
              WordTable.Cols.UUID + " = ?",
              new String[] {id.toString()}
      );

     try {
         if (cursor.getCount() == 0 ) {
             return null;
         }

         cursor.moveToFirst();
         return cursor.getWord();
     } finally {
         cursor.close();
     }
    }

    public void updateWord(Word word) {
        String uuidString = word.getId().toString();
        ContentValues values = getContentValues(word);

        mDatabase.update(WordTable.NAME, values,
                WordTable.Cols.UUID + " = ?",
                new String[] { uuidString}) ;
    }

    private static ContentValues getContentValues(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordTable.Cols.UUID, word.getId().toString());
        values.put(WordTable.Cols.SPELL,word.getSpell());
        values.put(WordTable.Cols.MEAN, word.getMean());

        return values;
    }

    private WordCursorWrapper queryWords(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WordTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new WordCursorWrapper(cursor);
    }
}
