package com.jufan.util;

import com.google.common.collect.Lists;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author pengyd
 * @Date 2017/11/18 11:12
 * @function: 封装写入Excel的操作
 */
public class DoExcelUtil {

    /**
     * 写入表头信息  String数组
     */
    public static void doExcel(String[] s, HSSFRow headRow, HSSFWorkbook workbook, HSSFSheet sheet) {
        for (int m = 0; m <= s.length - 1; m++) {
            HSSFCell cell = headRow.createCell(m);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            sheet.setColumnWidth(m, 3500);
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short color = HSSFColor.RED.index;
            font.setColor(color);
            style.setFont(font);
            //填写数据
            cell.setCellStyle(style);
            cell.setCellValue(s[m]);
        }
    }


    /**
     * 写入表头信息  List集合
     */
    public static void listExcel(List list, HSSFRow headRow, HSSFWorkbook workbook, HSSFSheet sheet) {
        for (int m = 0; m <= list.size() - 1; m++) {
            HSSFCell cell = headRow.createCell(m);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            sheet.setColumnWidth(m, 3500);
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short color = HSSFColor.RED.index;
            font.setColor(color);
            style.setFont(font);
            //填写数据
            cell.setCellStyle(style);
            cell.setCellValue((String) list.get(m));
        }
    }


    /**
     * 传入当前时间，获取前18个月的年和月
     */
    public static List<String> getMonthArray(String month) {
        List<String> stringList = Lists.newArrayList();
        String[] strings = month.split("-");
        int year = Integer.parseInt(strings[0]);
        int mon = Integer.parseInt(strings[1]);
        int index = 0;
        if (mon != 1) {
            for (int i = mon; i >= 1; i--) {
                if (i < 10) {
                    String s = year + "-0" + i;
                    stringList.add(s);
                } else {
                    String s = year + "-" + i;
                    stringList.add(s);
                }
                index++;
            }
            year = year - 1;
            for (int mon1 = 12; mon1 > 0; mon1--) {
                if (mon1 < 10) {
                    String s1 = year + "-0" + mon1;
                    stringList.add(s1);
                } else {
                    String s1 = year + "-" + mon1;
                    stringList.add(s1);
                }
                index++;
                if (index == 18) {
                    break;
                }
            }
            if (index < 18) {
                year = year - 1;
                for (int mon1 = 12; mon1 > 0; mon1--) {
                    if (mon1 < 10) {
                        String s1 = year + "-0" + mon1;
                        stringList.add(s1);
                    } else {
                        String s1 = year + "-" + mon1;
                        stringList.add(s1);
                    }
                    index++;
                    if (index == 18) {
                        break;
                    }
                }
            }
        } else {
            year = year - 1;
            for (int mon1 = 12; mon1 >= 1; mon1--) {
                if (mon1 < 10) {
                    String s1 = year + "-0" + mon1;
                    stringList.add(s1);
                } else {
                    String s1 = year + "-" + mon1;
                    stringList.add(s1);
                }
                index++;
            }
            year = year - 1;
            for (int mon2 = 12; mon2 > 6; mon2--) {
                if (mon2 < 10) {
                    String s1 = year + "-0" + mon2;
                    stringList.add(s1);
                } else {
                    String s1 = year + "-" + mon2;
                    stringList.add(s1);
                }
                index++;
            }
            System.out.println(index);
        }
        Collections.reverse(stringList);
        return stringList;
    }

    /**
     * 两个double相减
     */
    public static double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }


    /**
     * 解决double科学计数法
     */
    public static String big2(double d) {
        BigDecimal d1 = new BigDecimal(Double.toString(d));
        BigDecimal d2 = new BigDecimal(Integer.toString(1));
        // 四舍五入,保留2位小数
        return d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * 传入时间,和需要得到的前多少个月 得到年和月
     */
    public static List getMonth(String yearAndMonth, int n) {
        List<String> stringList = Lists.newArrayList();
        String[] strings = yearAndMonth.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);


        return null;
    }


//    public static void main(String[] args) {
//        //只支持读取2003
//        Map<Integer, List<String[]>> map = readExcel(new File("H:/总的.xls"));
//        for (int n = 0; n < map.size(); n++) {
//            List<String[]> list = map.get(n);
//            System.out.println("-------------------------sheet" + n + "--------------------------------");
//            for (int i = 0; i < list.size(); i++) {
//                String[] arr = (String[]) list.get(i);
//                for (int j = 0; j < arr.length; j++) {
//                    if (j == arr.length - 1)
//                        System.out.print(arr[j]);
//                    else
//                        System.out.print(arr[j] + "|");
//                }
//                System.out.println();
//            }
//        }
//        writeExcel(new File("H:/写入excel.xls"), map);
//    }

    /**
     *   读取
     *
     */
    public static Map<Integer, List<String[]>> readExcel(File file) {
        Map<Integer, List<String[]>> map = new HashMap<Integer, List<String[]>>();
        try {
            Workbook wb = Workbook.getWorkbook(file);
            for (int n = 0; n < wb.getSheets().length; n++) {
                Sheet sheet = wb.getSheet(n);
                if (sheet == null) {
                    continue;
                }
                List<String[]> list = new ArrayList<String[]>();
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell[] row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    String[] singleRow = new String[sheet.getColumns()];
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i); // 列 行
                        //获取Cell的类型:cell.getType()   类型的枚举CellType.xxx
                        singleRow[j] = cell.getContents();
                    }
                    list.add(singleRow);
                }
                map.put(n, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *   写入
     *
     */
    public static void writeExcel(File file, Map<Integer, List<String[]>> map) {
        try {
            // 创建文件
            WritableWorkbook book = Workbook.createWorkbook(file);
            for (int n = 0; n < map.size(); n++) {
                WritableSheet sheet = book.createSheet("sheet" + (n + 1), n);
                List<String[]> list = map.get(n);
                for (int i = 0; i < list.size(); i++) {
                    String[] arr = list.get(i);
                    for (int j = 0; j < arr.length; j++) {
                        sheet.addCell(new Label(j, i, arr[j]));
                    }
                }
            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static WritableWorkbook ssss(File file, Map<Integer, List<String[]>> map) {
        try {
            // 创建文件
            WritableWorkbook book = Workbook.createWorkbook(file);
            for (int n = 0; n < map.size(); n++) {
                WritableSheet sheet = book.createSheet("sheet" + (n + 1), n);
                List<String[]> list = map.get(n);
                for (int i = 0; i < list.size(); i++) {
                    String[] arr = list.get(i);
                    for (int j = 0; j < arr.length; j++) {
                        sheet.addCell(new Label(j, i, arr[j]));
                    }
                }
            }
            book.write();
            book.close();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }












}
