package com.kayosystem.honki.chapter04.lesson16.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.kayosystem.honki.chapter04.lesson16.R;

public class ToggleButtonFragment extends Fragment {

    ToggleButton mToggleButton;

    TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_toggle_button, container, false);

        //TextView,ToggleButtonのインスタンスを取得
        mTextView = (TextView) rootView.findViewById(R.id.textView);
        mToggleButton = (ToggleButton) rootView.findViewById(R.id.toggleButton1);

        //リスナーをセット
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //テキストを更新
                updateText();
            }
        });

        //TextViewを更新
        updateText();

        return rootView;
    }


    /**
     * ToggleButtonの値をテキストで表示.
     */
    private void updateText() {
        mTextView.setText("Toggle is " + mToggleButton.isChecked());
    }
}
