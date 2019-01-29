package com.example.chengsiguo.mocktimestatistics;

import android.os.UserHandle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //实现次数统计的范类
    private SparseIntArray mFingerprintFailedAttempts = new SparseIntArray();//zi ding yi ji he lei ,(key and value are int)
    private static int sCurrentUser = UserHandle.CONTENTS_FILE_DESCRIPTOR;//key
    TextView showCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.start_c);
        Button buttonp = (Button) findViewById(R.id.stop_c);
        button.setOnClickListener(this);
        buttonp.setOnClickListener(this);
        showCounts = (TextView)findViewById(R.id.show_c);
    }


    private int getFingerprintFailedUnlockAttempts() {
        return mFingerprintFailedAttempts.get(sCurrentUser, 0);
    }

    private void clearFingerprintFailedUnlockAttempts() {
        mFingerprintFailedAttempts.delete(sCurrentUser);// key_value
       // Log.d("stopc"," counts: "+getFingerprintFailedUnlockAttempts());
    }

    private void reportFailedFingerprintAuthUnlockAttempt() {
        mFingerprintFailedAttempts.put(sCurrentUser, getFingerprintFailedUnlockAttempts() + 1);//在原来的基础上加1，来更新对应的value
    }

    @Override
    public void onClick(View view) {//mock onFingerprintAuthFailed
        switch(view.getId()) {
            case R.id.start_c:
            //add,show user tip when fingerprint auth failed
                reportFailedFingerprintAuthUnlockAttempt();
                break;
            case R.id.stop_c:
              //  Log.d("stopc"," you click stop");
                clearFingerprintFailedUnlockAttempts();
                break;
        }
        showCounts.setText("counts: "+getFingerprintFailedUnlockAttempts());
    }

}
