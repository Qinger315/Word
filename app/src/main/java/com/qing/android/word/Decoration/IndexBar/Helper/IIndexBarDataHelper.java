package com.qing.android.word.Decoration.IndexBar.Helper;

import com.qing.android.word.Decoration.IndexBar.Bean.BaseIndexPinyinBean;


import java.util.List;



public interface IIndexBarDataHelper {
        //汉语-》拼音
        IIndexBarDataHelper convert(List<?extends BaseIndexPinyinBean> data);

        //拼音->tag
        IIndexBarDataHelper fillInexTag(List<?extends BaseIndexPinyinBean> data);

        //对源数据进行排序（RecyclerView）
        IIndexBarDataHelper sortSourceDatas(List<? extends BaseIndexPinyinBean> datas);

        //对IndexBar的数据源进行排序(右侧栏),在 sortSourceDatas 方法后调用
        IIndexBarDataHelper getSortedIndexDatas(List<? extends BaseIndexPinyinBean> sourceDatas, List<String> datas);
    }
