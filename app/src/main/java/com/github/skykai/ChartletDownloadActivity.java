package com.github.skykai;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSON;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 贴图库
 * Created by sky on 2015/6/10.
 */
public class ChartletDownloadActivity extends Activity {

    @InjectView(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;
    private ArrayList<ChartletRecyclerView> chartletViews = new ArrayList<ChartletRecyclerView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartlet_download);

        ButterKnife.inject(this);
        new ChartletLoader().execute();
    }

    public class ChartlatPagerAdapter extends PagerAdapter {

        private Context context;
        private ArrayList<ChartletType> mTypeList;
        private ArrayList<ChartletRecyclerView> mChartList;

        public ChartlatPagerAdapter(Context mContext,ArrayList<ChartletType> typeList,ArrayList<ChartletRecyclerView> ChartList){
            this.context = mContext;
            this.mTypeList = typeList;
            this.mChartList = ChartList;
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
            ((ViewPager)container).addView(mChartList.get(position), 0);
            return mChartList.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //解决界面重叠
            ((ViewPager)container).removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTypeList.get(position).getType();
        }
    }


    private class ChartletLoader extends AsyncTask<Void,Void,ArrayList<ChartletRecyclerView>>{

        private ArrayList<ChartletType> mTypeList = new ArrayList<ChartletType>();
        private ArrayList<ChartletItem> mChartList= new ArrayList<ChartletItem>();
        private Sticker mSticker;

        @Override
        protected ArrayList<ChartletRecyclerView> doInBackground(Void... params) {

            String stickerStr = readFromAsset("sticker.json");
            Sticker localSticker = JSON.parseObject(stickerStr, Sticker.class);
            String str = "ss";

            mTypeList = (ArrayList<ChartletType>) localSticker.getTypelist();
            mChartList = (ArrayList<ChartletItem>) localSticker.getCommonlist();

            //添加全部
            chartletViews.add(new ChartletRecyclerView(ChartletDownloadActivity.this, mChartList));
            //从第一个开始
            if(mTypeList.size()>1){
                for(int i = 1;i<mTypeList.size();i++) {
                    ArrayList<ChartletItem> tempList = new ArrayList<ChartletItem>();
                    for(ChartletItem c :mChartList){
                        if(c.getTypeid().equals(mTypeList.get(i).getId())){
                            tempList.add(c);
                        }
                    }
                    chartletViews.add(new ChartletRecyclerView(ChartletDownloadActivity.this, tempList));
                    tempList.clear();
                }
            }

            return chartletViews;
        }
        @Override
        protected void onPostExecute(ArrayList<ChartletRecyclerView> chartletRecyclerViews) {
            super.onPostExecute(chartletRecyclerViews);
            viewPager.setAdapter(new ChartlatPagerAdapter(ChartletDownloadActivity.this, mTypeList, chartletViews));
            smartTabLayout.setViewPager(viewPager);

        }
    }

    public String readFromAsset(String fileName) {
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = getAssets().open(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            String addonStr = "";
            String line = br.readLine();
            while (line != null) {
                addonStr = addonStr + line;
                line = br.readLine();
            }
            return addonStr;
        } catch (Exception e) {
            return null;
        } finally {
            IOUtil.closeStream(br);
            IOUtil.closeStream(is);
        }
    }

}
