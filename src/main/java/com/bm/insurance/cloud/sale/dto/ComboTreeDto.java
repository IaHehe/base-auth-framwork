package com.bm.insurance.cloud.sale.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ComboTree下拉菜单树dto
 */
public class ComboTreeDto implements Serializable {

    private String id;

    private String text;

    private List children = new ArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
