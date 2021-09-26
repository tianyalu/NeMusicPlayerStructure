package com.sty.music.player.structure.presenter;

import com.sty.core.network.rx.databus.RegisterRxBus;
import com.sty.music.player.structure.bean.Girl;
import com.sty.music.player.structure.task.GirlTaskImpl;
import com.sty.music.player.structure.task.IGirlTask;
import com.sty.music.player.structure.view.IGirlView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Author: ShiTianyi
 * Time: 2021/9/6 0006 20:40
 * Description:
 */
public class GirlPresenter<T extends IGirlView> { // T == IGirlView 或者 IGirlView子类
    //View层的定义
    private WeakReference<T> mView;

    //Task层的定义
    private IGirlTask iGirlTask;

    public GirlPresenter(T mView) {
        this.mView = new WeakReference<T>(mView);

        iGirlTask = new GirlTaskImpl();
    }

    public void loadDataAction() {
        iGirlTask.loadGirlData(); //加载数据
    }

    @RegisterRxBus
    public void showGirlDataAction(ArrayList<Girl> arrayList) {
        // 还需要做很多的事情逻辑
        // ...

        // data数据   流向 流到这里来了

        // 把结果给View
        mView.get().showGirlDate(arrayList);
    }
}
