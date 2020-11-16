package com.sogo.myglide.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 执行请求的线程，去队列中获取请求对象
 *
 * @author wangheng
 */
public class BitmapDispatcher extends Thread {
    private LinkedBlockingQueue<BitmapRequest> mQueue;
    private Handler handler = new Handler(Looper.getMainLooper());

    //多个线程统一管理，处理同一个队列
    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> mQueue) {
        this.mQueue = mQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //队列里面获取到请求对象，消费数据
                BitmapRequest bitmapRequest = mQueue.take();
                //占位图
                showLoadingImg(bitmapRequest);
                //加载图片
                Bitmap bitmap = findBitmap(bitmapRequest);
                //显示图片
                showImageView(bitmapRequest, bitmap);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showImageView(BitmapRequest bitmapRequest, final Bitmap bitmap) {
        if (bitmap != null && bitmapRequest != null && bitmapRequest.getImageView() != null &&
                bitmapRequest.getImageView().getTag().equals(bitmapRequest.getUrlMD5())) {
            final ImageView imageView = bitmapRequest.getImageView();
            //主线程更新ui
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
    }

    private Bitmap findBitmap(BitmapRequest bitmapRequest) {
        //去服务器加载图片
        Bitmap bitmap = downloadImage(bitmapRequest.getUrl());
        return bitmap;
    }

    private void showLoadingImg(BitmapRequest bitmapRequest) {
        if (bitmapRequest.getResId() > 0 && bitmapRequest.getImageView() != null) {
            final int resId = bitmapRequest.getResId();
            final ImageView imageView = bitmapRequest.getImageView();
            //主线程更新ui
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resId);
                }
            });

        }
    }

    private Bitmap downloadImage(String uri) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            //创建URL对象
            URL imageurl = new URL(uri);
            //使用HttpURLConnection通过URL去开始读取数据
            HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }

}
