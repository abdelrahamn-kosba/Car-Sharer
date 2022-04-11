package de.unidue.inf.is.domain;

import de.unidue.inf.is.stores.BestDriverStore;

public class transportmittel {


    int tid;
    String name;
    String ICON;


    public transportmittel(int tid, String name) {
        this.tid = tid;
        this.name = name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getICON() {
        return ICON;
    }

    public void setICON(String ICON) {
        this.ICON = ICON;
    }
}
