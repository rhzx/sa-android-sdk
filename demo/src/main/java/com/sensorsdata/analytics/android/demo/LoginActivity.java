package com.sensorsdata.analytics.android.demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.util.JSONUtils;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText user = (EditText)findViewById(R.id.user);
        Button confirm = (Button)findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userName = user.getText().toString();
                try {
                     JSONObject json = new JSONObject();
                    json.put("LoginId",userName);
                    SensorsDataAPI.sharedInstance().registerSuperProperties(json);
                    json.put("si_n","登录");
                    json.put("si_x","登录成功");
                    json.put("dl","30");
                    SensorsDataAPI.sharedInstance().track("loginSuccess",json);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();

            }
        });
    }

}
