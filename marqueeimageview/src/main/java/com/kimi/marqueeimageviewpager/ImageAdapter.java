package com.kimi.marqueeimageviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    public static final int MAX_PAGE_COUNT = 1000000;
    private List<String> images;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .cacheOnDisk(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .considerExifParams(true)
            .displayer(new FadeInBitmapDisplayer(300))
            .build();
    private Context context;
    private ImageLoadingListener imageLoadingListener;

    public int getItemCount() {
        if (images == null) {
            return 0;
        } else {
            return images.size();
        }
    }

    ImageAdapter(Context context, List<String> images, DisplayImageOptions options, ImageLoadingListener imageLoadingListener) {
        this.context = context;
        this.images = images;
        this.imageLoadingListener = imageLoadingListener;
        if (options != null) {
            this.options = options;
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    private boolean infiniteMarqueeEnabled;

    public void enableInfiniteMarquee(boolean enable) {
        infiniteMarqueeEnabled = enable;
    }

    @Override
    public int getCount() {
        if (images == null) {
            return 0;
        } else {
            if (infiniteMarqueeEnabled) {
                return MAX_PAGE_COUNT;
            } else {
                return images.size();
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);
        ImageLoader.getInstance().displayImage(images.get(position % images.size()), imageView, options, imageLoadingListener);
        view.addView(imageView, 0);
        return imageView;
    }
}
