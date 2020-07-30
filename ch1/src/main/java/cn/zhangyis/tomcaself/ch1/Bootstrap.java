package cn.zhangyis.tomcaself.ch1;

import java.io.IOException;

/**
 * @description
 * @author: sksa
 * @create: 2020-07-30 15:47
 **/
public class Bootstrap {
    public static void main(String[] args) {
        try {
            HttpConnector httpConnector = new HttpConnector();
            SimpleContainer simpleContainer = new SimpleContainer();
            httpConnector.setContainer(simpleContainer);

            httpConnector.initialize();
            httpConnector.start();

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
