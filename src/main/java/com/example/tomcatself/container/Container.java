package com.example.tomcatself.container;

import com.example.tomcatself.Service;
import com.example.tomcatself.base.life.Lifecycle;
import com.example.tomcatself.container.listen.ContainerListener;

public interface Container extends Lifecycle {
    public static final String ADD_CHILD_EVENT = "addChild";
    public static final String ADD_VALVE_EVENT = "addValve";

    public static final String REMOVE_CHILD_EVENT = "removeChild";
    public static final String REMOVE_VALVE_EVENT = "removeValve";

    /**
     * 管道类似过滤器链
     * @return
     */
    public Pipeline getPipeline();

    public String getName();
    public void setName(String name);

    public Container getParent();
    public void setParent(Container container);

    public ClassLoader getParentClassLoader();
    public void setParentClassLoader(ClassLoader parent);

    /**
     * 容器是嵌套的只有e engine的父容器是Service
     * @param container
     * @return
     */
    public static Service getService(Container container){
        while (container != null && !(container instanceof Engine)){
            container = container.getParent();
        }

        if(container == null){
            return null;
        }

        return ((Engine)container).getService();
    }

    public void addChild(Container child);
    public void addContainerListener(ContainerListener listener);
    public Container findChild(String name);
    public Container[] findChildren();
    public ContainerListener[] findContainerListeners();
    public void removeChild(Container child);
    public void removeContainerListener(ContainerListener listener);
    public void fireContainerEvent(String type, Object data);
    public int getStartStopThreads();
    public void setStartStopThreads(int startStopThreads);

}
