package com.example.tomcatself.container.wrapper;

import com.example.tomcatself.container.Container;
import com.example.tomcatself.container.ContainerBase;
import com.example.tomcatself.container.StandardContext;
import com.example.tomcatself.container.instance.InstanceManager;
import com.example.tomcatself.container.wrapper.Wrapper;

import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationEmitter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StandardWrapper extends ContainerBase implements ServletConfig, Wrapper, NotificationEmitter {

    protected static final String[] DEFAULT_SERVLET_METHODS = new String[] {
            "GET", "HEAD", "POST" };

    public StandardWrapper() {

        super();
        swValve=new StandardWrapperValve();
        pipeline.setBasic(swValve);
        broadcaster = new NotificationBroadcasterSupport();

    }

    protected final NotificationBroadcasterSupport broadcaster;

    /**
     * 创建了多少个实例
     */
    protected final AtomicInteger countAllocated = new AtomicInteger(0);


    /**
     * 单个实例
     */
    protected volatile Servlet instance = null;
    protected volatile boolean instanceInitialized = false;
    protected final StandardWrapperFacade facade = new StandardWrapperFacade(this);

    protected int loadOnStartup = -1;
    protected final ArrayList<String> mappings = new ArrayList<>();
    protected HashMap<String, String> parameters = new HashMap<>();
    protected String servletClass = null;

    /**
     *  是否实现了SingleThreadModel
     */
    protected volatile boolean singleThreadModel = false;

    /**
     * 是否正在卸载
     */
    protected volatile boolean unloading = false;
    protected int maxInstances = 20;
    protected int nInstances = 0;

    /**
     * 创建servlet的池
     */
    protected Stack<Servlet> instancePool = null;
    protected boolean isJspServlet;
    protected StandardWrapperValve swValve;
    /**
     * Async support
     */
    protected boolean asyncSupported = false;
    /**
     * Enabled
     */
    protected boolean enabled = true;

    @Override
    public void setParent(Container container) {
        super.setParent(container);
    }
    @Override
    public String getServletClass() {
        return this.servletClass;
    }

    @Override
    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public void setServletName(String name) {
        setName(name);
    }

    public Boolean isSingleThreadModel() {
        // If the servlet has been loaded either singleThreadModel will be true
        // or instance will be non-null
        if (singleThreadModel || instance != null) {
            return Boolean.valueOf(singleThreadModel);
        }
        return null;
    }

    @Override
    public String[] getServletMethods() throws ServletException {
        instance = loadServlet();
        Class<? extends Servlet> servletClazz = instance.getClass();
        Set<String> allow = new HashSet<>();
        allow.add("OPTIONS");
        allow.add("TRACE");
        Method[] methods = getAllDeclaredMethods(servletClazz);
        for (int i=0; methods != null && i<methods.length; i++) {
            Method m = methods[i];

            if (m.getName().equals("doGet")) {
                allow.add("GET");
                allow.add("HEAD");
            } else if (m.getName().equals("doPost")) {
                allow.add("POST");
            } else if (m.getName().equals("doPut")) {
                allow.add("PUT");
            } else if (m.getName().equals("doDelete")) {
                allow.add("DELETE");
            }
        }
        return new String[0];
    }

    private Method[] getAllDeclaredMethods(Class<? extends Servlet> servletClazz) {
    }

    @Override
    public Servlet getServlet() {
        return instance;
    }


    /**
     * Set the associated servlet instance.
     */
    @Override
    public void setServlet(Servlet servlet) {
        instance = servlet;
    }


    /**
     * 创建servlet
     * @return
     */
    private synchronized Servlet loadServlet() {
        if (!singleThreadModel && (instance != null))
            return instance;
        Servlet servlet = null;

        InstanceManager instanceManager = ((StandardContext)getParent()).getInstanceManager();

        servlet = (Servlet) instanceManager.newInstance(servletClass);

        if (servlet instanceof SingleThreadModel) {
            if (instancePool == null) {
                instancePool = new Stack<>();
            }
            singleThreadModel = true;
        }

        initServlet(servlet);

        //发布事件
        fireContainerEvent("load", this);

        return servlet;
    }

    /**
     * 初始化servlet
     * @param servlet
     */
    private synchronized void initServlet(Servlet servlet) {
        if (instanceInitialized && !singleThreadModel) return;

        try {
            servlet.init(facade);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
