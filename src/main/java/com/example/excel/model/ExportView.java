package com.example.excel.model;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.Data;

import java.util.List;

@Data
public class ExportView {

    private ExportParams exportParams;
    private List<?> dataList;
    private Class<?> cls;

}
