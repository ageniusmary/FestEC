package com.example.mary.mary.util.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.util.timer
 * 创建者：   Mary
 * 创建时间:  2018/2/6 15:44
 * 描述：     TODO
 */

public class BaseTimerTask extends TimerTask {


    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
