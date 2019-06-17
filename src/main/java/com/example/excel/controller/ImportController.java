package com.example.excel.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.example.excel.model.ExportMoreView;
import com.example.excel.model.ExportView;
import com.example.excel.model.UserInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/excel")
public class ImportController {

    @RequestMapping("/import")
    public void importExcel(@RequestParam("file") MultipartFile file){
        Workbook workBook = null;
        try {
            //获取多个sheet
            XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
            ExportMoreView exportMoreView = new ExportMoreView();
            List<ExportView> exportViewList = new ArrayList<>();
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                ImportParams importParams = new ImportParams();

                // 数据处理
                importParams.setStartSheetIndex(i);

                // 需要验证
                importParams.setNeedVerfiy(true);

                InputStream inputStream = file.getInputStream();
                ExcelImportResult<UserInfo> result;
                try {
                    result = ExcelImportUtil.importExcelMore(inputStream, UserInfo.class,
                            importParams);
                } catch (Exception e) {
                    log.error("解析失败",e);
                    return;
                }
                ExportParams empExportParams = new ExportParams();
                empExportParams.setSheetName("sheet"+(i+1));
                ExportView exportView = new ExportView();
                exportView.setDataList(result.getFailList());
                exportView.setCls(UserInfo.class);
                exportView.setExportParams(empExportParams);
                exportViewList.add(exportView);
            }
            exportMoreView.setMoreViewList(exportViewList);
            List<Map<String, Object>> exportParamList=Lists.newArrayList();
            for(ExportView view:exportMoreView.getMoreViewList()){
                Map<String, Object> valueMap= Maps.newHashMap();
                valueMap.put(NormalExcelConstants.DATA_LIST,view.getDataList());
                valueMap.put(NormalExcelConstants.CLASS,UserInfo.class);
                valueMap.put("title",view.getExportParams());
                exportParamList.add(valueMap);
            }
            FileOutputStream fos = new FileOutputStream("D:/baseModetest.xls");
            workBook = ExcelExportUtil.exportExcel(exportParamList, ExcelType.HSSF);
            workBook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
