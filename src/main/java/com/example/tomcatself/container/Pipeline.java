package com.example.tomcatself.container;

import java.util.Set;

/**
 * 阀链 类似过滤器链  Valve每一个阀类似过滤器
 */
public interface Pipeline extends Contained {

    public Valve getBasic();
    public void setBasic(Valve valve);
    public void addValve(Valve valve);
    public Valve[] getValves();
    public void removeValve(Valve valve);
    public Valve getFirst();
    public boolean isAsyncSupported();
    public void findNonAsyncValves(Set<String> result);

}
