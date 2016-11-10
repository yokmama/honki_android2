package com.kayosystem.honki.chapter07.lesson29.samples.navigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kayosystem.honki.chapter07.lesson29.R;

public class NavigationViewContentsFragment extends Fragment {
    private static final String EXTRA_ITEM_NAME = "extra.ITEM_NAME";

    public static NavigationViewContentsFragment newInstance(CharSequence itemName) {
        NavigationViewContentsFragment fragment = new NavigationViewContentsFragment();

        Bundle args = new Bundle();
        args.putCharSequence(EXTRA_ITEM_NAME, itemName);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_view, container, false);

        TextView textView = (TextView) v.findViewById(R.id.text);
        textView.setText(getText());

        return v;
    }

    private String getText() {
        CharSequence title = getArguments().getCharSequence(EXTRA_ITEM_NAME);
        return String.format("テキスト\n%1$s", title);
    }
}
