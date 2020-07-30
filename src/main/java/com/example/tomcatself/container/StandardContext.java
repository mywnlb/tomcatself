package com.example.tomcatself.container;

import com.example.tomcatself.container.instance.InstanceManager;

public class StandardContext implements Context{
    private InstanceManager instanceManager = null;

    @Override
    public InstanceManager getInstanceManager() {
        return instanceManager;
    }


    @Override
    public void setInstanceManager(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }
}
