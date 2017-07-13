package com.dyhdyh.support.glide.example;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.stream.StreamStringLoader;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dyhdyh.support.glide.GlideSupport;
import com.dyhdyh.support.glide.animator.DrawableCrossFadeAnimator;
import com.dyhdyh.support.glide.resource.gifbitmap.ImageWrapper;
import com.dyhdyh.support.glide.resource.gifbitmap.ImageWrapperDrawableTranscoder;
import com.dyhdyh.support.glide.resource.gifbitmap.ImageWrapperFileToStreamDecoder;
import com.dyhdyh.support.glide.resource.gifbitmap.ImageWrapperStreamResourceDecoder;
import com.dyhdyh.support.glide.transformations.CircleTransformation;
import com.dyhdyh.support.glide.transformations.ImageWrapperCircleTransformation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {
    ImageView iv_glide;
    ImageView iv_gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_glide = (ImageView) findViewById(R.id.iv_glide);
        iv_gif = (ImageView) findViewById(R.id.iv_gif);
    }


    public void clickBitmap(View v) {
        //String url = "http://img2.imgtn.bdimg.com/it/u=848366370,4290604224&fm=26&gp=0.jpg";
        //String url="http://img3.duitang.com/uploads/item/201407/18/20140718155834_NMS23.jpeg";
        String url = "http://dl.bizhi.sogou.com/images/2012/03/07/70401.jpg";
        loadGlide(url);
        //Toast.makeText(this, iv_gif.getDrawable().getClass().getName()+"", Toast.LENGTH_SHORT).show();
    }

    public void clickGif(View v) {
        //500*309
        String url = "http://imgs.ebrun.com/resources/2016_08/2016_08_12/201608128571470994130828.gif";
        //600*250
        //String url="http://img208.poco.cn/mypoco/myphoto/20110620/23/53838356201106202249241923418303298_000.gif";

        //400*400
        //String url = "http://www.cheerinus.com/upload_files/qb_news_/95/68143_20160528050505_fttee.gif";
        //600*600
        //String url="http://payload345.cargocollective.com/1/0/19261/9211166/random_ani_buns_600x600.gif";

        //400*800
        //String url="http://www.cheerinus.com/upload_files/qb_news_/95/68143_20160528050505_fttee.gif";
        loadGlide(url);
    }


    public void clickLongGif(View v) {
        String url = "http://www.poluoluo.com/qq/UploadFiles_7828/201410/20141022210900574.gif";
        loadGlide(url);
    }

    public void clickBigGif(View v) {
        String url = "http://i2.kiimg.com/1949/7e171c3a397062e0.gif";
        loadGlide(url);
    }



    private void loadGlide(String url) {
        Glide.with(this)
                .load(url)
                .bitmapTransform(new CircleTransformation(this))//圆形
                .crossFade()//淡入淡出
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(iv_glide);

        GlideSupport.with(this)
                .gifEnhancement()//开启gif增强
                .crossFade()//淡入淡出
                .circle()//圆形
                //.transform()
                //.animate(new DrawableScaleBounceAnimator())
                .glide()
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(iv_gif);
    }

    private void loadGlide3(String url) {

        Glide.with(this)
                .load(url)
                .bitmapTransform(new CircleTransformation(this))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .override(100, 100)
                .into(iv_glide);


        /*Glide
                .with(this)
                .using(new StreamStringLoader(this), InputStream.class)
                .from(String.class)
                .as(byte[].class)
                .transcode(new GifDrawableByteTranscoder(), GifDrawable.class)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .decoder(new StreamByteArrayResourceDecoder())
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<byte[]>(new StreamByteArrayResourceDecoder()))
                .load(url).into(iv_gif);*/

        Glide
                .with(this)
                .using(new StreamStringLoader(this), InputStream.class)
                .from(String.class)
                .as(ImageWrapper.class)
                .transcode(new ImageWrapperDrawableTranscoder(this), Drawable.class)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .decoder(new ImageWrapperStreamResourceDecoder(this))
                .cacheDecoder(new ImageWrapperFileToStreamDecoder(this))
                .sourceEncoder(new StreamEncoder())
                .override(100, 100)
                .animate(new DrawableCrossFadeAnimator())
                .transform(new ImageWrapperCircleTransformation(this))
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round).into(iv_gif);


        /*Glide
                .with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                //.transform(new CircleTransformation(this))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        iv_gif.setImageDrawable(resource);
                    }
                });*/
    }

    private void loadGlide1(String url) {
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                //.transform(new CircleTransformation(this))
                .into(iv_glide);


        if (url.endsWith(".gif")) {
            Glide.with(this)
                    .load(url)
                    .downloadOnly(new SimpleTarget<File>() {
                        @Override
                        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                            try {
                                GifDrawable gifDrawable = new GifDrawable(resource);
                                //gifDrawable.setTransform(new GifDrawableCircleTransform());
                                iv_gif.setImageDrawable(gifDrawable);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            DrawableRequestBuilder<String> builder = Glide.with(this)
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher_round);
            //builder.into(iv_circle);
            //builder.transform(new CircleTransformation(this)).into(iv_gif);
        }
    }


}