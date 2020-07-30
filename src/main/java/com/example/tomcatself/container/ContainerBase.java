package com.example.tomcatself.container;

import com.example.tomcatself.base.life.LifecycleBase;
import com.example.tomcatself.container.listen.ContainerEvent;
import com.example.tomcatself.container.listen.ContainerListener;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * 容器基础功能类
 */
public abstract class ContainerBase extends LifecycleBase implements Container{
    /**
     * 子容器的关联
     */
    protected final HashMap<String, Container> children = new HashMap<>();
    protected ScheduledFuture<?> backgroundProcessorFuture;
    protected ScheduledFuture<?> monitorFuture;
    protected final List<ContainerListener> listeners = new CopyOnWriteArrayList<>();
    protected String name = null;
    protected Container parent = null;
    protected ClassLoader parentClassLoader = null;
    protected final Pipeline pipeline = new StandardPipeline(this);
    protected boolean startChildren = true;
    private int startStopThreads = 1;
    protected ExecutorService startStopExecutor;

    @Override
    public Container getParent() {
        return parent;
    }
    @Override
    public void setParent(Container container) {

        Container oldParent = this.parent;
        this.parent = container;
    }

    @Override
    public int getStartStopThreads() {
        return startStopThreads;
    }

    @Override
    public void setStartStopThreads(int startStopThreads) {
        int oldstartStopThreads = this.startStopThreads;
        this.startStopThreads = startStopThreads;
        
        if(oldstartStopThreads != startStopThreads && startStopExecutor != null){
            reconfigureStartStopExecutor(getStartStopThreads());
        }
    }

    private void reconfigureStartStopExecutor(int startStopThreads) {
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 子容器是否自动启动
     * @return
     */
    public boolean getStartChildren() {
        return startChildren;
    }


    @Override
    public void fireContainerEvent(String type, Object data) {
        if(listeners.size()<1){
            return;
        }

        ContainerEvent event = new ContainerEvent(this,type,data);
        for (ContainerListener listener : listeners) {
            listener.containerEvent(event);
        }
    }
}
