package cn.zhangyis.tomcaself.ch1.life;

/**
 * @description
 * @author: sksa
 * @create: 2020-07-30 17:24
 **/
public interface Lifecycle {
    public static final String START_EVENT = "start";
    public static final String BEFORE_START_EVENT = "before_start";
    public static final String AFTER_START_EVENT = "after_start";
    public static final String STOP_EVENT = "stop";
    public static final String BEFORE_STOP_EVENT = "before_stop";
    public static final String AFTER_STOP_EVENT = "after_stop";
    public void addLifecycleListener(LifecycleListener listener);
    public LifecycleListener[] findLifecycleListeners();
    public void removeLifecycleListener(LifecycleListener listener);
    public void start() ;
    public void stop() ;

}
