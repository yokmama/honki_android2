package com.kayosystem.honki.chapter04.lesson17.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.kayosystem.honki.chapter04.lesson17.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabLayoutFragment extends Fragment {

    private TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        //タブを追加
        mTabLayout.addTab(mTabLayout.newTab().setText("タブ１"));
        mTabLayout.addTab(mTabLayout.newTab().setText("タブ２"));
        mTabLayout.addTab(mTabLayout.newTab().setText("タブ３"));

        //タブ選択時の処理
        mTabLayout.addOnTabSelectedListener(mTabSelectedListener);

        return rootView;
    }

    TabLayout.OnTabSelectedListener mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //タブが選択された
            Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //タブが未選択になった
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //タブが再選択された
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabLayout.removeOnTabSelectedListener(mTabSelectedListener);
    }

}
