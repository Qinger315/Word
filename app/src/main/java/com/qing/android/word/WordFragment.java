package com.qing.android.word;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by qing on 2017/5/23.
 */

public class WordFragment extends Fragment {


    private static final String ARG_WORD_ID = "word_id";


    private Word mWord;
    private TextView mSpellTextView;
    private TextView mMeanTextView;

    public static WordFragment newInstance(UUID wordId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORD_ID, wordId);

        WordFragment fragment = new WordFragment();
        fragment.setArguments(args);
        return fragment;
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID wordId = (UUID) getArguments().getSerializable(ARG_WORD_ID);
        mWord = WordLab.get(getActivity()).getWord(wordId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        View v = inflater.inflate(R.layout.fragment_word, container, false);

        mSpellTextView = (TextView) v.findViewById(R.id.word_spell);
        mSpellTextView.setText(mWord.getSpell());
        mMeanTextView = (TextView) v.findViewById(R.id.word_mean);
        mMeanTextView.setText(mWord.getMean());

        return v;

    }

}
