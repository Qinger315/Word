package com.qing.android.word;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.qing.android.word.database.WordDbSchema;

import java.util.List;

/**
 * Created by qing on 2017/5/22.
 */

public class WordListFragment extends Fragment {

    private static final String TAG = "WordListFragment";

    private RecyclerView mWordRecyclerView;
    private WordAdapter mAdapter;
    TitleItemDecoration mtitleItemDecoration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list,container,false);

        mWordRecyclerView = (RecyclerView) view.findViewById(R.id.word_recycler_view);
        mWordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWordRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));

        updateUI();

        return view;

    }

    private class  WordHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener  {

        private  TextView mSpellTextView;
        private  TextView mMeanTextView;

        private Word mWord;


        public WordHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mSpellTextView = (TextView) itemView.findViewById(R.id.list_item_word_spell_text_view);
            mMeanTextView = (TextView) itemView.findViewById(R.id.list_item_word_mean_text_view);
        }


        public void bindWord(Word word) {

            mWord = word;
            mSpellTextView.setText(mWord.getSpell());
            mMeanTextView.setText(mWord.getMean());

        }

        @Override
        public void onClick(View v) {
            Intent intent = WordPagerActivity.newIntent(getActivity(), mWord.getId());
            startActivity(intent);
        }
    }

    private class  WordAdapter extends RecyclerView.Adapter<WordHolder> {

        private List<Word> mWords;

        public WordAdapter(List<Word> words) {
            mWords = words;
        }

        public WordHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_word, parent, false);
            return new WordHolder(view);
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {
            Word word = mWords.get(position);
            holder.bindWord(word);

        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }

        public void setWords(List<Word> words) {
            mWords = words;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_word_search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mWordRecyclerView.removeItemDecoration(mtitleItemDecoration);
                updateSearchUI(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {


                return false;
            }
        });


    }

    private void updateSearchUI(String query) {

        WordLab wordLab = WordLab.get(getActivity());
        List<Word> words = wordLab.getWords(query);

        if (words.size() >0 ) {
             updateAdapter(words);
        }


    }
    private void updateUI() {

        WordLab wordLab = WordLab.get(getActivity());
        List<Word> words = wordLab.getWords();
        updateAdapter(words);
    }

    private void updateAdapter(final List<Word> words) {

        if(mAdapter == null) {
            mAdapter = new WordAdapter(words);
            mtitleItemDecoration = new TitleItemDecoration(getActivity(),words);
            mWordRecyclerView.addItemDecoration(mtitleItemDecoration);
            mWordRecyclerView.setAdapter(mAdapter);

        } else {
            mAdapter.setWords(words);
            mAdapter.notifyDataSetChanged();
        }



    }


}
