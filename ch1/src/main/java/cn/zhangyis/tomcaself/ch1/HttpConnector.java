package cn.zhangyis.tomcaself.ch1;

import cn.zhangyis.tomcaself.ch1.life.Lifecycle;
import cn.zhangyis.tomcaself.ch1.life.LifecycleListener;

import java.util.Vector;

/**
 * @description
 * @author: sksa
 * @create: 2020-07-30 15:49
 **/
public class HttpConnector implements Lifecycle,Runnable{
    private String address = null;

    /**
     * The Container used for processing requests received by this Connector.
     */
    protected Container container = null;

    protected LifecycleSupport lifecycle = new LifecycleSupport(this);


    /**
     * The set of processors that have ever been created.
     */
    private Vector created = new Vector();

    @Override
    public void run() {

    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycle.addLifecycleListener(listener);

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
