package com.example.tomcatself.container;

/**
 * 快速获取关联的容器
 */
public interface Contained {

    Container getContainer();
    void setContainer(Container container);
}
