package com.sty.music.player.structure.dagger;

import com.sty.music.player.structure.MainActivity;
import com.sty.music.player.structure.presenter.GirlPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Author: ShiTianyi
 * Time: 2021/9/6 0006 20:38
 * Description:
 */
@Module
public class GirlPresenterModule {
    private MainActivity mView;

    public GirlPresenterModule(MainActivity mView) {
        this.mView = mView;
    }

    //对外暴露的就是P层
    @Provides
    public GirlPresenter providerGirlPresenter() {
        return new GirlPresenter(mView);
    }
}
