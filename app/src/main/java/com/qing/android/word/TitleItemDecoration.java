package com.qing.android.word;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.List;


/**
 * Created by qing on 2017/6/6.
 */

public class TitleItemDecoration extends  RecyclerView.ItemDecoration{

        private List<Word> mWords;
        private Paint mPaint;
        private Rect mBounds;

        private int mTitleHeight;
        private static int COLOR_TITLE_BG = Color.parseColor("#EE4000");
        private static int COLOR_TITLE_FONT = Color.parseColor("#FF000000");
        private static int mTitleFontSize;

        public TitleItemDecoration(Context context, List<Word> datas) {
            super();
            mWords = datas;
            mPaint = new Paint();
            mBounds = new Rect();
            mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    50, context.getResources().getDisplayMetrics());
            mTitleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    30, context.getResources().getDisplayMetrics());
            mPaint.setTextSize(mTitleFontSize);
            mPaint.setAntiAlias(true);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                int position = params.getViewLayoutPosition();
                if (position > -1) {
                    if (position == 0) {
                        drawTitleArea(c, left, right, child, params, position);

                    } else {
                        if (null != mWords.get(position).getTag()&&
                                !mWords.get(position).getTag().equals(mWords.get(position - 1).getTag())) {
                            drawTitleArea(c, left, right, child, params, position);
                        } else {

                        }
                    }
                }
            }
        }

        /**
         * 绘制Title区域背景和文字的方法
         *
         * @param c
         * @param left
         * @param right
         * @param child
         * @param params
         * @param position
         */
        private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层
            mPaint.setColor(COLOR_TITLE_BG);
            c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight,
                    right, child.getTop() - params.topMargin, mPaint);
            mPaint.setColor(COLOR_TITLE_FONT);
            mPaint.getTextBounds(mWords.get(position).getSpell(), 0,
                    mWords.get(position).getTag().length(), mBounds);
            c.drawText(mWords.get(position).
                    getTag(), child.getPaddingLeft(),
                    child.getTop() - params.topMargin - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {//最后调用 绘制在最上层
            int pos = ((LinearLayoutManager)(parent.getLayoutManager())).findFirstVisibleItemPosition();

            String tag = mWords.get(pos).getTag();
            View child = parent.findViewHolderForLayoutPosition(pos).itemView;
            mPaint.setColor(COLOR_TITLE_BG);
            c.drawRect(parent.getPaddingLeft(),
                    parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
            mPaint.setColor(COLOR_TITLE_FONT);
            mPaint.getTextBounds(tag, 0, tag.length(), mBounds);
            c.drawText(tag, child.getPaddingLeft(),
                    parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBounds.height() / 2),
                    mPaint);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            if (position > -1) {
                if (position == 0) {
                    outRect.set(0, mTitleHeight, 0, 0);
                } else {//其他的通过判断
                    if (null != mWords.get(position).getTag() &&
                            !mWords.get(position).getTag().equals(mWords.get(position - 1).getTag())) {
                        outRect.set(0, mTitleHeight, 0, 0);
                    } else {
                        outRect.set(0, 0, 0, 0);
                    }
                }
            }
        }

    }

