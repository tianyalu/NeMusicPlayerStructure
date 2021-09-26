package com.sty.core.network.rx.databus;

// 负责数据总线

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxBus {

    // 分发事件标记
    private final static String START_RUN = "doProcessInvoke start emitter run";

    private Set<Object> subscribers;

    /**
     * 注册
     */
    public synchronized void register(Object subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * 移除
     */
    public synchronized void unRegister(Object subscriber) {
        subscribers.remove(subscriber);
    }

    private static volatile RxBus instance;

    private RxBus() {
        // 给容器初始化
        subscribers = new CopyOnWriteArraySet<>(); // 稳定的 安全的
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    // TODO 对外暴露API
    public <T, R> void doProcessInvoke(Function function) { // function 提供给外界 网络耗时操作的 (异步)
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(START_RUN);
                emitter.onComplete();
            }
        })
        .map(function)
        .subscribeOn(Schedulers.io()) // 设置 业务层 业务实现层等 异步线程
        .observeOn(AndroidSchedulers.mainThread()) // 给总线设置  android主线程
        .subscribe(new Consumer() {
            @Override
            public void accept(Object data) throws Exception {

                // data == GirlTaskImpl{return data}；

                if (data != null) {
                    sendDataActoin(data);
                }
            }
        });
    }

    // TODO 对外暴露API 2
    public <T, R> void doProcessInvoke(Observable<String> observable) {
        observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String data) throws Exception {
                if (data != null) {
                    sendDataActoin(data);
                }
            }
        });
    }

    // 发生 并 负责扫描 被注册的目标
    public void sendDataActoin(Object data) {
        // 扫描注册进来的对象，所以需要遍历subscribers容器
        // 目前是遍历 P层
        for (Object subscriber : subscribers) { // size=1
            checkSubscriberAnnotationMethod(subscriber, data);
        }
    }

    // 专门总线发射的

    /**
     *
     * @param subscriberTarget 暂时理解是P层
     * @param data 数据
     */
    private void checkSubscriberAnnotationMethod(Object subscriberTarget, Object data) {
        Method[] declaredMethods = subscriberTarget.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.setAccessible(true); // 让虚拟机不要检测private

            RegisterRxBus registerRxBus = method.getAnnotation(RegisterRxBus.class);
            if (registerRxBus != null) {
                // 找到目标了...

                Class<?>[] parameterTypes = method.getParameterTypes();
                String parameterType = parameterTypes[0].getName();

                // 判断目标方法
                if (data.getClass().getName().equals(parameterType)) {
                    try {
                        // 执行目标，把数据丢给目标
                        method.invoke(subscriberTarget, new Object[]{data});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
