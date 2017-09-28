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
 * @description:  Vitamio第三方框架，播放在线视频
 */
public class VitamioUrlActivity extends AppCompatActivity {

    /**
     * 测试在线视频地址
     * mp4: http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=480p&line=0&is_gif=0.mp4
     * flv: http://v.cctv.com/flash//jingjibanxiaoshi/2008/09/jingjibanxiaoshi_300_20080919_1.flv
     * avi: http://7xt2pm.com1.z0.glb.clouddn.com/%E7%AC%AC%E5%9B%9B%E8%AF%BE.avi
     */
    private String[] mVideoTestPath = new String[]{
            "http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=480p&line=0&is_gif=0.mp4",
            "http://v.cctv.com/flash//jingjibanxiaoshi/2008/09/jingjibanxiaoshi_300_20080919_1.flv",
            "http://7xt2pm.com1.z0.glb.clouddn.com/%E7%AC%AC%E5%9B%9B%E8%AF%BE.avi"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio_url);
    }


    public void onClick(View view) {
        Intent intent = new Intent(this, VitamioUrlPlayActivity.class);
        switch (view.getId()) {
            case R.id.tv_vitamio_url_mp4:
                intent.putExtra(VitamioUrlPlayActivity.VIDEO_PATH, mVideoTestPath[0]);
                startActivity(intent);
                break;
            case R.id.tv_vitamio_url_flv:
                intent.putExtra(VitamioUrlPlayActivity.VIDEO_PATH, mVideoTestPath[1]);
                startActivity(intent);
                break;
            case R.id.tv_vitamio_url_avi:
                intent.putExtra(VitamioUrlPlayActivity.VIDEO_PATH, mVideoTestPath[2]);
                startActivity(intent);
                break;
        }
    }
}
