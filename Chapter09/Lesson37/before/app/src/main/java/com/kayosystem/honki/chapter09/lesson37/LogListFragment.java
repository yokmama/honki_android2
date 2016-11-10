package com.kayosystem.honki.chapter09.lesson37;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * いつでもご優待画面フラグメント.
 */
public class LogListFragment extends ListFragment {
    public static LogListFragment newInstance() {
        return new LogListFragment();
    }

    private ArrayAdapter<String> mAdapter;
    private int mIndex;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(mAdapter);
    }

    /**
     * ListViewにログを追加.
     *
     * @param msg
     */
    protected void pushLogMessage(String msg) {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        }
        mIndex++;
        mAdapter.add(mIndex + " : " + msg);
        if (getView() != null) {
            setSelection(mAdapter.getCount());
        }
    }
}