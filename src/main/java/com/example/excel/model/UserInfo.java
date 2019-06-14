package com.example.excel.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserInfo {

    @Excel(name = "姓名")
    @NotNull(message = "姓名不能为空")
    private String name;

    @Excel(name = "年龄")
    @NotNull(message = "年龄不能为空")
    @Size(max = 3,message = "长度不能超过3")
    private String age;

    @Excel(name = "邮箱地址")
    @NotNull(message = "邮箱地址不能为空")
    private String email;

    @Excel(name = "手机号")
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号格式不正确")
    private String phoneNumber;

    @Excel(name = "地址")
    @NotNull(message = "地址不能为空")
    @Size(max = 200,message = "长度不能超过200")
    private String address;



}
