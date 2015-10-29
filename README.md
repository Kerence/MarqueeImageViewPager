# MarqueeImageViewPager
With MarqueeImageViewPager, you work with the prevailing scroll images and circle dot indicator pattern effortlessly, this library benefits from ![universal image loader](https://github.com/nostra13/Android-Universal-Image-Loader) and ![viewpagerindicator](https://github.com/JakeWharton/ViewPagerIndicator) (with a bit modification for head-to-end animation bug fix)
![](https://github.com/Kerence/MarqueeImageViewPager/blob/master/raw/images-folder/1.jpg)
<br>
#you can set imageview scaletype easily by using:
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
