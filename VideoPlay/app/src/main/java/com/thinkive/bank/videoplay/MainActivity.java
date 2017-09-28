package com.thinkive.bank.videoplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thinkive.bank.videoplay.activity.MediaPlayerLocalActivity;
import com.thinkive.bank.videoplay.activity.MediaPlayerUrlActivity;
import com.thinkive.bank.videoplay.activity.VideoViewActivity;
import com.thinkive.bank.videoplay.activity.VitamioLiveActivity;
import com.thinkive.bank.videoplay.activity.VitamioUrlActivity;
import com.thinkive.bank.videoplay.util.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    private static final String FLAG_MEDIAPLAYER = "mediaPlayer";
    private static final String FLAG_VIDEOVIEW = "videoView";
    private String flag;

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_MULTI_PERMISSION:
                    if (flag.equals(FLAG_MEDIAPLAYER)) {
                        startActivity(new Intent(MainActivity.this, MediaPlayerLocalActivity.class));
                    } else if (flag.equals(FLAG_VIDEOVIEW)) {
                        startActivity(new Intent(MainActivity.this, VideoViewActivity.class));
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mediaPlayer_local:
                flag = FLAG_MEDIAPLAYER;
                //申请读写存储权限
                PermissionUtils.requestMultiPermissions(this, mPermissionGrant, false);
                break;
            case R.id.tv_mediaPlayer_url:
                startActivity(new Intent(this, MediaPlayerUrlActivity.class));
                break;
            case R.id.tv_videoView:
                flag = FLAG_VIDEOVIEW;
                //申请读写存储权限
                PermissionUtils.requestMultiPermissions(this, mPermissionGrant, false);
                break;
            case R.id.tv_vitamio_url:
                startActivity(new Intent(this, VitamioUrlActivity.class));
                break;
            case R.id.tv_vitamio_live:
                startActivity(new Intent(this, VitamioLiveActivity.class));
                break;

        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant, false);

    }
}
