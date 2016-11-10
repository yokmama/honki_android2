package com.kayosystem.honki.chapter09.lesson37;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

abstract class LogActivity extends AppCompatActivity {
    private LogListFragment mLogListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        mLogListFragment = (LogListFragment) getFragmentManager().findFragmentById(R.id.loglist_fragment);

        addLog("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        addLog("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        addLog("onResume");
    }

    @Override
    protected void onPause() {
        addLog("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        addLog("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        addLog("onDestroy");
        super.onDestroy();
    }

    /**
     * LogListFragmentのListViewにメッセージを追加.
     *
     * @param msg
     */
    protected void addLog(String msg) {
        if (mLogListFragment != null) {
            mLogListFragment.pushLogMessage(msg);
        }
    }

    /**
     * ActivityのレイアウトIDを返却.
     *
     * @return
     */
    abstract public int getActivityLayoutId();
}