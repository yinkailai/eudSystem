package com.jacc.serviceedu.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneName;//一级分类名
    @ExcelProperty(index = 1)
    private String twoName;//二级分类名
}
