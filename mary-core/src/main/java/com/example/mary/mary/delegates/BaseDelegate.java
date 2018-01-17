package com.example.mary.mary.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.delegates
 * 创建者：   Mary
 * 创建时间:  2018/1/17 20:11
 * 描述：     TODO
 */

public abstract class BaseDelegate extends SwipeBackFragment{
    public abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;

        if(setLayout()instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if(setLayout() instanceof View){
            rootView = (View) setLayout();
        }

        if(rootView != null){

        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
