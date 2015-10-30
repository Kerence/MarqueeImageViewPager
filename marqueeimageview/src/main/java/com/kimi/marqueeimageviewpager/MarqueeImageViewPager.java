
package com.kimi.marqueeimageviewpager;

import android.content.Context;
import android.util.AttributeSet;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MarqueeImageViewPager extends AutoScrollViewPager {
    private Context c;
    private boolean enableInfiniteMarquee = true;

    public void enableInfiniteMarquee(boolean enable) {
        if (bannerViewAdapter != null) {
            bannerViewAdapter.enableInfiniteMarquee(enable);
        }
        jump2MidOffset();
        enableInfiniteMarquee = enable;
    }

    public MarqueeImageViewPager(Context context) {
        this(context, null);

    }

    public MarqueeImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        c = context;
    }

    private void jump2MidOffset() {
        if (bannerViewAdapter == null) {
            return;
        }
        int itemCount = bannerViewAdapter.getItemCount();
        if (itemCount == 0) {
            return;
        }
        int mid = ImageAdapter.MAX_PAGE_COUNT / 2;
        int offset = mid % itemCount;
        int initPosition = mid - offset;
        setCurrentItem(initPosition, false);
    }

    public void loadImages(List<String> urls, DisplayImageOptions options) {
        loadImages(urls, options, null);
    }

    public void loadImages(List<String> urls, DisplayImageOptions options, ImageLoadingListener imageLoadingListener) {
        if (urls == null || urls.size() == 0) {
            return;
        }
        bannerViewAdapter = new ImageAdapter(c, urls, options, imageLoadingListener);
        bannerViewAdapter.enableInfiniteMarquee(enableInfiniteMarquee);
        setAdapter(bannerViewAdapter);
        bannerViewAdapter.notifyDataSetChanged();
        if (enableInfiniteMarquee) {
            jump2MidOffset();
        }
    }

    protected ImageAdapter bannerViewAdapter;

}

