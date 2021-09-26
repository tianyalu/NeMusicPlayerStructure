package com.sty.music.player.structure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.sty.core.network.rx.databus.RxBus;
import com.sty.music.player.structure.adapter.GirlAdapter;
import com.sty.music.player.structure.bean.Girl;
import com.sty.music.player.structure.dagger.DaggerGirlComponent;
import com.sty.music.player.structure.dagger.GirlPresenterModule;
import com.sty.music.player.structure.presenter.GirlPresenter;
import com.sty.music.player.structure.view.IGirlView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IGirlView {
    private ListView listView;

    // 定义一个P层
    @Inject
    GirlPresenter girlPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv);

        // 简写
        // DaggerGirlComponent.builder().build().inject(this);
        // DaggerGirlComponent.create().inject(this);

        DaggerGirlComponent.builder().
                girlPresenterModule(new GirlPresenterModule(this)).
                build().inject(this);

        // girlPresenter == 有值

        // 注册总线（紫色 桥梁）
        RxBus.getInstance().register(girlPresenter); // 目标对象 +1
    }

    @Override
    public void showGirlDate(List<Girl> girls) {
        listView.setAdapter(new GirlAdapter(this, girls));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 加载数据
        girlPresenter.loadDataAction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 移除总线
        RxBus.getInstance().unRegister(girlPresenter);
    }
}