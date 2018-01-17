package com.example.mary.festec;

import android.app.Application;

import com.example.mary.mary.app.Mary;
import com.example.mary.mary.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.festec
 * 创建者：   Mary
 * 创建时间:  2018/1/16 14:47
 * 描述：     TODO
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mary.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApihost("http://127.0.0.1/")
                .configure();
    }
}
