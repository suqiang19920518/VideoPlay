package com.thinkive.bank.videoplay.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.thinkive.bank.videoplay.R;
import com.thinkive.bank.videoplay.adapter.VideoAdapter;
import com.thinkive.bank.videoplay.bean.VIdeoItem;
import com.thinkive.bank.videoplay.util.WakeLockUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sq
 * @date: 2017/9/27
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: MediaPlayer播放器，结合SurfaceView，播放在线视频
 */
public class MediaPlayerUrlActivity extends AppCompatActivity {

    private ListView mListView;

    private List<VIdeoItem> list = new ArrayList<>();

    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_url);
        initData();
        mListView = ((ListView) findViewById(R.id.lv_media_player_url));
        adapter = new VideoAdapter(this, list, R.layout.video_item_layout);
        mListView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        WakeLockUtil.getInstance().keepCreenWake(this);
    }

    /**
     * 读取Asset文件中的内容，并封装到Bean类的集合中
     */
    private void initData() {
        //获取AssetManager，读取asset中的文件内容
        AssetManager assets = getAssets();

        InputStream is = null;

        StringBuilder stringBuilder = new StringBuilder();

        try {
            is = assets.open("video1.json");

            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = is.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, buffer.length));
            }

            processJson(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json解析，将数据封装到Bean类集合中
     *
     * @param s
     */
    private void processJson(String s) {
        try {
            JSONObject jo = new JSONObject(s);
            JSONArray data = jo.getJSONObject("data").getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                try {
                    JSONObject group = data.getJSONObject(i).getJSONObject("group");
                    String p360 = group.getJSONObject("360p_video").getJSONArray("url_list").getJSONObject(0).getString("url");
                    String cover = group.getJSONObject("medium_cover").getJSONArray("url_list").getJSONObject(0).getString("url");
                    int video_height = group.getInt("video_height");
                    int video_width = group.getInt("video_width");
                    String content = group.getString("content");
                    list.add(new VIdeoItem(content, cover, video_height, p360, video_width));
                } catch (JSONException e) {
//                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.release();
        WakeLockUtil.getInstance().cancleCreenWake();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.release();
        WakeLockUtil.getInstance().cancleCreenWake();
    }
}
