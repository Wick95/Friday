package com.surge.Friday.tools.excel;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToExcel {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static File file;

    //创建sheet页
    public static void setSheet(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    //创建表头
    public static void createHead(List<String> headList) {
        //创建表头，也就是第一行
        row = sheet.createRow(0);
        for (int i = 0; i < headList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
        }
    }

    //创建表内容
    public static void createContent(List<List<String>> contentList) {
        //创建表内容，从第二行开始
        for (int i = 0; i < contentList.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < contentList.get(i).size(); j++) {
                row.createCell(j).setCellValue(contentList.get(i).get(j));
            }
        }
    }

    //写入文件
    public static void writeToFile(String filePath) {
        file = new File(filePath);
        //将文件保存到指定的位置
        try {
            workbook.write(new FileOutputStream(file));
            System.out.println("写入成功");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 内容测试数据
    protected static List<List<String>> getContent() {
        List<List<String>> contentList = new ArrayList<>();
        List<String> content1 = new ArrayList<>();
        content1.add("张三");
        content1.add("18");
        List<String> content2 = new ArrayList<>();
        content2.add("李四");
        content2.add("20");
        contentList.add(content1);
        contentList.add(content2);
        return contentList;
    }

    public static void main(String[] args) {
        //表头测试数据
        List<String> headList = new ArrayList<>();
        headList.add("昵称");
        headList.add("年龄");
        List<List<String>> contentList = getContent();//内容测试数据
        setSheet("WorkSheet");                        //创建sheet页
        createHead(headList);                         //设置表头
        createContent(contentList);                   //设置内容
        writeToFile("D://work.xls");         //写入文件
    }
}