package com.thinkive.bank.videoplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sq
 * @date: 2017/8/29
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 基础Adapter, 适用于ListView, GridView等AbsListView的子类, <T> 数据模型(model/JavaBean)类
 * 出于性能考虑，里面很多方法对变量(比如list)都没有判断，应在adapter外判断
 */
public abstract class LVBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected Resources resources;
    protected LayoutInflater inflater;

    private int layoutId;
    protected List<T> data;//数据源

    public LVBaseAdapter(Context context, List<T> data, int layoutId) {
        this.data = data;
        this.context = context;
        this.layoutId = layoutId;
        resources = context.getResources();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<T> getData() {
        return data;
    }

    /**
     * 刷新列表
     *
     * @param data
     */
    public synchronized void refresh(List<T> data) {
        this.data = data == null ? null : new ArrayList<>(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getInstance(convertView, inflater, layoutId);

        initialHolder(holder, getItem(position), position);

        return holder.mConvertView;
    }

    public abstract void initialHolder(ViewHolder holder, T item, int position);

    protected static class ViewHolder {
        SparseArray<View> mViews;
        View mConvertView;

        public ViewHolder(View convertView) {
            mViews = new SparseArray<>();
            mConvertView = convertView;
        }

        public static ViewHolder getInstance(View convertView, LayoutInflater mInflater, int layoutId) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(layoutId, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return holder;
        }

        public void setText(int id, String text) {
            TextView textView = getView(id);
            textView.setText(text);
        }

        public <V extends View> V getView(int id) {
            View view = mViews.get(id);

            if (view == null) {
                view = mConvertView.findViewById(id);
                mViews.put(id, view);
            }
            return (V) view;
        }
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     */
    public void startActivity(Class<?> activity) {
        final Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     * @param flag     启动模式
     */
    public void startActivity(Class<?> activity, int flag) {
        final Intent intent = new Intent(context, activity);
        intent.addFlags(flag);
        context.startActivity(intent);
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     * @param bundle   需要传的参数
     */
    public void startActivity(Class<?> activity, Bundle bundle) {
        final Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 使用class来启动一个Activity
     *
     * @param activity 需要启动的Activity的.class实例
     * @param bundle   需要传的参数
     * @param flag     启动模式
     */
    public void startActivity(Class<?> activity, Bundle bundle, int flag) {
        final Intent intent = new Intent(context, activity);
        intent.addFlags(flag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
