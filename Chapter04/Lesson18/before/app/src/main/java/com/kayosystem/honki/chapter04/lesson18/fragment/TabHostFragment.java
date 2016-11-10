package com.kayosystem.honki.chapter04.lesson18.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.kayosystem.honki.chapter04.lesson18.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabHostFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_host, container, false);

        TabHost tabhost = (TabHost) rootView.findViewById(android.R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec tab1 = tabhost.newTabSpec("tab1");
        tab1.setIndicator("タブ１");
        tab1.setContent(R.id.tab1);
        tabhost.addTab(tab1);

        TabHost.TabSpec tab2 = tabhost.newTabSpec("tab2");
        tab2.setIndicator("タブ2");
        tab2.setContent(R.id.tab2);
        tabhost.addTab(tab2);

        TabHost.TabSpec tab3 = tabhost.newTabSpec("tab3");
        tab3.setIndicator("タブ3");
        tab3.setContent(R.id.tab3);
        tabhost.addTab(tab3);

        tabhost.setCurrentTab(0);

        return rootView;
    }


}
