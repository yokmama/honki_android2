package com.kayosystem.honki.chapter04.lesson16.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.kayosystem.honki.chapter04.lesson16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_snack_bar, container, false);

        //Toastの使用例
        rootView.findViewById(R.id.buttonToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show()を忘れないないように
                Toast.makeText(getActivity(), "こんにちはToastです。", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}
