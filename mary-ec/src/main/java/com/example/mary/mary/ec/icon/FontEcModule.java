package com.example.mary.mary.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.ec.icon
 * 创建者：   Mary
 * 创建时间:  2018/1/16 15:22
 * 描述：     TODO
 */

public class FontEcModule implements IconFontDescriptor{
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
