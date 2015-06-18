package com.github.skykai.model;

import java.util.List;

/**
 * Created by sky on 2015/6/17.
 */
public class Sticker {


    private String state;
    private List<StickerItem> commonlist;
    private List<StickerType> typelist;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<StickerItem> getCommonlist() {
        return commonlist;
    }

    public void setCommonlist(List<StickerItem> commonlist) {
        this.commonlist = commonlist;
    }

    public List<StickerType> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<StickerType> typelist) {
        this.typelist = typelist;
    }
}
