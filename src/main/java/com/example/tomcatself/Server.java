package com.example.tomcatself;

import com.example.tomcatself.base.life.Lifecycle;

import java.util.concurrent.ScheduledExecutorService;

public interface Server extends Lifecycle {
    public int getPort();
    public void setPort(int port);

    public int getPortOffset();
    public void setPortOffset(int portOffset);

    public int getPortWithOffset();

    public String getAddress();
    public void setAddress(String address);

    public String getShutdown();
    public void setShutdown(String shutdown);

    public ClassLoader getParentClassLoader();
    public void setParentClassLoader(ClassLoader parent);

    public void addService(Service service);
    public void await();
    public Service findService(String name);
    public Service[] findServices();
    public void removeService(Service service);

    public ScheduledExecutorService getUtilityExecutor();
}
