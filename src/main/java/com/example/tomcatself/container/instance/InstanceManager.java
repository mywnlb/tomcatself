package com.example.tomcatself.container.instance;

public interface InstanceManager {
    Object newInstance(Class<?> clazz);
    Object newInstance(String className);
    Object newInstance(String fqcn, ClassLoader classLoader);

    void newInstance(Object o);
    void destoryInstance(Object o);
}
