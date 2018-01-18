package com.example.mary.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.mary.mary.delegates.MaryDelegate;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.festec
 * 创建者：   Mary
 * 创建时间:  2018/1/18 12:38
 * 描述：     TODO
 */

public class ExampleDelegate extends MaryDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
