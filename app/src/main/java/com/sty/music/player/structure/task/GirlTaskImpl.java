package com.sty.music.player.structure.task;

import com.sty.core.network.rx.RxRequestClient;
import com.sty.core.network.rx.databus.RxBus;
import com.sty.music.player.structure.R;
import com.sty.music.player.structure.bean.Girl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Author: ShiTianyi
 * Time: 2021/9/6 0006 20:48
 * Description:
 */
public class GirlTaskImpl implements IGirlTask{

    @Override
    public void loadGirlData() {
        // 调用到核心层 core
        RxBus.getInstance().doProcessInvoke(new Function() {
            @Override
            public Object apply(Object o) throws Exception {

                // 模拟数据
                List<Girl> data = new ArrayList<>();

                data.add(new Girl(R.drawable.f1, "3颗星", "11111111111111111"));
                data.add(new Girl(R.drawable.f2, "2颗星", "56754757546756567"));
                data.add(new Girl(R.drawable.f3, "5颗星", "534534534534345"));
                data.add(new Girl(R.drawable.f4, "4颗星", "75675756756567"));
                data.add(new Girl(R.drawable.f5, "7颗星", "86586797789769"));
                data.add(new Girl(R.drawable.f7, "5颗星", "346365463463465"));
                data.add(new Girl(R.drawable.f6, "4颗星", "25433253453465"));
                data.add(new Girl(R.drawable.f8, "9颗星", "34675475686875"));
                data.add(new Girl(R.drawable.f9, "3颗星", "3463675688679"));
                data.add(new Girl(R.drawable.f10, "0颗星", "5475476586796789769"));

                return data;
            }
        });
    }

    @Override
    public void testData() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", "liudeli");
        param.put("pwd", "13454");

        // 调用到核心层 core
        RxBus.getInstance().doProcessInvoke(

                // 真实的访问网络
                RxRequestClient.create()
                        .url("user/info/query")
                        .error(null)
                        .success(null)
                        .request(null)
                        .param(param)
                        .build()
                        .post()

        );
    }
}
