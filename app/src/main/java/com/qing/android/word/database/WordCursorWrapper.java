package com.qing.android.word.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.qing.android.word.Word;
import com.qing.android.word.database.WordDbSchema.WordTable;

import java.util.UUID;


/**
 * Created by qing on 2017/5/22.
 */

public class WordCursorWrapper extends CursorWrapper {
    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord() {
        String uuidString = getString(getColumnIndex(WordTable.Cols.UUID));
        String spell = getString(getColumnIndex(WordTable.Cols.SPELL));
        String mean = getString(getColumnIndex(WordTable.Cols.MEAN));

        Word word = new Word(UUID.fromString(uuidString));
        word.setSpell(spell);
        word.setMean(mean);

        return  word;
    }
}
