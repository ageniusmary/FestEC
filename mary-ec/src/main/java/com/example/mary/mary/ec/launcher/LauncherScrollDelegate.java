package com.example.mary.mary.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.mary.mary.delegates.MaryDelegate;
import com.example.mary.mary.ec.R;
import com.example.mary.mary.ui.launcher.ScrollLauncherTag;
import com.example.mary.mary.ui.launcher.launcherHolderCreator;
import com.example.mary.mary.util.storage.MaryPerference;

import java.util.ArrayList;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.ec.launcher
 * 创建者：   Mary
 * 创建时间:  2018/2/7 14:15
 * 描述：     TODO
 */

public class LauncherScrollDelegate extends MaryDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initView() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new launcherHolderCreator(), INTEGERS)
                //轮播图下面的小圆点
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_foucs})
                //小圆点的位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                //可以循环
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            MaryPerference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否登陆了app
        }
    }
}
