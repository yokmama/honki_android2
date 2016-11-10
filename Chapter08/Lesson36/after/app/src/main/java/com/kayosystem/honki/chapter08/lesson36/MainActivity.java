package com.kayosystem.honki.chapter08.lesson36;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private TextView mTxtToday;
    private TextView mTxtHistory;

    /**
     * 現在年月日(yyyy-MM-dd)を取得する
     *
     * @return 現在年月日(yyyy-MM-dd)
     */
    private String getDate() {
        CharSequence charSequence = android.text.format.DateFormat.format("yyyy-MM-dd", Calendar.getInstance());
        return charSequence.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtToday = (TextView) findViewById(R.id.txToday);
        mTxtToday.setText(getDate());

        findViewById(R.id.btnArrive).setOnClickListener(this);
        findViewById(R.id.btnLeave).setOnClickListener(this);

        mTxtHistory = (TextView) findViewById(R.id.txtHistory);

        setup();
    }

    /**
     * Firebaseのデータベースからデータを取得する
     */
    private void setup() {

        DatabaseReference reference = database.getReference("attendance");
        Query query = reference.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mTxtHistory.setText(null);

                StringBuilder sb = new StringBuilder();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String key = snapshot.getKey();
                    Attendance attendance = snapshot.getValue(Attendance.class);

                    sb.append(key).append(" ").append(attendance.arrive).append(" ").append(attendance.leave);
                    sb.append("\n");
                }

                mTxtHistory.setText(sb.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * 出社時間の追加
     * 同日に２回呼び出した場合は上書きする
     *
     * @param date   年月日
     * @param arrive 出社時刻
     */
    private void add(String date, String arrive) {

        Attendance attendance = new Attendance();
        attendance.arrive = arrive;
        attendance.leave = "";

        DatabaseReference reference = database.getReference("attendance" + "/" + date);
        reference.setValue(attendance);
    }

    /**
     * 退社時間の更新
     *
     * @param date  年月日
     * @param leave 退社時刻
     */
    private void update(String date, String leave) {


        Map<String, Object> map = new HashMap<>();
        map.put("leave", leave);

        DatabaseReference reference = database.getReference("attendance" + "/" + date);
        reference.updateChildren(map);
    }

    /**
     * 現在時刻(HH:mm)を取得する
     *
     * @return 現在時刻(HH:mm)
     */
    private String getTime() {

        CharSequence charSequence = android.text.format.DateFormat.format("HH:mm", Calendar.getInstance());
        return charSequence.toString();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (R.id.btnArrive == id) {

            add(getDate(), getTime());
        } else {

            update(getDate(), getTime());
        }

    }
}
