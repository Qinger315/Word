package com.qing.android.word;

import com.qing.android.word.Decoration.IndexBar.Bean.BaseIndexPinyinBean;

import java.util.UUID;

/**
 * Created by qing on 2017/5/22.
 */

public class Word extends BaseIndexPinyinBean {

    private UUID mId;
    private String mSpell;
    private String mMean;
    private boolean isTop;

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

    public String getTag() {
        return  mSpell.substring(0,1).toUpperCase();
    }

    @Override
    public String getTarget() { return mSpell;}

    @Override
    public  boolean isNeedToPinyin() { return !isTop; }

    @Override
    public  boolean isShowSuspension() { return !isTop; }




}
