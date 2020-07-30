package cn.zhangyis.tomcaself.ch1.life;

/**
 * @description
 * @author: sksa
 * @create: 2020-07-30 17:32
 **/
public class LifecycleSupport {
    /**
     * The source component for lifecycle events that we will fire.
     */
    private Lifecycle lifecycle = null;


    /**
     * The set of registered LifecycleListeners for event notifications.
     */
    private LifecycleListener listeners[] = new LifecycleListener[0];

}
