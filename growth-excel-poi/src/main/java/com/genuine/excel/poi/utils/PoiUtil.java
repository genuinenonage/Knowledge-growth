package com.genuine.excel.poi.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PoiUtil {

    /**
     * 获取单元格的内容
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(cell.getDateCellValue());
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue();
            return str == null ? "" : str.trim();
        } else if (cell.getCellType() == CellType.FORMULA) {
            String str = cell.getStringCellValue();
            return str == null ? "" : str.trim();
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }
    }


    /**
     * 拷贝sheet（表）
     * @param targetSheet 目标sheet
     * @param sourceSheet 资源sheet
     * @param targetWork 目标workbook（用于创建目标单元格）
     * @param startRow 目标开始行数 默认0
     */
    public static void copySheet(Sheet targetSheet, Sheet sourceSheet,
                                 Workbook targetWork, int startRow) {
        if(targetSheet == null || sourceSheet == null || targetWork == null ){
            throw new IllegalArgumentException("调用PoiUtil.copyRow()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
        }

//        //设置单元格默认宽度
//        targetSheet.setDefaultColumnWidth(25);

        int lastRowNum = sourceSheet.getLastRowNum();

        //复制源表中的行
        for (int i = sourceSheet.getFirstRowNum(); i <= lastRowNum; i++) {
            Row sourceRow = sourceSheet.getRow(i);
            Row targetRow = targetSheet.createRow(i+startRow);  //创建新的row
            if (sourceRow != null) {
                copyRow(targetRow, sourceRow,
                        targetWork);
            }
        }

        // 合并单元格
        for (CellRangeAddress region : sourceSheet.getMergedRegions()) {
            int fr = region.getFirstRow()+startRow;
            int lr = region.getLastRow()+startRow;
            int fc = region.getFirstColumn();
            int lc = region.getLastColumn();
            // 合并单元格需要把样式都合并
            int oldfr = region.getFirstRow();
            Cell sourceCell = sourceSheet.getRow(oldfr).getCell(fc);
            CellStyle sourceCellStyle = sourceCell.getCellStyle();
            CellStyle targetCellStyle = targetWork.createCellStyle();
            targetCellStyle.cloneStyleFrom(sourceCellStyle);

            targetSheet.addMergedRegion(new CellRangeAddress(fr, lr, fc, lc));
            RegionUtil.setBorderBottom(sourceCellStyle.getBorderBottom(), new CellRangeAddress(fr, lr, fc,lc), targetSheet);
            RegionUtil.setBorderTop(sourceCellStyle.getBorderTop(), new CellRangeAddress(fr, lr, fc,lc), targetSheet);
            RegionUtil.setBorderLeft(sourceCellStyle.getBorderLeft(), new CellRangeAddress(fr, lr, fc,lc), targetSheet);
            RegionUtil.setBorderRight(sourceCellStyle.getBorderRight(), new CellRangeAddress(fr, lr, fc,lc), targetSheet);
        }
    }


    /**
     * 拷贝row(行)
     * @param targetRow 目标行
     * @param sourceRow 资源行
     * @param targetWork 目标workbook（用于创建目标单元格）
     */
    public static void copyRow(Row targetRow, Row sourceRow,
                               Workbook targetWork)  {
        if(targetRow == null || sourceRow == null || targetWork == null ){
            throw new IllegalArgumentException("调用PoiUtil.copyRow()方法时，targetRow、sourceRow、targetWork、sourceWork都不能为空，故抛出该异常！");
        }

        //设置行高
        targetRow.setHeight(sourceRow.getHeight());

        for (int i = sourceRow.getFirstCellNum(); i <= sourceRow.getLastCellNum(); i++) {
            Cell sourceCell = sourceRow.getCell(i);
            Cell targetCell = null;

            if (sourceCell != null && sourceCell.getStringCellValue()!="") {
                if (targetCell == null) {
                    targetCell = targetRow.createCell(i);
                }
                //拷贝单元格，包括内容和样式
                copyCell(targetCell, sourceCell, targetWork);
            }
        }
    }

    /**
     * 拷贝cell（单元格）
     * @param targetCell 目标单元格
     * @param sourceCell 资源单元格
     * @param targetWork 目标workbook（用于创建目标单元格）
     */
    public static void copyCell(Cell targetCell, Cell sourceCell, Workbook targetWork) {
        if(targetCell == null || sourceCell == null || targetWork == null ){
            throw new IllegalArgumentException("调用PoiUtil.copyCell()方法时，targetCell、sourceCell、targetWork都不能为空，故抛出该异常！");
        }

        CellStyle targetCellStyle=targetWork.createCellStyle();
        CellStyle sourceCellCellStyle = sourceCell.getCellStyle();
        targetCellStyle.cloneStyleFrom(sourceCellCellStyle);//拷贝样式
        //重新添加样式（这里可以根据你的需要重新进行单元格样式添加）
        targetCellStyle.setBorderTop(sourceCellCellStyle.getBorderTop());//设置上边框线
        targetCellStyle.setBorderLeft(sourceCellCellStyle.getBorderLeft());//设置左边框线
        targetCellStyle.setBorderBottom(sourceCellCellStyle.getBorderBottom());//设置下边框线
        targetCellStyle.setBorderRight(sourceCellCellStyle.getBorderRight());//设置右边框线
        targetCell.setCellStyle(targetCellStyle);

        targetCell.setCellValue(sourceCell.getStringCellValue());
    }

}
