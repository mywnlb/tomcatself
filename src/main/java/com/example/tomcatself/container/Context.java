package com.example.tomcatself.container;

import com.example.tomcatself.container.instance.InstanceManager;

public interface Context {
    /**
     * @return the instance manager associated with this context.
     */
    public InstanceManager getInstanceManager();

    /**
     * Set the instance manager associated with this context.
     *
     * @param instanceManager the new instance manager instance
     */
    public void setInstanceManager(InstanceManager instanceManager);
}
