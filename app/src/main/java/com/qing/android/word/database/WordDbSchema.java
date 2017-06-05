package com.qing.android.word.database;

/**
 * Created by qing on 2017/5/22.
 */

public class WordDbSchema {
    public static final class WordTable {
        public static final String NAME = "words";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SPELL = "spell";
            public static final String MEAN = "mean";
        }
    }

}
