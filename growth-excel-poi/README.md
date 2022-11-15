---
title: growth-excel-poi
date: 2022/10/20 19:27:00
tags:
- Excel
- Poi
categories:
- Excel
- Poi
---

# growth-excel-poi

在项目中操作Excel的工具-Poi

## 添加依赖

```xml
<!-- poi-ooxml是poi的升级版，直接使用poi-ooxm即可 -->
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>4.0.1</version>
</dependency>

<!-- 下面是easypoi的依赖，同时引入不影响-->
<dependency>
  <groupId>cn.afterturn</groupId>
  <artifactId>easypoi-spring-boot-starter</artifactId>
  <version>4.4.0</version>
</dependency>

<!-- 下面是jxl的依赖，同时引入不影响-->
<dependency>
  <groupId>net.sourceforge.jexcelapi</groupId>
  <artifactId>jxl</artifactId>
  <version>2.6.12</version>
</dependency>
```

## 介绍

### POI和JXL

excel流行的开发工具有两种：POI和JXL

如果Excel需求的功能比较复杂尽量使用Poi，

如果Excel有大量数据可以考虑使用Jxl，Jxl插入数据比Poi快。

### POI

Poi提供03和07版本，两个区别如下：

1. 03优点：数据写在内存中，最后一次性写入磁盘，速度快

2. 03缺点：最多只能处理65536行数据，否则会抛出异常

  

1. 07优点：可以一次写大量数据，如20W条
2. 07缺点：写数据慢，耗内存，可能会发生内存异常，如100W条数据

### 第三方类库

对Apache Poi的封装第三方库有EasyPoi和EasyExcel

SAX模式：一边扫描一边解析，不需要将数据存储在内存中。

EasyPoi 读写数据时，优先将数据写入内存，读写性能非常高，但是当数据量很大的时候，会出现OOM。也提供了SAX模式。

EasyExcel 基于SAX模式进行读写数据，不会出现OOM情况，程序运行比较稳定，相对于 EasyPoi 来说，读写性能稍慢。

EasyPoi 对定制化的导出支持更丰富，如果当前项目的需求，并发量不大、数据量也不大，但是需要导出 Excel 的文件样式千差万别，推荐用EasyPoi；反之，使用EasyExcel。

### My工具的使用

PoiUtil工具类中有对单元格内容的提取，拷贝表、行、单元格的操作

ExcelUtils中有以前在项目中使用的基础的单表导入和导出功能



