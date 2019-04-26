package com.sexymonkey.slidebardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private List<ItemBean> datas;
    private StickyListHeadersListView mList;
    private StickHeaderAdapter mStickHeaderAdapter;
    private TextView mSectionText;
    private SlideBar mSlideBar;
    private int mIndex;
    private String mSectionString;
    private TextView mheader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        datas = new ArrayList<>();
        datas.add(new ItemBean("aaa",true));
        datas.add(new ItemBean("bbc",true));
        datas.add(new ItemBean("ad",true));
        datas.add(new ItemBean("cac",true));
        datas.add(new ItemBean("aaa",true));
        datas.add(new ItemBean("daa",true));
        datas.add(new ItemBean("daa",true));
        datas.add(new ItemBean("caa",true));
        datas.add(new ItemBean("baa",true));
        datas.add(new ItemBean("caa",true));
        datas.add(new ItemBean("aaa",true));
        datas.add(new ItemBean("bbc",true));
        datas.add(new ItemBean("ad",true));
        datas.add(new ItemBean("cac",true));
        datas.add(new ItemBean("aaa",true));
        datas.add(new ItemBean("daa",true));
        datas.add(new ItemBean("daa",true));
        datas.add(new ItemBean("caa",true));
        datas.add(new ItemBean("baa",true));
        datas.add(new ItemBean("caa",true));
        datas.add(new ItemBean("esfsf",true));
        datas.add(new ItemBean("bbc",true));
        datas.add(new ItemBean("ad",true));
        datas.add(new ItemBean("fffdg",true));
        datas.add(new ItemBean("gtytu",true));
        datas.add(new ItemBean("gtytu",true));
        datas.add(new ItemBean("gtytu",true));
        datas.add(new ItemBean("yhjhj",true));
        datas.add(new ItemBean("yhjhj",true));
        datas.add(new ItemBean("yhjhj",true));
        datas.add(new ItemBean("jhgh",true));
        datas.add(new ItemBean("jhgh",true));
        datas.add(new ItemBean("jhgh",true));
        datas.add(new ItemBean("khghg",true));
        datas.add(new ItemBean("khghg",true));
        datas.add(new ItemBean("lhgh",true));
        datas.add(new ItemBean("lhgh",true));
        datas.add(new ItemBean("zhghg",true));
        datas.add(new ItemBean("zhghg",true));
        datas.add(new ItemBean("zhghg",true));
        datas.add(new ItemBean("zhghg",true));
        Collections.sort(datas, new Comparator<ItemBean>() {
            @Override
            public int compare(ItemBean o1, ItemBean o2) {
                return o1.name.charAt(0) - o2.name.charAt(0);
            }
        });

        for (int i = 0; i < datas.size(); i++) {
            int j = i -1;
            if(datas.get(0).name.charAt(0) == datas.get(1).name.charAt(0)){
                datas.get(1).isShowFirstLetter = false;
            }
            if( j > 0 ){
                if(datas.get(i).name.charAt(0) == datas.get(j).name.charAt(0)){
                    datas.get(i).isShowFirstLetter = false;
                }
            }
        }
    }

    private void initView() {

        mList =(StickyListHeadersListView) findViewById(R.id.list);
        mSectionText = (TextView) findViewById(R.id.section);
        mSlideBar = (SlideBar) findViewById(R.id.slide_bar);

        initRecyclerView();
    }

    private int lastPostion = -1;
    private int currentPostion = -1;
    private int mDx;
    private int mDy;

    private void initRecyclerView() {
            mStickHeaderAdapter = new StickHeaderAdapter(this, datas);
            mList.setAdapter(mStickHeaderAdapter);

            mSlideBar.setSliderListenter(new SlideBar.SliderListenter() {
                @Override
                public void onSliding(int index, String section) {
                    mIndex = index;
                    mSectionString = section;
                    processHeader(mSectionString);
                    for (int i = 0; i < datas.size(); i++) {
                        if(String.valueOf(datas.get(i).name.toUpperCase().charAt(0)).equals(section) && datas.get(i).isShowFirstLetter){
                              mList.smoothScrollToPosition(i);
                        }
                    }
                }
                @Override
                public void onFinishSliding() {
                    mSectionText.setVisibility(View.GONE);
                }
            });
    }

    private void processHeader(String sectionString) {
        mSectionText.setVisibility(View.VISIBLE);
        mSectionText.setText(sectionString);


    }
}
