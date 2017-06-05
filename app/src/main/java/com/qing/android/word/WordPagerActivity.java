package com.qing.android.word;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by qing on 2017/5/23.
 */

public class WordPagerActivity extends AppCompatActivity {

    private static final String EXTRA_WORD_ID = "com.qing.android.word.word_id";

    private ViewPager mViewPager;
    private List<Word> mWords;

    public static Intent newIntent(Context packageContext, UUID wordId) {
        Intent intent = new Intent(packageContext, WordPagerActivity.class);
        intent.putExtra(EXTRA_WORD_ID, wordId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pager);

        UUID wordId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_WORD_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_word_pager_view_pager);

        mWords = WordLab.get(this).getWords();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Word word = mWords.get(position);
                return WordFragment.newInstance(word.getId());
            }

            @Override
            public int getCount() {
                return mWords.size();
            }
        });

        for (int i = 0; i < mWords.size(); i++) {
            if (mWords.get(i).getId().equals(wordId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

}
