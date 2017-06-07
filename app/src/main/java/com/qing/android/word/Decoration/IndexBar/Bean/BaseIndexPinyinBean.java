package com.qing.android.word.Decoration.IndexBar.Bean;

/**
 * Created by qing on 2017/6/7.
 */

public abstract class BaseIndexPinyinBean extends BaseIndexBean {

    private String mbaseIndexPinyin;

    public String getBaseIndexPinyin() {
        return mbaseIndexPinyin;
    }

    public BaseIndexPinyinBean setBaseIndexPinyin(String baseIndexPinyin) {
        mbaseIndexPinyin = baseIndexPinyin;
        return this;
    }

    public boolean isNeedToPinyin() {
        return true;
    }

    public abstract String getTarget();




}
