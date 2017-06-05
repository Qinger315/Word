package com.qing.android.word;

import java.util.UUID;

/**
 * Created by qing on 2017/5/22.
 */

public class Word {

    private UUID mId;
    private String mSpell;
    private String mMean;

    public Word() {
        this(UUID.randomUUID());
    }

    public Word(UUID id){
        mId = id;
    }


    public UUID getId() {
        return mId;
    }

    public String getSpell() {
        return mSpell;
    }

    public void setSpell(String spell) {
        mSpell = spell;
    }

    public String getMean() {
        return mMean;
    }

    public void setMean(String mean) {
        mMean = mean;
    }
}
