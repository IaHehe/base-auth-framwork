package com.bm.insurance.cloud.sale.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hubo on 2017/4/17.
 * 菜单树dto
 */
public class MenuTreeDto implements Serializable {

    private Long id;

    private String text;

    private String url;

    private boolean isOpen;

    private String icon;

    private String targetType;

    private List<MenuTreeDto> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public List<MenuTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeDto> children) {
        this.children = children;
    }
}
