package com.github.skykai.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.skykai.R;
import com.github.skykai.model.StickerItem;
import com.github.skykai.util.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.Random;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealFrameLayout;

/**
 * Created by sky on 2015/6/11.
 */
public class StickerRecyclerView extends RecyclerView {

    private static final long[] delayList = {400, 500, 600, 700, 800, 900};

    private Context mContext;
    private ArrayList<StickerItem> mStickerList;
    private StickerAdapter stickerAdapter;
    private SupportAnimator mAnimator;


    public StickerRecyclerView(Context context, ArrayList<StickerItem> chartList) {
        super(context);
        this.mContext = context;
        this.mStickerList = chartList;

        init();

    }

    private void init() {
        setLayoutManager(new GridLayoutManager(mContext, 3));
        stickerAdapter = new StickerAdapter();
        stickerAdapter.setList(mStickerList);
        setAdapter(stickerAdapter);
    }


    private class StickerAdapter extends RecyclerView.Adapter<ViewHolder> {

        private ArrayList<StickerItem> items = new ArrayList<StickerItem>();
        private int lastPosition = -1;

        public void setList(ArrayList<StickerItem> list) {
            if (items.size() > 0) {
                items.clear();
            }
            items.addAll(list);
        }



        @Override
        public void onViewAttachedToWindow(final ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            final long delayTime = delayList[new Random().nextInt(5)];
            holder.card.setVisibility(View.INVISIBLE);

            if (holder.getPosition() > lastPosition) {
                holder.card.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.card.setVisibility(View.VISIBLE);
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.card, "alpha", 0f, 1f);
                        ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.card, "scaleY", 0f, 1f);
                        ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.card, "scaleX", 0f, 1f);
                        AnimatorSet animSet = new AnimatorSet();
                        animSet.play(alpha).with(scaleY).with(scaleX);
                        animSet.setInterpolator(new OvershootInterpolator());
                        animSet.setDuration(400);
                        animSet.start();

                    }
                }, delayTime);

                lastPosition = holder.getPosition();
            } else {
                holder.card.setVisibility(View.VISIBLE);
            }
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sticker, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final StickerItem stickerItem = items.get(position);
            ImageLoader.getInstance().displayImage(stickerItem.getThumbnail2(), holder.chartlet
                    , Utils.getInst().buildDefaultOptions());
            holder.download.setText("下载");
            holder.download.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {

                        final View myView = holder.card;
                        int cx = (myView.getLeft() + myView.getRight()) / 2;
                        int cy = (myView.getTop() + myView.getBottom()) / 2;
                        float finalRadius = hypo(myView.getWidth(), myView.getHeight());
                        mAnimator = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
                        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                        mAnimator.setDuration(600);
                        mAnimator.start();

                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }


    //Viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView download;
        private ImageView chartlet;
        private CardView card;
        RevealFrameLayout mReveal;

        public ViewHolder(View contentView) {
            super(contentView);
            card = (CardView) contentView.findViewById(R.id.card);
            chartlet = (ImageView) contentView.findViewById(R.id.chartlet_review);
            download = (TextView) contentView.findViewById(R.id.download_btn);
            mReveal = (RevealFrameLayout) card.getParent();

        }
    }

    static float hypo(int a, int b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

}
