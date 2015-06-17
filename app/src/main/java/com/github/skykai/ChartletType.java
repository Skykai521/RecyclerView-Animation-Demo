package com.github.skykai;

import java.io.Serializable;

/**
 * 贴图类型
 * Created by sky on 2015/6/10.
 */
public class ChartletType implements Serializable {
    /**
     * id : 0
     * type : 全部
     */
    private String id;
    private String type;


    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
