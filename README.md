# MarqueeImageViewPager
With MarqueeImageViewPager, you work with the prevailing scroll images and circle dot indicator pattern effortlessly, this library benefits from ![universal image loader](https://github.com/nostra13/Android-Universal-Image-Loader), ![viewpagerindicator](https://github.com/JakeWharton/ViewPagerIndicator) (with a bit modification for head-to-end animation bug fix) and ![autoscrollviewpager](https://github.com/Trinea/android-auto-scroll-view-pager) <br>
![](https://github.com/Kerence/MarqueeImageViewPager/blob/master/raw/images-folder/1.jpg)
<br>
#Usage:<br>
Typically add the following xml code in desired layout file:
```xml
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="200dp">

        <com.kimi.marqueeimageviewpager.MarqueeImageViewPager
            android:id="@+id/mivp"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

        <com.kimi.viewpagerindicator.CirclePageIndicator
            android:id="@+id/indicator"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:padding="10dip" />
    </RelativeLayout>
```
then get the page indicator in touch with viewpager with the following code:<br>
(the param images is of type List<String> which indicate the uri of images you wanna display)
```Java
((MarqueeImageViewPager) findViewById(R.id.mivp)).loadImages(images, null);
                        ((CirclePageIndicator) findViewById(R.id.indicator)).setViewPager(mvp);
```
#1.you can set imageview scaletype easily by using:
```Java
MarqueeImageViewPager.loadImages(images, new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .displayer(new BitmapDisplayer() {
                                @Override
                                public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                                    imageAware.setImageBitmap(bitmap);
                                    ((ImageView) imageAware.getWrappedView()).setScaleType(ImageView.ScaleType.FIT_CENTER);
                                }
                            })
                            .build());
```
![](https://github.com/Kerence/MarqueeImageViewPager/blob/master/raw/images-folder/2015_10_29_01_51_59~1.gif?raw=true)
#2.
