package com.example.excel.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class ExportMoreView {
    private List<ExportView> moreViewList= Lists.newArrayList();

}
