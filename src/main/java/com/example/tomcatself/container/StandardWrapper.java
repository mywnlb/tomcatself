package com.example.tomcatself.container;

import javax.management.NotificationEmitter;
import javax.servlet.ServletConfig;

public class StandardWrapper extends ContainerBase implements ServletConfig,Wrapper, NotificationEmitter {

    protected static final String[] DEFAULT_SERVLET_METHODS = new String[] {
            "GET", "HEAD", "POST" };
}
