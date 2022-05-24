package com.narko.alarmclock;

import android.widget.Switch;
// Вот эта вся параша будет не нужна лол)
public class ListClock {
    private String name;
    private Switch switcher;
    private int flagResource;

    ListClock(String name, int resource, Switch onOff) {
        this.flagResource = resource;
        this.name = name;
        this.switcher = onOff;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}
