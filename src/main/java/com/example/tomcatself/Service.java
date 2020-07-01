package com.example.tomcatself;

import com.example.tomcatself.base.concurrent.Executor;
import com.example.tomcatself.base.life.Lifecycle;
import com.example.tomcatself.base.mapper.Mapper;
import com.example.tomcatself.connector.Connector;
import com.example.tomcatself.container.Engine;

public interface Service extends Lifecycle {
    public Engine getContainer();
    public void setContainer(Engine engine);

    public String getName();
    public void setName(String name);

    public Server getServer();
    public void setServer(Server server);

    public ClassLoader getParentClassLoader();
    public void setParentClassLoader(ClassLoader parent);

    public void addConnector(Connector connector);
    public Connector[] findConnectors();
    public void removeConnector(Connector connector);

    public void addExecutor(Executor ex);
    public Executor[] findExecutors();
    public Executor getExecutor(String name);
    public void removeExecutor(Executor ex);

    Mapper getMapper();

}
