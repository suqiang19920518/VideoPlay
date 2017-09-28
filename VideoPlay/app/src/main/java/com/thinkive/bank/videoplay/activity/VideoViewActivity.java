package com.thinkive.bank.videoplay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.thinkive.bank.videoplay.R;
import com.thinkive.bank.videoplay.util.WakeLockUtil;

/**
 * @author: sq
 * @date: 2017/9/27
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: VideoView播放器，结合MediaController，播放视频（本地、在线）
 */
public class VideoViewActivity extends AppCompatActivity {

    //声明视频的本地路径
    private static final String VIDEO_PATH = "/storage/A283-CC03/music/都是缘分惹的祸.mp4";

    //声明视频的网络地址
    private String videoUrl = "http://112.253.22.157/17/z/z/y/u/" +
            "zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/" +
            "D046015255134077DDB3ACA0D7E68D45.flv";

    private VideoView videoView;

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        //初始化VideoView
        videoView = ((VideoView) findViewById(R.id.videoView));

        //设置视频源
        videoView.setVideoPath(VIDEO_PATH);
//        videoView.setVideoURI(Uri.parse(videoUrl));

        //初始化视频控制器(系统自带)
        mediaController = new MediaController(this);

        //VideoView和MediaController双向绑定
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);

        //设置MediaController的左上右下的位置
        mediaController.setPadding(0, 0, 0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WakeLockUtil.getInstance().keepCreenWake(this);
    }

    public void play(View view) {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    public void pause(View view) {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        WakeLockUtil.getInstance().cancleCreenWake();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WakeLockUtil.getInstance().cancleCreenWake();
    }
}
