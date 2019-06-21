package com.happyldc.base.crash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.happyldc.base.R;

public class ExceptionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 关闭
     */
    private TextView mTvClose;
    /**
     * 复制
     */
    private TextView mTvCopyInfo;
    private TextView mTvDetail;
    private String outputInfo;
    private Toolbar mToolbar;
    String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_detail);
        initView();
        if (getIntent() == null) {
            finish();
            return;
        }
        if (getIntent().hasExtra(GlobalExceptionHandler.CRASH_EXTRA_KEY_LOGCONTENT)) {
            outputInfo = getIntent().getStringExtra(GlobalExceptionHandler.CRASH_EXTRA_KEY_LOGCONTENT);
            if (outputInfo == null || outputInfo.length() <= 0) {
                finish();
                return;
            }
        }
        if (getIntent().hasExtra(GlobalExceptionHandler.CRASH_EXTRA_KEY_APPNAME)) {
            appName = getIntent().getStringExtra(GlobalExceptionHandler.CRASH_EXTRA_KEY_APPNAME);
            if (appName == null || appName.length() <= 0) {
                finish();
                return;
            }
        }
        mToolbar.setTitle(getString(R.string.exception_detail_title, appName));

        setSupportActionBar(mToolbar);
        mTvDetail.setText(outputInfo);
        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvCopyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText(GlobalExceptionHandler.CRASH_EXTRA_KEY_LOGCONTENT, outputInfo);
                manager.setPrimaryClip(data);
                Toast.makeText(ExceptionDetailActivity.this, getResources().getString(R.string.exception_detail_toast_copy_sucs), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        mTvClose = (TextView) findViewById(R.id.tv_close);
        mTvCopyInfo = (TextView) findViewById(R.id.tv_copy_info);
        mTvDetail = (TextView) findViewById(R.id.tv_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_exception_detail);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
