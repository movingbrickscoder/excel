package com.example.excel.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Data
public class User implements java.io.Serializable{

	private Integer id;

	@Excel(name = "name")
	@Pattern(regexp = "[\\u4E00-\\u9FA5]{2,5}", message = "姓名中文2-5位")
	private String name;

	@Max(value=20)
	@Excel(name = "age")
	private Integer age;
	@NotNull(message = "生日不能为空")
	@Excel(name = "birthday", importFormat = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthday;


}