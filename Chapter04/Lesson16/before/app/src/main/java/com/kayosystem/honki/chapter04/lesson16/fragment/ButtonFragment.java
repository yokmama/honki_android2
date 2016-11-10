package com.kayosystem.honki.chapter04.lesson16.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.kayosystem.honki.chapter04.lesson16.R;

//入力処理
public class ButtonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_button, container, false);

        //Buttonのクリックリスナーを設定
        rootView.findViewById(R.id.buttonNormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //トーストを表示
                Toast.makeText(getActivity(), "Button Click!", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}
