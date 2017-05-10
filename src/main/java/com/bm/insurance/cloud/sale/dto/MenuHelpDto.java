package com.bm.insurance.cloud.sale.dto;

import com.bm.insurance.cloud.sale.model.SaleMenu;

public class MenuHelpDto extends SaleMenu {

    private Long _parentId;

    public Long get_parentId() {
        return _parentId;
    }

    public void set_parentId(Long _parentId) {
        this._parentId = _parentId;
    }
}
