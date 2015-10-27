package com.kimi.marqueeimageviewapp.model;

import java.util.List;

public class idlist {
    public String section;

    public List<ids> idss;

    public List<newslist> newslist;

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return this.section;
    }

    public void setIds(List<ids> ids) {
        this.idss = ids;
    }

    public List<ids> getIds() {
        return this.idss;
    }

    public void setNewslist(List<newslist> newslist) {
        this.newslist = newslist;
    }

    public List<newslist> getNewslist() {
        return this.newslist;
    }

}
