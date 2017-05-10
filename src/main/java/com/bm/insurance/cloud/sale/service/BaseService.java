package com.bm.insurance.cloud.sale.service;

import com.bm.insurance.cloud.sale.dto.DataGridDto;
import com.github.pagehelper.Page;

import java.util.List;

public abstract class BaseService {

    public DataGridDto dataGridDto(List<?> data) {
        DataGridDto dataGridDto = new DataGridDto();
        dataGridDto.setRows(data);

        return dataGridDto;
    }

    public DataGridDto getDataGridDto(Page<?> page, List<?> data) {
        DataGridDto dataGridDto = new DataGridDto();
        dataGridDto.setTotal(page.getTotal());
        dataGridDto.setPages(page.getPageNum());
        dataGridDto.setRows(data);

        return dataGridDto;
    }

    public String like(String value) {
        return "%".concat(value.trim()).concat("%");
    }
}
