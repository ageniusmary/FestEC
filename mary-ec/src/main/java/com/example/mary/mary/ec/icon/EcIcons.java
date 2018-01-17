package com.example.mary.mary.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.ec.icon
 * 创建者：   Mary
 * 创建时间:  2018/1/17 11:41
 * 描述：     TODO
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue60f'),
    icon_alipay('\ue67e');

    private char character;

    //构造方法
    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
