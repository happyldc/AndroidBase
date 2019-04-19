package com.happyldc.base.baseadapter.list;

import android.content.Context;
import android.view.View;

import com.happyldc.base.baseadapter.ViewHolderHelp;


/**
 * <p>
 *
 * @author lindiancheng
 * @version 1.0
 * @date: 2017年8月8日
 */
public class LyBaseViewHolder {
    private Object tag;
    ViewHolderHelp help;
    public ViewHolderHelp get() {
        return help;
    }
    public LyBaseViewHolder(Context context, View view) {
        help = new ViewHolderHelp(context, view);
    }

    public int getPosition() {
        return help.getPosition();
    }

    public void setPosition(int position) {
        help.setPosition(position);
    }



    public View getView() {
        return help.getRootView();
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

}
