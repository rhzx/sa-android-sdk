/**Created by wangzhuozhou on 2015/08/01.
 * Copyright © 2015－2018 Sensors Data Inc. All rights reserved. */
 
package com.sensorsdata.analytics.android.demo;

import android.app.Application;
import android.text.TextUtils;

import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentSuperProperties;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 王灼洲 on 2016/11/12
 */

public class MyApplication extends Application {
    /**
     * Sensors Analytics 采集数据的地址
     */

    private final static String WS_SERVER_URL = "http://39.98.54.111/v1/dcslbiart00000gwngvpqkrcn_2u3z/events.svc";
   // private final static String WS_SERVER_URL = "https://dc.xwg.cc/v1/dcslbiart00000gwngvpqkrcn_2u3z/events.svc";

    @Override
    public void onCreate() {
        super.onCreate();
        initSensorsDataAPI();
    }

    /**
     * 初始化 Sensors Analytics SDK
     */
    private void initSensorsDataAPI() {
        SensorsDataAPI.sharedInstance(
                this,                               // 传入 Context
                WS_SERVER_URL,
                SensorsDataAPI.DebugMode.DEBUG_OFF);                     // Debug 模式选项
        // 打开自动采集, 并指定追踪哪些 AutoTrack 事件
        List<SensorsDataAPI.AutoTrackEventType> eventTypeList = new ArrayList<>();
        // $AppStart
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START);
        // $AppEnd
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END);
        // $AppViewScreen
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN);
        // $AppClick
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK);
        SensorsDataAPI.sharedInstance().enableAutoTrack(eventTypeList);
        SensorsDataAPI.sharedInstance().trackAppCrash();
        try {
                JSONObject properites = new JSONObject();
                String downloadChannel = null;
                // 获取下载商店的渠道
                downloadChannel = SensorsDataUtils.getApplicationMetaData(this, "YOUR_DOWNLOAD_CHANNEL");
                properites.put("DownloadChannel",downloadChannel);
                SensorsDataAPI.sharedInstance().registerSuperProperties(properites);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
