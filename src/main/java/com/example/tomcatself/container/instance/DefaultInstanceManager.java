package com.example.tomcatself.container.instance;

public class DefaultInstanceManager implements InstanceManager{
    @Override
    public Object newInstance(Class<?> clazz) {
        return null;
    }

    @Override
    public Object newInstance(String className) {
        return null;
    }

    @Override
    public Object newInstance(String fqcn, ClassLoader classLoader) {
        return null;
    }

    @Override
    public void newInstance(Object o) {

    }

    @Override
    public void destoryInstance(Object o) {

    }
}
