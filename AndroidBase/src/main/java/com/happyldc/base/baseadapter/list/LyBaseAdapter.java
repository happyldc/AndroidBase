package com.happyldc.base.baseadapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.happyldc.base.baseadapter.ViewHolderHelp;

import java.util.List;

/**
 * <p>
 *
 * @author lindiancheng
 * @version 1.0
 * @date: 2017年8月8日
 */
public abstract class LyBaseAdapter<T> extends BaseAdapter {
    protected List<T> mLists;
    protected Context mContext;

    public LyBaseAdapter(Context context, List<T> lists) {
        super();
        this.mLists = lists;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract int getLayoutId();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LyBaseViewHolder holder;
        if (convertView == null) {
            holder = createViewHolder(getLayoutId(), convertView, parent);
            holder.get().setContext(mContext);
            convertView = holder.getView();
            convertView.setTag(holder);
        } else {
            holder = (LyBaseViewHolder) convertView.getTag();
        }
        holder.setTag(position);
        holder.setPosition(position);
        bindData(holder.get(), mLists.get(position));

        return convertView;
    }

    /**
     * 便于扩展使用 需要穿多个参数时重新本方法 调用holder.bindData（T t,..）
     * * @param holder
     *
     * @param data
     */
    protected abstract void bindData(ViewHolderHelp holder, T data);

    protected LyBaseViewHolder createViewHolder(int layoutId, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), parent, false);
        return new LyBaseViewHolder(mContext,view);
    }

}
