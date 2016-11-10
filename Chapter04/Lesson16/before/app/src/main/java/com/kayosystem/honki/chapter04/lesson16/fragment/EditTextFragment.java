package com.kayosystem.honki.chapter04.lesson16.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.kayosystem.honki.chapter04.lesson16.R;

public class EditTextFragment extends Fragment {

    private static final String TAG = EditTextFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_text, container, false);

        //EditTextのインスタンスを取得
        EditText etValidate = (EditText) rootView.findViewById(R.id.editValidate);

        // 入力を制限するInputFilterを作成
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                    Spanned dest, int dstart, int dend) {
                if (source.toString().matches("^[0-9a-zA-Z@¥.¥_¥¥-]+$")) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        //InputFilterをセット
        etValidate.setFilters(new InputFilter[]{inputFilter});

        return rootView;
    }
}
