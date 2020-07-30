package com.example.tomcatself.base.life;

import com.example.tomcatself.base.logging.Log;
import com.example.tomcatself.base.logging.LogFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 生命接口基本实现，抽象了各个接口，添加了事件的发布
 */
public abstract class LifecycleBase implements Lifecycle{
    private static final Log log = LogFactory.getLog(LifecycleBase.class);

    private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();
    private volatile LifecycleState state = LifecycleState.NEW;

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.add(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycleListeners.toArray(new LifecycleListener[0]);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }

    /**
     * 发布事件
     * @param type       LifecycleState
     * @param data       数据
     */
    protected void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(this, type, data);
        for (LifecycleListener listener : lifecycleListeners) {
            listener.lifecycleEvent(event);
        }
    }

    /**
     * 添加抽象
     * @throws LifecycleException
     */
    @Override
    public void init() throws LifecycleException {
        setStateInternal(LifecycleState.INITIALIZING, null, false);
        initInternal();
        setStateInternal(LifecycleState.INITIALIZED, null, false);
    }

    private synchronized void setStateInternal(LifecycleState state, Object data, boolean check) {
        this.state = state;
        String lifecycleEvent = state.getLifecycleEvent();
        if (lifecycleEvent != null) {
            fireLifecycleEvent(lifecycleEvent, data);
        }
    }

    /**
     * 继承类实现
     * @throws LifecycleException
     */
    protected abstract void initInternal() throws LifecycleException;

    @Override
    public void start() throws LifecycleException {

        if (state.equals(LifecycleState.NEW)) {
            init();
        }

        try {
            setStateInternal(LifecycleState.STARTING_PREP, null, false);
            startInternal();
            setStateInternal(LifecycleState.STARTED, null, false);
        } catch (Throwable t) {
            
        }
    }

    protected abstract void startInternal();

    @Override
    public void stop() throws LifecycleException {
        setStateInternal(LifecycleState.STOPPING_PREP, null, false);
        stopInternal();
        setStateInternal(LifecycleState.STOPPED, null, false);
    }

    protected abstract void stopInternal();

    @Override
    public void destroy() throws LifecycleException {
        if (LifecycleState.FAILED.equals(state)) {
            stop();
        }
        setStateInternal(LifecycleState.DESTROYING, null, false);
        destroyInternal();
        setStateInternal(LifecycleState.DESTROYED, null, false);
    }

    protected abstract void destroyInternal();

    @Override
    public LifecycleState getState() {
        return state;
    }

    @Override
    public String getStateName() {
        return getState().toString();
    }
}
