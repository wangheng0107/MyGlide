package com.sogo.myglide.request;

import android.graphics.Bitmap;

/**
 * 回调接口
 *
 * @author wangheng
 */
public interface RequestListener {
    /**
     * 回调成功
     *
     * @param bitmap
     */
    void onSuccess(Bitmap bitmap);

    /**
     * 回调失败
     */
    void onFailed();
}
