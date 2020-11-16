package com.sogo.myglide.request;

import android.content.Context;

/**
 * @author wangheng
 */
public class Glide {
    public static BitmapRequest with(Context context) {
        return new BitmapRequest(context);
    }
}
