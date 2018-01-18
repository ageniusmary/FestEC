package com.example.mary.mary.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.mary.mary.R;
import com.example.mary.mary.delegates.MaryDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.activities
 * 创建者：   Mary
 * 创建时间:  2018/1/17 20:08
 * 描述：     程序唯一的activity  做为容器来使用
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract MaryDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    public void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container= new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
    }
}
