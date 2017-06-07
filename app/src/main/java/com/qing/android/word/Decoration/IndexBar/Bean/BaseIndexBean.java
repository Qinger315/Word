package com.qing.android.word.Decoration.IndexBar.Bean;

/**
 * Created by qing on 2017/6/7.
 */

public abstract class BaseIndexBean implements ISuspensionInterface {

    private String mBaseIndexTag;

    public String getBaseIndexTag() {
        return mBaseIndexTag;
    }

    public BaseIndexBean setBaseIndexTag(String baseIndexTag) {
        mBaseIndexTag = baseIndexTag;
        return this;
    }

    @Override
    public String getSuspensionTag() {
        return mBaseIndexTag;
    }

    @Override
    public boolean isShowSuspension() {
        return true;
    }

}
