package com.bm.insurance.cloud.sale.dto;

import java.io.Serializable;

/**
 * Created by dan on 2017/4/17.
 */
public class PageDto implements Serializable {

    private int page; //当前页

    private int rows; //每页显示条数

    public PageDto() {
        this.page = 1;
        this.rows = 1;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
