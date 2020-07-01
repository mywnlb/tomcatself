package com.example.tomcatself.container;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * servlet的包装类
 */
public interface Wrapper extends Container{
    public static final String ADD_MAPPING_EVENT = "addMapping";
    public static final String REMOVE_MAPPING_EVENT = "removeMapping";

    public String getServletClass();
    public void setServletClass(String servletClass);

    public String[] getServletMethods() throws ServletException;

    public Servlet getServlet();
    public void setServlet(Servlet servlet);

    public void addInitParameter(String name, String value);
    public void addMapping(String mapping);
    public Servlet allocate() throws ServletException;
    public void deallocate(Servlet servlet) throws ServletException;
    public String findInitParameter(String name);
    public String[] findInitParameters();
    public String[] findMappings();

    /**
     * 加载并初始化servlet
     * @throws ServletException
     */
    public void load() throws ServletException;
    public void removeMapping(String mapping);
    public void unload() throws ServletException;

}
