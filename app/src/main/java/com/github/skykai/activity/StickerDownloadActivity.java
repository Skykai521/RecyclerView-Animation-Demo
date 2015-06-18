package com.github.skykai.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSON;
import com.github.skykai.view.StickerRecyclerView;
import com.github.skykai.R;
import com.github.skykai.model.StickerItem;
import com.github.skykai.model.StickerType;
import com.github.skykai.model.Sticker;
import com.github.skykai.util.Utils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 贴图库
 * Created by sky on 2015/6/10.
 */
public class StickerDownloadActivity extends AppCompatActivity {

    @InjectView(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;
    private ArrayList<StickerRecyclerView> stickerViews = new ArrayList<StickerRecyclerView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_download);

        ButterKnife.inject(this);
        new StickerLoader().execute();
    }

    public class StickerPagerAdapter extends PagerAdapter {

        private Context context;
        private ArrayList<StickerType> mTypeList;
        private ArrayList<StickerRecyclerView> mStickerList;

        public StickerPagerAdapter(Context mContext,ArrayList<StickerType> typeList,ArrayList<StickerRecyclerView> stickerList){
            this.context = mContext;
            this.mTypeList = typeList;
            this.mStickerList = stickerList;
        }
        @Override
        public int getCount() {
            return mTypeList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mStickerList.get(position), 0);
            return mStickerList.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTypeList.get(position).getType();
        }
    }


    private class StickerLoader extends AsyncTask<Void,Void,ArrayList<StickerRecyclerView>>{

        private ArrayList<StickerType> mTypeList = new ArrayList<StickerType>();
        private ArrayList<StickerItem> mStickerList= new ArrayList<StickerItem>();

        @Override
        protected ArrayList<StickerRecyclerView> doInBackground(Void... params) {
            String stickerStr = Utils.getInst().readFromAsset("sticker.json");
            Sticker localSticker = JSON.parseObject(stickerStr, Sticker.class);
            mTypeList = (ArrayList<StickerType>) localSticker.getTypelist();
            mStickerList = (ArrayList<StickerItem>) localSticker.getCommonlist();

            //添加全部
            stickerViews.add(new StickerRecyclerView(StickerDownloadActivity.this, mStickerList));
            //从第一个开始
            if(mTypeList.size()>1){
                for(int i = 1;i<mTypeList.size();i++) {
                    ArrayList<StickerItem> tempList = new ArrayList<StickerItem>();
                    for(StickerItem c :mStickerList){
                        if(c.getTypeid().equals(mTypeList.get(i).getId())){
                            tempList.add(c);
                        }
                    }
                    stickerViews.add(new StickerRecyclerView(StickerDownloadActivity.this, tempList));
                    tempList.clear();
                }
            }
            return stickerViews;
        }
        @Override
        protected void onPostExecute(ArrayList<StickerRecyclerView> stickerRecyclerViews) {
            super.onPostExecute(stickerRecyclerViews);
            viewPager.setAdapter(new StickerPagerAdapter(StickerDownloadActivity.this, mTypeList, stickerViews));
            smartTabLayout.setViewPager(viewPager);
        }
    }
    
}
