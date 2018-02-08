package com.example.mary.mary.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.example.mary.mary.delegates.MaryDelegate;
import com.example.mary.mary.ec.R;
import com.example.mary.mary.ec.R2;
import com.example.mary.mary.ui.launcher.ScrollLauncherTag;
import com.example.mary.mary.util.storage.MaryPerference;
import com.example.mary.mary.util.timer.BaseTimerTask;
import com.example.mary.mary.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.ec.launcher
 * 创建者：   Mary
 * 创建时间:  2018/2/4 16:24
 * 描述：     TODO
 */

public class launcherDelegate extends MaryDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    private int mCount = 5;

    private Timer mTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScorll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScorll(){
        if(!MaryPerference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检查用户是否登陆了APP
        }
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScorll();
                        }
                    }
                }
            }
        });
    }
}
