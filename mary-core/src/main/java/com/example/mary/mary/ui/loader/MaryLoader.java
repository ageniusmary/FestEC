package com.example.mary.mary.ui.loader;

import android.content.Context;

import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.mary.mary.R;
import com.example.mary.mary.ui.loader.LoaderCreator;
import com.example.mary.mary.ui.loader.LoaderStyle;
import com.example.mary.mary.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.ui
 * 创建者：   Mary
 * 创建时间:  2018/1/25 11:56
 * 描述：     TODO
 */

public class MaryLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    //偏移量
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            //位置居中
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    //cancel()跟dismiss()都是关闭   但cancel有回掉，dismiss单纯的关闭   、
                    //若后续有回掉 则建议用cancel
                    dialog.cancel();
                    //dialog.dismiss();
                }
            }
        }
    }
}
