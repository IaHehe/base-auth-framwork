package com.bm.insurance.cloud.sale.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TreegridDto树形表格
 */
public class TreegridDto implements Serializable {

    private int total; //总数

    private String iconCls;  //图标

    private String state;  //图标open | closed

    private List rows = new ArrayList(); //数据集合

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
