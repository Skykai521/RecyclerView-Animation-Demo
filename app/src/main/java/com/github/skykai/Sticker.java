package com.github.skykai;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2015/6/17.
 */
public class Sticker {


    private String state;
    private List<ChartletItem> commonlist;
    private List<ChartletType> typelist;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ChartletItem> getCommonlist() {
        return commonlist;
    }

    public void setCommonlist(List<ChartletItem> commonlist) {
        this.commonlist = commonlist;
    }

    public List<ChartletType> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<ChartletType> typelist) {
        this.typelist = typelist;
    }
}
