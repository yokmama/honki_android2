package com.kayosystem.honki.chapter06.lesson27;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Todo> mTodoList;

    private boolean mIsTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ダミーデータ作成
        mTodoList = Todo.addDummyItem();

        //TODOリスト一覧を表示
        showTodoList();

        //タブレットレイアウトなら右側にフォーム画面を表示
        FrameLayout container2 = (FrameLayout) findViewById(R.id.container2);
        if (container2 != null) {
            mIsTablet = true;
            showTodoForm(mTodoList.get(0));
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            //フォーム画面を開いている場合は画面を閉じる
            getSupportFragmentManager().popBackStack();
        } else {
            //リスト画面の場合は通常のバックキー処理(アプリを終了)
            super.onBackPressed();
        }
    }

    /**
     * TODOリスト一覧を表示
     */
    public void showTodoList() {
        String tag = TodoListFragment.TAG;
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                TodoListFragment.newInstance(), tag).commit();
    }

    /**
     * TODOフォーム画面を表示
     *
     * @param item TODOリストデータ
     */
    public void showTodoForm(Todo item) {
        String tag = TodoFormFragment.TAG;
        TodoFormFragment fragment;
        if (item == null) {
            fragment = TodoFormFragment.newInstance();
        } else {
            fragment = TodoFormFragment.newInstance(item.getColorLabel(),
                    item.getValue(), item.getCreatedTime());
        }
        if (!mIsTablet) {
            //スマートフォンレイアウトの場合はcontainerに表示
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    fragment, tag).addToBackStack(tag).commit();
        }else{
            //タブレットレイアウトの場合はcontainer2に表示
            getSupportFragmentManager().beginTransaction().replace(R.id.container2,
                    fragment, tag).addToBackStack(tag).commit();
        }
    }

    public List<Todo> getTodoList() {
        return mTodoList;
    }

    /**
     * タブレットか判定.
     * @return
     */
    public boolean isTablet() {
        return mIsTablet;
    }
}

