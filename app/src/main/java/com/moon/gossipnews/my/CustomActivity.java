package com.moon.gossipnews.my;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.moon.gossipnews.R;

import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class CustomActivity extends Activity {
    private Button btn_code, btn_commit;
    private EditText et_phone, et_code;
    private String mPhone;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_code = (Button) findViewById(R.id.btn_getCode);
        et_code = (EditText) findViewById(R.id.et_code);
        btn_commit = (Button) findViewById(R.id.btn_commit);



        SMSSDK.initSDK(this, "153056c521c82", "b8a671c4843ad0f05b09ed902aec18e1");

        btn_code.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mPhone = et_phone.getText().toString().trim();
                SMSSDK.getVerificationCode("86", mPhone);

            }
        });



        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = et_code.getText().toString();
                SMSSDK.submitVerificationCode("86", mPhone, code);
            }
        });

        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    HashMap<String, Object> map = (HashMap<String, Object>) data;
                    String country = (String) map.get("country");
                    String phone = (String) map.get("phone");

                }


            } else if (result == SMSSDK.RESULT_ERROR) {


                try {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    int status = object.optInt("status");
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        Toast.makeText(CustomActivity.this, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {

                }
            }
        }
    };
}
