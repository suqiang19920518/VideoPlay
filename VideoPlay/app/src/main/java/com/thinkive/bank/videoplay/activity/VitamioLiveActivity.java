package com.thinkive.bank.videoplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thinkive.bank.videoplay.R;

/**
 * @author: sq
 * @date: 2017/9/28
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: Vitamio第三方框架，播放直播视频
 */
public class VitamioLiveActivity extends AppCompatActivity {

    /**
     * 测试直播流的地址
     * RTMP:rtmp://live.hkstv.hk.lxdns.com/live/hks
     * m3u8: http://live.3gv.ifeng.com/zixun.m3u8
     */
    private String[] mVideoLiveTestPath = new String[]{
            "rtmp://live.hkstv.hk.lxdns.com/live/hks", "http://live.3gv.ifeng.com/zixun.m3u8"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio_live);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, VitamioLivePlayActivity.class);
        switch (view.getId()) {
            case R.id.tv_vitamio_live_rtmp:
                intent.putExtra(VitamioLivePlayActivity.VIDEO_PATH, mVideoLiveTestPath[0]);
                startActivity(intent);
                break;
            case R.id.tv_vitamio_live_m3u8:
                intent.putExtra(VitamioLivePlayActivity.VIDEO_PATH, mVideoLiveTestPath[1]);
                startActivity(intent);
                break;
        }
    }
}
