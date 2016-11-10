package com.kayosystem.honki.chapter08.lesson34;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Realm mRealm;
    private EditText mEditName;
    private long mCat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // ソフトウェアキーボードを開く
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mEditName = (EditText) findViewById(R.id.editText);
        findViewById(R.id.btnOK).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);

        // 初期データの設定
        initData();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btnOK) {

            insert();
        }
        finish();
    }
    
    /**
     * 主キーを生成します。
     *
     * @return 次のID
     */
    public long nextCatId() {
        RealmResults<Cat> results = mRealm.where(Cat.class).findAll().sort("id", Sort.DESCENDING);
        if (results.size() > 0) {
            return results.first().getId() + 1;
        }
        return 0;
    }
    /**
     * 初期データの設定
     */
    private void initData(){
        mRealm = Realm.getDefaultInstance();

        String cat_id = getIntent().getStringExtra("cat_id");
        if (TextUtils.isEmpty(cat_id)) {

            // データが無い場合は新しいIDを取得
            mCat_id = nextCatId();
        } else {

            // データがある場合は更新
            mCat_id = Long.parseLong(cat_id);
            mEditName.setText(mRealm.where(Cat.class).equalTo("id", mCat_id).findFirst().getName());
            mEditName.setSelection(mEditName.getText().length());
        }
    }

    /**
     * Catを追加します
     */
    public void insert() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cat cat = new Cat();
                cat.setId(mCat_id);
                cat.setName(mEditName.getText().toString());
                realm.insertOrUpdate(cat);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.close();
    }
}
