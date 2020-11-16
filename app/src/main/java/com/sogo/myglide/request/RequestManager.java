package com.sogo.myglide.request;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 单利，请求队列管理
 * 控制图片加载过程的管理类
 *
 * @author wangheng
 */
public class RequestManager {
    /**
     * 请求队列，需要添加所以用link链表式
     */
    private LinkedBlockingQueue<BitmapRequest> mQueue;
    private BitmapDispatcher[] bitmapDispatchers;

    private RequestManager() {
        mQueue = new LinkedBlockingQueue<>();
        creatAndOpenThread();
    }

    private void creatAndOpenThread() {
        //app可以创建的最多线程数
        int count = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[count];
        for (int i = 0; i < count; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(mQueue);
            //开启线程
            bitmapDispatcher.start();
            bitmapDispatchers[i] = bitmapDispatcher;
        }
    }

    private static RequestManager requestManager = new RequestManager();

    public static RequestManager getInstance() {
        return requestManager;
    }

    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (mQueue != null && !mQueue.contains(bitmapRequest)) {
            mQueue.add(bitmapRequest);
        }
    }

    /**
     * 关闭所有线程
     */
    public void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if (!bitmapDispatcher.isInterrupted()){
                    bitmapDispatcher.isInterrupted();
                }

            }
        }
    }
}
