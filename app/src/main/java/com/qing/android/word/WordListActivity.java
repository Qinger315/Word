package com.qing.android.word;

import android.support.v4.app.Fragment;

public class WordListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new WordListFragment();
    }

}
