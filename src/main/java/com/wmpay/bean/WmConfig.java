package com.wmpay.bean;

public class WmConfig {
    private Integer wmConfigId;

    private String name;

    private String title;

    private String value;

    public Integer getWmConfigId() {
        return wmConfigId;
    }

    public void setWmConfigId(Integer wmConfigId) {
        this.wmConfigId = wmConfigId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}