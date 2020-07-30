package cn.zhangyis.tomcaself.ch1.life;

import java.util.EventObject;

/**
 * @description
 * @author: sksa
 * @create: 2020-07-30 17:26
 **/
public class LifecycleEvent extends EventObject {

    public LifecycleEvent(Lifecycle lifecycle, String type) {

        this(lifecycle, type, null);

    }

    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {

        super(lifecycle);
        this.lifecycle = lifecycle;
        this.type = type;
        this.data = data;

    }

    /**
     * The event data associated with this event.
     */
    private Object data = null;


    /**
     * The Lifecycle on which this event occurred.
     */
    private Lifecycle lifecycle = null;


    /**
     * The event type this instance represents.
     */
    private String type = null;


    public Object getData() {
        return data;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public String getType() {
        return type;
    }
}
