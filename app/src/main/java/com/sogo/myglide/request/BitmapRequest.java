package com.sogo.myglide.request;

import android.content.Context;
import android.widget.ImageView;

import com.sogo.myglide.util.Md5Utils;

import java.lang.ref.SoftReference;

/**
 * 首先请求对象封装
 *
 * @author wangheng
 */
public class BitmapRequest {

    /**
     * 上下文
     */
    private Context context;

    /**
     * 路径
     */
    private String url;

    /**
     * 图片控件，SoftReference利于回收
     */
    private SoftReference<ImageView> imageView;

    /**
     * 结果回调接口
     */
    private RequestListener requestListener;

    /**
     * 请求的标识
     */
    private String urlMD5;

    /**
     * 占位符的id
     */
    private int resId;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMD5 = Md5Utils.md5Hex(url);
        return this;
    }

    public BitmapRequest loading(int resId) {
        this.resId = resId;
        return this;
    }

    /**
     * 封装完毕，添加到请求队列
     *
     * @param imageView
     */
    public void into(ImageView imageView) {
        imageView.setTag(this.urlMD5);
        this.imageView = new SoftReference<>(imageView);
        //在此结尾将bitmap添加到请求队列
        RequestManager.getInstance().addBitmapRequest(this);
    }

    public BitmapRequest listener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMD5() {
        return urlMD5;
    }

    public int getResId() {
        return resId;
    }
}
