package com.example.excel.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.example.excel.model.entity.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@RequestMapping("/excel")
@RestController
public class ExcelController {

    @PostMapping("/userImport")
    public void userImport(MultipartFile file) {

        try {
            ImportParams params = new ImportParams();
            params.setNeedVerfiy(true);
            ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(
                    file.getInputStream(),
                    User.class, params);
            FileOutputStream fos = new FileOutputStream("D:/baseModetest.xlsx");
            result.getFailWorkbook().write(fos);
            fos.close();
            for (int i = 0; i < result.getList().size(); i++) {
                System.out.println(ReflectionToStringBuilder.toString(result.getList().get(i)));
            }

            //ImportParams params = new ImportParams();
            //List<Object> list = ExcelImportUtil.importExcel(file.getInputStream(),
            //        User.class, params);
            //return  list;
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }

    }
}
