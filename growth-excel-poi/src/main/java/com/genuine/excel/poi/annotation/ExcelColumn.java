package com.genuine.excel.poi.annotation;

import java.lang.annotation.*;

/**
 * 导出实体字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    // 标题
    String value() default "";
    // 正则
    String pattern() default "yyyy-MM-dd HH:mm:ss";
    // 排序号-必填
    int col() default 1;
    // 列宽
    int width() default -1;

}
