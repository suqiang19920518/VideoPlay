package com.thinkive.bank.videoplay.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

/**
 * @author: sq
 * @date: 2017/9/28
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: Bitmap缩略图采样
 */
public class BitmapThumbnailHelper {


    /**
     * 对图片进行二次采样，生成缩略图。防止加载过大图片出现内存溢出
     *
     * @param data
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap createThumbnail(byte[] data, int newWidth, int newHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;

        // Log.i("Helper", "--->oldWidth:" + oldWidth);
        // Log.i("Helper", "--->oldHeight:" + oldHeight);

        int ratioWidth = 0;
        int ratioHeight = 0;

        if (newWidth != 0 && newHeight == 0) {
            ratioWidth = oldWidth / newWidth;
            options.inSampleSize = ratioWidth;
            // Log.i("Helper", "--->ratioWidth:" + ratioWidth);

        } else if (newWidth == 0 && newHeight != 0) {
            ratioHeight = oldHeight / newHeight;
            options.inSampleSize = ratioHeight;
        } else {
            ratioHeight = oldHeight / newHeight;
            ratioWidth = oldWidth / newWidth;
            options.inSampleSize = ratioHeight > ratioWidth ? ratioHeight
                    : ratioWidth;
        }
        options.inPreferredConfig = Config.ALPHA_8;
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory
                .decodeByteArray(data, 0, data.length, options);
        return bm;
    }

    /**
     * 对图片进行二次采样，生成缩略图。防止加载过大图片出现内存溢出
     *
     * @param pathName
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap createThumbnail(String pathName, int newWidth,
                                         int newHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;

        int ratioWidth = 0;
        int ratioHeight = 0;

        if (newWidth != 0 && newHeight == 0) {
            ratioWidth = oldWidth / newWidth;
            options.inSampleSize = ratioWidth;
        } else if (newWidth == 0 && newHeight != 0) {
            ratioHeight = oldHeight / newHeight;
            options.inSampleSize = ratioHeight;
        } else {
            ratioHeight = oldHeight / newHeight;
            ratioWidth = oldWidth / newWidth;
            options.inSampleSize = ratioHeight > ratioWidth ? ratioHeight
                    : ratioWidth;
        }
        options.inPreferredConfig = Config.ALPHA_8;
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(pathName, options);
        return bm;
    }


    /**
     * 获取视频文件的典型帧，作为封面
     *
     * @param filePath
     * @return
     */
    public static Bitmap createVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (Exception ex) {
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
            }
        }
        return bitmap;
    }

    //

    /**
     * 获取音乐文件中内置的专辑图片，作为封面
     *
     * @param filePath
     * @return
     */
    public static Bitmap createAlbumThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            byte[] art = retriever.getEmbeddedPicture();
            bitmap = BitmapFactory.decodeByteArray(art, 0, art.length);
        } catch (Exception ex) {
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
            }
        }
        return bitmap;
    }
}
