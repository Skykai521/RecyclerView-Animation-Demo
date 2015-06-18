package com.github.skykai.model;

import java.io.Serializable;

/**
 * 贴图Item
 * Created by sky on 2015/6/10.
 */
public class StickerItem implements Comparable<StickerItem> {

    /**
     * usenum : 75
     * name : 我的照片会说话
     * description : #我的照片会说话#活动指定贴图 打满五个标签即可参加活动 活动截止2015年6月11日
     * typeid : 1
     * zoom : 0.5
     * id : 194
     * pic : http://api.socuteapp.cn/UpLoadImages/Chartlet/thumbnail1/1433413315552.png
     * isnew : 1
     * type : 2
     * thumbnail2 : http://api.socuteapp.cn/UpLoadImages/Chartlet/thumbnail2/1433413315969.png
     * example : http://api.socuteapp.cn/UpLoadImages/Chartlet/example/1433413315496.jpg
     */
    private int dbid;
    private String usenum;
    private String name;
    private String description;
    private String typeid;
    private String zoom;
    private String id;
    private String pic;
    private String isnew;
    private String type;
    private String thumbnail2;
    private String example;


    public void setUsenum(String usenum) {
        this.usenum = usenum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setThumbnail2(String thumbnail2) {
        this.thumbnail2 = thumbnail2;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getUsenum() {
        return usenum;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeid() {
        return typeid;
    }

    public String getZoom() {
        return zoom;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getIsnew() {
        return isnew;
    }

    public String getType() {
        return type;
    }

    public String getThumbnail2() {
        return thumbnail2;
    }

    public String getExample() {
        return example;
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }


    @Override
    public int compareTo(StickerItem another) {

        if (dbid == another.getDbid()) {
            return 0;
        } else if (another != null) {
            if (dbid > another.getDbid()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 1;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof StickerItem)) {
            return false;
        }
        return id.equals(((StickerItem) o).getId());
    }
}
