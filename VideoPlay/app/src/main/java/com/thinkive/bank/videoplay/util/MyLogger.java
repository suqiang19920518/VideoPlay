package com.thinkive.bank.videoplay.util;

import android.util.Log;

import java.util.Formatter;

/**
 * @author: sq
 * @date: 2017/8/4
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 自定义log打印日志
 */
public class MyLogger {

    public static boolean DEBUG = true;//调试阶段，显示log日志
//    public static boolean DEBUG = false;

    public static String DEFAULT_TAG = "asos";

    private static final ThreadLocal<ReusableFormatter> thread_local_formatter = new ThreadLocal<ReusableFormatter>() {
        protected ReusableFormatter initialValue() {
            return new ReusableFormatter();
        }
    };

    /**
     * A little trick to reuse a formatter in the same thread
     */
    private static class ReusableFormatter {

        private Formatter formatter;
        private StringBuilder builder;

        public ReusableFormatter() {
            builder = new StringBuilder();
            formatter = new Formatter(builder);
        }

        public String format(String msg, Object... args) {
            formatter.format(msg, args);
            String s = builder.toString();
            builder.setLength(0);
            return s;
        }
    }

    public static String format(String msg, Object... args) {
        ReusableFormatter formatter = thread_local_formatter.get();
        return formatter.format(msg, args);
    }

    public static void printError(Throwable err, String msg, Object... args) {
        Log.e(DEFAULT_TAG, format(msg, args), err);
    }

    public static void setDebug(boolean isDebug) {
        DEBUG = isDebug;
    }

    public static void d(String msg) {
        if (DEBUG)
            Log.d(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void i(String msg) {
        if (DEBUG)
            Log.i(DEFAULT_TAG, msg);
    }

    public static void w(String msg) {
        if (DEBUG)
            Log.w(DEFAULT_TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG)
            Log.w(tag, msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            Log.e(DEFAULT_TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void v(String msg) {
        if (DEBUG)
            Log.v(DEFAULT_TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, msg);
    }
}
