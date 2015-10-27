package com.kimi.marqueeimageviewapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.kimi.marqueeimageviewpager.MarqueeImageViewPager;
import com.kimi.marqueeimageviewapp.model.Root;
import com.kimi.marqueeimageviewapp.model.newslist;
import com.kimi.viewpagerindicator.CirclePageIndicator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


public class MainActivity extends Activity {
    void initWidget() {
        ((ToggleButton) findViewById(R.id.tb_autoscroll)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.startAutoScroll();
                } else {
                    mvp.stopAutoScroll();
                }
            }
        });
        ((ToggleButton) findViewById(R.id.tb_reverse)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.setDirection(AutoScrollViewPager.RIGHT);
                } else {
                    mvp.setDirection(AutoScrollViewPager.LEFT);
                }
            }
        });
        findViewById(R.id.tb_randomInterval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvp.setInterval(new Random().nextInt(5000) + 1000);
            }
        });
        ((ToggleButton) findViewById(R.id.tb_consecutive_scroll)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mvp.enableInfiniteMarquee(isChecked);
                mvp.getAdapter().notifyDataSetChanged();
            }
        });
        findViewById(R.id.btn_random_circle_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CirclePageIndicator indicator = ((CirclePageIndicator) findViewById(R.id.indicator));
                final float density = getResources().getDisplayMetrics().density;
                Random ra = new Random();
                indicator.setBackgroundColor(ra.nextInt());
                indicator.setRadius((ra.nextInt(3) + 3) * density);
                indicator.setPageColor(ra.nextInt());
                indicator.setFillColor(ra.nextInt());
                indicator.setStrokeColor(ra.nextInt());
                indicator.setStrokeWidth((ra.nextInt(3)) * density);
            }
        });
        ((RadioButton) findViewById(R.id.rb_centerCrop)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new BitmapDisplayer() {
                                @Override
                                public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                                    imageAware.setImageBitmap(bitmap);
                                    ((ImageView) imageAware.getWrappedView()).setScaleType(ImageView.ScaleType.CENTER_CROP);
                                }
                            })
                            .build(), null);

                }
            }
        });
        ((RadioButton) findViewById(R.id.rb_fitXY)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new BitmapDisplayer() {
                                @Override
                                public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                                    imageAware.setImageBitmap(bitmap);
                                    ((ImageView) imageAware.getWrappedView()).setScaleType(ImageView.ScaleType.FIT_XY);
                                }
                            })
                            .build());

                }
            }
        });
        ((RadioButton) findViewById(R.id.rb_centerInside)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new BitmapDisplayer() {
                                @Override
                                public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                                    imageAware.setImageBitmap(bitmap);
                                    ((ImageView) imageAware.getWrappedView()).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                }
                            })
                            .build());

                }
            }
        });
        ((RadioButton) findViewById(R.id.rb_fitCenter)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new BitmapDisplayer() {
                                @Override
                                public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                                    imageAware.setImageBitmap(bitmap);
                                    ((ImageView) imageAware.getWrappedView()).setScaleType(ImageView.ScaleType.FIT_CENTER);
                                }
                            })
                            .build());

                }
            }
        });
        ((ToggleButton) findViewById(R.id.tb_imageClickListener)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mvp.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new FadeInBitmapDisplayer(300))
                            .build(), new ImageLoadingListener() {

                        @Override
                        public void onLoadingStarted(final String imageUri, View view) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "image with uri:" + imageUri + " clicked", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });

                } else {
                    mvp.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new FadeInBitmapDisplayer(300))
                            .build());
                }
            }
        });

    }

    MarqueeImageViewPager mvp;
    List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        mvp = (MarqueeImageViewPager) findViewById(R.id.mivp);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://r.inews.qq.com/getQQNewsIndexAndItems").build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                String htmlStr = response.body().string();
                Root r = new Gson().fromJson(htmlStr, Root.class);
                List<newslist> list = r.idlist.get(0).newslist;
                images = new ArrayList<String>();
                for (int i = 0; i < list.size(); i++) {
                    images.add(list.get(i).thumbnails.get(0));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mvp.loadImages(images, null);
                        ((CirclePageIndicator) findViewById(R.id.indicator)).setViewPager(mvp);
//                        ((CirclePageIndicator) findViewById(R.id.indicator)).setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                            @Override
//                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                            }
//
//                            @Override
//                            public void onPageSelected(int position) {
//                                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onPageScrollStateChanged(int state) {
//
//                            }
//                        });
                    }
                });

            }
        });
    }

}
