package com.happyldc.base.impl;

import android.text.Editable;

/**
 * 自动保留小数位
 *
 * @author ldc
 * @Created at 2018/6/11 10:25.
 */

public class TextWatcherReservedDecimalImpl extends TextWatcherSimpleImpl {
    private int reseredCount = 2;//保留小数位 默认保留2位

    /**
     * 默认保留2位小胡
     */
    public TextWatcherReservedDecimalImpl() {
    }

    /**
     * @param reseredCount 保留位数
     */
    public TextWatcherReservedDecimalImpl(int reseredCount) {
        this.reseredCount = reseredCount;
    }
    public void afterTextChanged(Editable edt) {
        String temp = edt.toString();

        int posDot = temp.indexOf(".");
        if (posDot <= 0) return;
        if (temp.length() - posDot - 1 > reseredCount) {
            edt.delete(posDot + reseredCount, temp.length() - 1);
        }
    }
}
