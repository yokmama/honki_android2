package com.kayosystem.honki.chapter08.lesson34;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements MyAdapter.DeleteListener{

    private static final String[] initData = {"黒猫", "白猫", "虎猫", "三毛猫", "錆び猫", "はちわれ"};
    private Realm mRealm;
    private MyAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 詳細画面開始
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("cat_id", Long.toString(id));
                startActivity(intent);
            }
        });


        // 初期データを読み込みます
        initCat();
    }

    /**
     * 初期データを追加します。
     * 保存しているデータが0件の場合は、サンプルデータを作成します。
     */
    private void initCat(){

        mRealm = Realm.getDefaultInstance();

        // すべてのCatを取得します。
        RealmResults<Cat> cats = mRealm.where(Cat.class).findAll().sort("id");
        if (cats.size() == 0) {

            // データが無い場合は初期データを追加
            mRealm.beginTransaction();
            for (int i = 0; i < initData.length; i++) {
                Cat cat = mRealm.createObject(Cat.class, i);
                cat.setName(initData[i]);
            }
            mRealm.commitTransaction();
        }

        mAdapter = new MyAdapter(this, cats);
        mListView.setAdapter(mAdapter);
        mAdapter.setCallback(this);
    }

    /**
     * Catを削除します
     * @param catId
     */
    private void deleteCat(long catId){
        final long id = catId;
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cat cat = realm.where(Cat.class).equalTo("id", id).findFirst();
                cat.deleteFromRealm();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_add) {

            // 詳細画面開始
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void delete(long catId) {
        deleteCat(catId);
    }
}