package com.thinkive.bank.videoplay.adapter;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thinkive.bank.videoplay.R;
import com.thinkive.bank.videoplay.bean.VIdeoItem;

import java.io.IOException;
import java.util.List;

public class VideoAdapter extends LVBaseAdapter<VIdeoItem> {

    //声明媒体播放器
    private MediaPlayer mediaPlayer;

    //此变量用来指示当前的播放item位置
    private int currentPlayPosition = -1;

    public VideoAdapter(Context context, List<VIdeoItem> data, int layoutId) {
        super(context, data, layoutId);

        //初始化播放器
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
    }

    @Override
    public void initialHolder(ViewHolder holder, VIdeoItem item, final int position) {

        SurfaceView surfaceView = holder.getView(R.id.surfaceView_item);
        ImageView imageView = holder.getView(R.id.image_preview);
        holder.setText(R.id.content_list, item.getContent());
        //使用Picasso加载网络图片到ImageView控件上
        Picasso.with(context).load(item.getCoverUrl()).into(imageView);

        //创建LayoutParams对象，动态设置SurfaceView的宽高
        ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
        layoutParams.width = item.getVideoWidth();
        layoutParams.height = item.getVideoHeight();
        //将LayoutParams设置到ImageView和SurfaceView控件上
        imageView.setLayoutParams(layoutParams);
        surfaceView.setLayoutParams(layoutParams);

        //当前播放列表，被用户滑出屏幕后，自动停止播放
        Object tag = imageView.getTag();
        if (tag != null) {
            Integer pos = (Integer) tag;
            if (pos == currentPlayPosition && pos != position) {
                mediaPlayer.stop();
                currentPlayPosition = -1;
            }
        }
        imageView.setTag(position);

        if (currentPlayPosition == position) { //说明用户点击了position对应的item
            //隐藏ImageView,并调用MediaPlayer进行播放
            imageView.setVisibility(View.INVISIBLE);
            surfaceView.setVisibility(View.VISIBLE);

            try {
                //重置MediaPlayer
                mediaPlayer.reset();
                //设置播放位置为当前Item中的SurfaceView
                mediaPlayer.setDisplay(surfaceView.getHolder());
                //设置视频源
                mediaPlayer.setDataSource(context, Uri.parse(item.getVideoUrl()));
                //启动准备工作
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            imageView.setVisibility(View.VISIBLE);
            surfaceView.setVisibility(View.INVISIBLE);
        }

        //设置ImageView的点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断当前是否有视频在播放，如果有则停止MediaPlayer
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                //设置当前的播放位置为position
                currentPlayPosition = position;

                //刷新UI列表
                notifyDataSetChanged();
            }
        });

    }

    public void release() {
        mediaPlayer.release();
    }
}
