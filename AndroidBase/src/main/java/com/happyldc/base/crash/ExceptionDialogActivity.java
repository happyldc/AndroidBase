package com.happyldc.base.crash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.happyldc.base.R;


/**
 *
 */
public class ExceptionDialogActivity extends AppCompatActivity implements View.OnClickListener {
    final static String CRASH_SHOW_DETAIL_ACTION = "com.happyldc.crash.action.showDetail";

    final static String CRASH_EXTRA_KEY_APPNAME = "crash_extra_key_appname";

    final static String CRASH_EXTRA_KEY_LOGCONTENT = "crash_extra_key_log_content";

    private TextView mTvMsg;
    /**
     * 关闭程序
     */
    private Button mBtnExit;
    /**
     * 查看详情
     */
    private Button mBtnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exception_dialog);

        initView();
        if (getIntent() == null) {
            finish();
            return;
        }
        if (getIntent().hasExtra(CRASH_EXTRA_KEY_APPNAME)) {
            String appName = getIntent().getStringExtra(CRASH_EXTRA_KEY_APPNAME);
            if (appName == null || appName.length() <= 0) {
                mTvMsg.setText("应用程序已停止运行");
            } else {
                mTvMsg.setText(appName + "已停止运行");
            }
        }
        if (getIntent().hasExtra(CRASH_EXTRA_KEY_LOGCONTENT)) {
            String stringExtra = getIntent().getStringExtra(CRASH_EXTRA_KEY_LOGCONTENT);
            if (TextUtils.isEmpty(stringExtra)) {
                finish();
                return;
            }
        }

    }


    private void initView() {
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mBtnExit = (Button) findViewById(R.id.btn_exit);
        mBtnExit.setOnClickListener(this);
        mBtnDetail = (Button) findViewById(R.id.btn_detail);
        mBtnDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.btn_exit == v.getId()) {
            finish();
        } else if (R.id.btn_detail == v.getId()) {
            Intent intent = new Intent(CRASH_SHOW_DETAIL_ACTION);
            intent.putExtra(CRASH_EXTRA_KEY_APPNAME, getIntent().getStringExtra(CRASH_EXTRA_KEY_APPNAME));
            intent.putExtra(CRASH_EXTRA_KEY_LOGCONTENT, getIntent().getStringExtra(CRASH_EXTRA_KEY_LOGCONTENT));

            startActivity(intent);
            finish();
        }
    }
}
