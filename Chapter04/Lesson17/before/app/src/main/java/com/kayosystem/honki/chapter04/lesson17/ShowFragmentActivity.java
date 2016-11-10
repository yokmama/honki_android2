package com.kayosystem.honki.chapter04.lesson17;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class ShowFragmentActivity extends AppCompatActivity {
    public static final String EXTRA_FRAGMENT_CLASS = "extra.FRAGMENT_CLASS";
    public static final String EXTRA_TITLE = "extra.TITLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        // ActionBar内に←を設置
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // タイトルを設定
        setTitle(intent.getStringExtra(EXTRA_TITLE));

        // 画面を表示
        Fragment fragment = getIntentFragment(intent);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public Fragment getIntentFragment(Intent intent) {
        String fragmentClass = intent.getStringExtra(EXTRA_FRAGMENT_CLASS);
        if (fragmentClass != null) {
            try {
                return (Fragment) Class.forName(fragmentClass).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
