package com.example.tomcatself.container;

import com.example.tomcatself.connector.Request;
import com.example.tomcatself.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * pipeline中的过滤器 用于请求时在容器中调用
 */
public interface Valve {
    public Valve getNext();
    public void setNext(Valve valve);
    public void invoke(Request request, Response response)
            throws IOException, ServletException;

    public boolean isAsyncSupported();

}
