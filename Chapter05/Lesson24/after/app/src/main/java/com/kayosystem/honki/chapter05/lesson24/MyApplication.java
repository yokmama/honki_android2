package com.kayosystem.honki.chapter05.lesson24;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.util.Random;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    private HandlerThread mHandlerThread;

    private Handler mHandler;
    private int mCounter;
    private Random mRand = new Random();


    @Override
    public void onCreate() {
        super.onCreate();

        //Handlerを生成
        mHandlerThread = new HandlerThread("myLooper");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());

        handlerMessage();
    }


    public void countUp() {
        mHandler.post(new Task(mCounter++));
    }

    private class Task implements Runnable {

        private int mIndex;

        public Task(int index) {
            mIndex = index;
        }

        @Override
        public void run() {
            try {
                //スレッドをランダムにスリープ
                int sleepTime = mRand.nextInt(5) * 1000;
                Log.d(TAG, "sleep " + sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "My Index is " + mIndex);
        }
    }

    private void handlerMessage() {
        Handler myHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 100) {
                    Log.d(TAG, String.format("%d %d %s", msg.arg1, msg.arg2, (String) msg.obj));
                }
                return false;
            }
        });

        myHandler.sendMessage(myHandler.obtainMessage(100, 1, 2, "test"));
    }

}
