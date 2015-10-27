package com.kimi.marqueeimageviewapp.model;

import java.util.List;

public class Root {
    public int ret;

    public List<idlist> idlist;

    public String version;

    public int timestamp;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getRet() {
        return this.ret;
    }

    public void setIdlist(List<idlist> idlist) {
        this.idlist = idlist;
    }

    public List<idlist> getIdlist() {
        return this.idlist;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

}