package com.sty.music.player.structure.dagger;

import com.sty.music.player.structure.MainActivity;

import dagger.Component;

/**
 * Author: ShiTianyi
 * Time: 2021/9/6 0006 20:37
 * Description:
 */
@Component(modules = GirlPresenterModule.class)
public interface GirlComponent { //注入对象 --》MainActivity
    void inject(MainActivity mainActivity); //注入动作
}
